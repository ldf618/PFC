package com.ldf.pfcwebtest.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "exams")

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Exam extends IdentityIntId {

    public enum ExamType{
        INDIVIDUAL,GROUP
    }
/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
*/

    @Column
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @Column
    @Size(max = 500)
    private String instructions;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 25)
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

    @OneToMany (mappedBy = "exam", fetch = FetchType.LAZY , cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ExamQuestion> examQuestions;
}
