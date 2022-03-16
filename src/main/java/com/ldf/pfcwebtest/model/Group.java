package com.ldf.pfcwebtest.model;

//javax.persistence

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Lo
 */
@Entity
@Table(name = "Groups")
@NamedQueries({
    @NamedQuery(name = "group.findByClassroom", 
                query = "from Group g where g.classroom.id=:idClassroom"),
    @NamedQuery(name = "group.findByStudentAndClassroom", 
                query = "select group from Group group "
                        + "join group.students student "
                        + "where student.id=:idStudent and group.classroom.id=:idClassroom")
})

//Lombok
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Group extends IdentityIntId {/*implements Serializable{
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;*/
        
        @Column
        @NotNull
        @Size(min = 2, max = 50)
	private String name;
        
        //Mapped by es el nombre de la lista en la clase Student
        @ManyToMany(mappedBy="groups",cascade = CascadeType.ALL) 
        private List<Student> students;
        
        @ManyToOne(cascade = CascadeType.ALL)
	private Classroom classroom;
}
