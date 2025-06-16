package com.hospitalAuth.AuthHMS.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "privileges")
@Data
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; // e.g., READ_PATIENT, CREATE_APPOINTMENT

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();
}
