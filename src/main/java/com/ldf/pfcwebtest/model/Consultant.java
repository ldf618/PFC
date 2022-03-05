package com.ldf.pfcwebtest.model;

import javax.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

//javax.persistence
@Entity

@DiscriminatorValue("Consultants")

//Lombok
@NoArgsConstructor
public class Consultant extends User {

    @Builder(builderMethodName = "consultantBuilder")
    public Consultant(int idUser, String firstName, String surname1, String surname2, String dni, String userName, String userPassword) {
        super(idUser, firstName, surname1, surname2, dni, userName, userPassword);
    }
}
