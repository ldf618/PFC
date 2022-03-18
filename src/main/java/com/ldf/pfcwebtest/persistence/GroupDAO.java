package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Group;
import com.ldf.pfcwebtest.model.Group_;
import java.util.List;
import java.util.Map;
import javax.persistence.metamodel.SingularAttribute;

public class GroupDAO extends DAO<Group> {

    public GroupDAO() {
        this.setModelClass(Group.class);
    }

    public Group findByStudentAndClassroom(int idStudent, int idClassroom) {

        Map<String, Integer> parameters = Map.of(
                "idStudent", idStudent,
                "idClassroom", idClassroom);

        return execJPQLSingle("group.findByStudentAndClassroom", parameters);

    }

    public List<Group> findByClassroom(int idClassroom) {
        Map<String, Integer> parameters = Map.of(
                "idClassroom", idClassroom);
        return execJPQLMultiple("group.findByClassroom", parameters);
    }
    
    public List<Group> findByClassroom2(int idClassroom) {
        Map<SingularAttribute, Integer> parameters = Map.of(
                Group_.classroom, idClassroom);
        int rango[] = new int [] {0,1};
        return findCustom(parameters, rango, "name", true);
    }    
}
