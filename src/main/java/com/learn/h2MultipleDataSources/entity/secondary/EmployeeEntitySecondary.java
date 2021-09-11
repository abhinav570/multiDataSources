package com.learn.h2MultipleDataSources.entity.secondary;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="TBL_EMPLOYEES")
public class EmployeeEntitySecondary {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    public EmployeeEntitySecondary(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
//Setters and getters

    @Override
    public String toString() {
        return "EmployeeEntitySecondary [id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName + ", email=" + email + "]";
    }
}
