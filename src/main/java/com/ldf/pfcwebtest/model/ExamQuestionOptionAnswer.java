package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.ToString;

@Entity
@Table(name = "examQuestionOptionAnswers")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionOptionAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ExamQuestionOption examQuestionOption;
    
    @ToString.Exclude //Avoid StackOverflow error
    @ManyToOne
    private ExamQuestionAnswer examQuestionAnswer;

    @Column
    @Size(max = 4000)
    @NotNull
    private String answer;

    @Column
    @NotNull
    private boolean isSelected;

    @OneToMany(mappedBy = "examQuestionOptionAnswer")
    private List <StudentOptionAnswerScore> Score;
}
