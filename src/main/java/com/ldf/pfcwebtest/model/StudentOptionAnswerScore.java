package com.ldf.pfcwebtest.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "StudentOptionAnswerScores")
public class StudentOptionAnswerScore {
   
    @EmbeddedId
    private StudentOptionAnswerScorePK id;

    @ManyToOne
    @MapsId("studentId") //This is the name of attr in StudentOptionAnswerScorePK class
    @JoinColumn(name = "students_id")
    private Student student;

    @ManyToOne
    @MapsId("optionAnswerId")
    @JoinColumn(name = "examQuestionOptionAnswer_id")
    private ExamQuestionOptionAnswer examQuestionOptionAnswer;   
        
    private int valoracion;
}
