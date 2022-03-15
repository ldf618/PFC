package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Consultant;

public class ConsultantDAO extends DAO<Consultant> {

    public ConsultantDAO() {
        this.setModelClass(Consultant.class);
    }
   
}
