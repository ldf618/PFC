package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "examQuestions")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestion implements Serializable {

    public enum QuestionType {
        FREETEXT, OPTIONS, INDIVIDUAL_EVALUATION, GROUP_EVALUATION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //@NotNull
    @ManyToOne
    private Exam exam;
  
    @Column
    @NotNull
    @Size(min = 2, max = 500)
    private String wording;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 25)
    private QuestionType type;

    @Column
    @NotNull
    private boolean isMultipleSelection;

    @Column
    private int position;

    @OneToMany (mappedBy = "examQuestion", fetch = FetchType.LAZY , cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List <ExamQuestionOption> examQuestionOptions;
}
