package com.ldf.pfcwebtest.model;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class User {
	
	private int idUser;
	private String name;
	private String surname1;
	private String surname2;
	private String dni;
	private String userName;
	private String userPassword;
 
}
