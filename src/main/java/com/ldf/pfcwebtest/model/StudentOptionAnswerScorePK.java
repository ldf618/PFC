package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable

@Data
@NoArgsConstructor
public class StudentOptionAnswerScorePK implements Serializable{
    
    @Column(name = "students_id")
    private int studentId;

    @Column(name = "examQuestionOptionAnswer_id")
    private int optionAnswerId;
}
