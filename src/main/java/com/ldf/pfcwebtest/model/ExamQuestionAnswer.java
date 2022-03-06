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
import lombok.ToString;

@Entity
@Table(name = "examQuestionAnswers")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionAnswer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ToString.Exclude
    @NotNull
    @ManyToOne
    private ExamAnswer examAnswer;
        
    //@ToString.Exclude
    @NotNull
    @ManyToOne
    private ExamQuestion examQuestion;    
  
    @Column
    @NotNull
    @Size(max = 4000)
    private String answer;
    
    @OneToMany (mappedBy = "examQuestionAnswer", fetch = FetchType.LAZY , cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List <ExamQuestionOptionAnswer> examQuestionOptionsAnswer;
}
