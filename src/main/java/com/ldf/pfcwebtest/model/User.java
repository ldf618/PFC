package com.ldf.pfcwebtest.model;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

//javax.persistence
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
        
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String name;
        
        @Column
        @Size(max = 50)
	private String surname1;
        
        @Column
        @Size(max = 50)
	private String surname2;
        
        @Column
        @NotNull
        @Size(min = 9, max = 9)
	private String dni;
        
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String userName;
        
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String userPassword;
 
}
