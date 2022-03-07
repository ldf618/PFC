package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Lo
 */
@Entity
@Table (name="classrooms")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
