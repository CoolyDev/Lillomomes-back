package com.oms.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(	name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "scolarite")
    private String scolarite;

    @Column(name = "archived")
    private Boolean archived;

    @OneToMany( cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,mappedBy = "room")
   // @JsonManagedReference
    private List<Student> studentList = new ArrayList<>();
}
