package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentOptionAnswerScorePK implements Serializable{
    
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "examQuestionOptionAnswer_id")
    private int optionAnswerId;
}
