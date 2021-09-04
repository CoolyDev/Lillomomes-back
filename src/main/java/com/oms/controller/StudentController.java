package com.oms.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.oms.model.EmergencyContact;
import com.oms.payload.response.MessageResponse;
import com.oms.service.EmergencyContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.oms.model.Student;
import com.oms.service.StudentService;

@CrossOrigin(origins="*", maxAge=1800)
@RestController
@RequestMapping("/api")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  EmergencyContactsService emergencyContactsService;

  @GetMapping("/students")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN') or hasRole('USER')")
  public Page<Student> getAllStudents(Pageable pageable){
    return studentService.findAllStudent(pageable);}

  @PostMapping("/students")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('MODERATOR')")
  public ResponseEntity<?> addStudent(@RequestBody Student studentRequest){
  /*  if (studentService.existsByEmail(studentRequest.getCode())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }*/
    Student userStudent = new Student(
    studentRequest.getFirstName(),
    studentRequest.getLastName(),
    studentRequest.getGender(),
    studentRequest.getBirthday(),
    studentRequest.getNationality(),
    studentRequest.getIdentificationNumber(),
    studentRequest.getAddress(),
    studentRequest.getCity(),
    studentRequest.getRoom()
    );
    Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
    if(!studentRequest.getEmergencyContacts().isEmpty()){
      studentRequest.getEmergencyContacts().forEach(
              emergencyContact -> {
                EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);
                emergencyContacts1.add(ec);
              }
      );};
    studentService.saveStudent(userStudent);
    return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
  }

  @PutMapping("/students/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
  public Optional<Student> updateStudent (@RequestBody Student studentRequest, @PathVariable ("id") Long Id){
    return studentService.findStudentByCode(Id).map(student -> {
      student.setLastName(studentRequest.getLastName());
      student.setFirstName(studentRequest.getFirstName());
      student.setGender(studentRequest.getGender());
      student.setBirthday(studentRequest.getBirthday());
      student.setNationality(studentRequest.getNationality());
      student.setIdentificationNumber(studentRequest.getIdentificationNumber());
      student.setAddress(studentRequest.getAddress());
      student.setCity(studentRequest.getCity());
      Set<EmergencyContact> emergencyContacts1 = new HashSet<>();
      if(!studentRequest.getEmergencyContacts().isEmpty()){
        studentRequest.getEmergencyContacts().forEach(
                emergencyContact -> {
                  EmergencyContact ec= emergencyContactsService.saveEmergencyContact(emergencyContact);

                  emergencyContacts1.add(ec);
                }
        );};
      student.setEmergencyContacts(emergencyContacts1);
      return studentService.saveStudent(student);
    });
  }
  @DeleteMapping("/students/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
  public void deleteStudent(@PathVariable ("id")Long Id){
     studentService.deleteStudent(Id);
  }

  @GetMapping("/students/{id}")
  @PreAuthorize("hasRole('SUPER_ADMIN')or hasRole('ADMIN')or hasRole('USER')")
  public Optional<Student> selectOneStudent(@PathVariable ("id") Long Id){
    return studentService.findStudentByCode(Id);
  }
};
