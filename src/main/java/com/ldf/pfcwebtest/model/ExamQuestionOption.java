package com.ldf.pfcwebtest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "examQuestionOptions")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionOption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private ExamQuestion examQuestion;

    @Column
    @Size(max = 500)
    @NotNull
    private String wording;

    @Column
    @NotNull
    private boolean isRigth;

    @Column
    private int position;

	
}
