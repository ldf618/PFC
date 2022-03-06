package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "examAnswers")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean finished;

    @Column 
    private LocalDate creationDate;

    @Column 
    private LocalDateTime changeDate;
   
    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    private Student student;
    
    @NotNull
    @ManyToOne (fetch = FetchType.LAZY)
    private Exam exam;

   //@OneToMany (mappedBy = "exam", fetch = FetchType.LAZY , cascade = CascadeType.PERSIST, orphanRemoval = true)
   //private List<ExamQuestionAnswer> examQuestionAnswers;
}
