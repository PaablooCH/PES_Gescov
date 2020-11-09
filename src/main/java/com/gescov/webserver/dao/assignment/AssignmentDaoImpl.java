package com.gescov.webserver.dao.assignment;

import com.gescov.webserver.dao.classSession.ClassSessionDao;
import com.gescov.webserver.model.Assignment;
import com.gescov.webserver.model.ClassSession;
import com.gescov.webserver.model.Contagion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("assignmentMongo")
public class AssignmentDaoImpl<T, ID> implements AssignmentDaoCustom<T, ID> {

    private final MongoTemplate mongoTemplate;
    private final ClassSessionDao classSessionDao;

    @Autowired
    public AssignmentDaoImpl(MongoTemplate mongoTemplate, ClassSessionDao classSessionDao) {
        this.mongoTemplate = mongoTemplate;
        this.classSessionDao = classSessionDao;
    }

    @Override
    public List<Assignment> findByClassroom(String classroomID) {
        List<ClassSession> classSessions = classSessionDao.selectAllByClassroomId(classroomID);
        List<String> classSessionID = new ArrayList<>();
        Query q = new Query();
        for (ClassSession cs : classSessions) classSessionID.add(cs.getId());
        q.addCriteria(Criteria.where("classSessionID").in(classSessionID));
        return mongoTemplate.find(q, Assignment.class);
    }
}
