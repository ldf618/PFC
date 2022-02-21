package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//javax.persistence
@Entity
@Table(name = "Courses")
//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Course implements Serializable {

    	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCourse;
        
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String courseName;
                
        @Column
	private double credits;

}
