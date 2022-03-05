package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

@Entity
@Table(name = "exams")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam implements Serializable {

    public enum ExamType{
        INDIVIDUAL,GROUP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @Column
    @Size(max = 500)
    private String instructions;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ExamType type;

    @Column //(columnDefinition = "DATE")
    private ZonedDateTime publicationDate;

    @Column //(columnDefinition = "DATE")
    private LocalDate creationDate;

    @Column //(columnDefinition = "DATE")
    private LocalDateTime changeDate;

    @Column //(columnDefinition = "DATE")
    private LocalDate deadline;
    
    @NotNull
    @ManyToOne (fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Course course;
    
    @NotNull
    @ManyToOne (fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Consultant consultant;

//	  private List<ApartadoPlantilla> apartados;
}
