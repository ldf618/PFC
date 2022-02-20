package com.ldf.pfcwebtest.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

//javax.persistence
@Entity

//Lombok
@NoArgsConstructor
public class Student extends User {

    @Builder(builderMethodName = "studentBuilder")
    public Student(int idUser, String name, String surname1, String surname2, String dni, String userName, String userPassword) {
        super(idUser, name, surname1, surname2, dni, userName, userPassword);
    }
}
