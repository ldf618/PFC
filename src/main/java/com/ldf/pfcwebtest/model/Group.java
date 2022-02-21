package com.ldf.pfcwebtest.model;

//javax.persistence

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Groups")
//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Group implements Serializable{
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGroup;
        
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String groupName;
        
        //Mapped by es el nombre de la lista en la clase Student
        @ManyToMany(mappedBy="groups") 
        private List<Student> students;
        
	private Integer idAula;
}
