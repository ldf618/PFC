package com.ldf.pfcwebtest.model;

import java.util.List;
import javax.persistence.*;
import lombok.Builder;
import lombok.Setter;
import lombok.NoArgsConstructor;

//javax.persistence
@Entity
@DiscriminatorValue("Students")
//Lombok
@NoArgsConstructor
@Setter
public class Student extends User {
    
    /*
    @JoinTable(
        name = "rel_student_group",
        joinColumns = @JoinColumn(name = "FK_STUDENT", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_GROUP", nullable = false)
    )*/
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

    @Builder(builderMethodName = "studentBuilder")
    public Student(int idUser, String name, String surname1, String surname2, String dni, String userName, String userPassword) {
        super(idUser, name, surname1, surname2, dni, userName, userPassword);
    }
}
