package com.ldf.pfcwebtest.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table (name="classrooms")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Classroom extends IdentityIntId{
	         
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String name;
        
        @EqualsAndHashCode.Exclude
        @ToString.Exclude        
        @ManyToOne(cascade=CascadeType.PERSIST)
	private Consultant consultant;
        
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToOne(cascade=CascadeType.PERSIST)
	private Course course;
        
        //Mapped by es el nombre de la lista en la clase Student
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToMany(mappedBy="classrooms",cascade = CascadeType.ALL) 
        private List<Student> students;
}
