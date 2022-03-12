package com.ldf.pfcwebtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author Lo
 */
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
        
        @ManyToOne
	private Consultant consultant;
        
        @ManyToOne
	private Course course;
}
