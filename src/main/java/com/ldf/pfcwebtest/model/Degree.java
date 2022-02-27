package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="degrees")
public class Degree implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
        
        @NotNull
        @Column
        @Size(min=2, max=50)
	private String name;
        
        @OneToMany (mappedBy = "degree", fetch = FetchType.LAZY /*, orphanRemoval = true*/)
        private List<Course> courses;
	
}
