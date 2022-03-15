package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Group;

public class GroupDAO extends DAO<Group> {

    public GroupDAO() {
        this.setModelClass(Group.class);
    }
   
}
