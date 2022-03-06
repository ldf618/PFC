package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "StudentOptionAnswerScores")
public class StudentOptionAnswerScore implements Serializable{
   
    @EmbeddedId
    private StudentOptionAnswerScorePK id;

    @ManyToOne
    @MapsId("studentId") //This is the name of attr in StudentOptionAnswerScorePK class
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("optionAnswerId")
    @JoinColumn(name = "examQuestionOptionAnswer_id")
    private ExamQuestionOptionAnswer examQuestionOptionAnswer;   
       
    @Column
    private int score;
}
