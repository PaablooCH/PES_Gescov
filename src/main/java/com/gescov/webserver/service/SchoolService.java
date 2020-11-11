package com.gescov.webserver.service;

import com.gescov.webserver.dao.school.SchoolDao;
import com.gescov.webserver.exception.IsNotAnAdministratorException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Classroom;
import com.gescov.webserver.model.School;
import com.gescov.webserver.model.User;
import com.mongodb.client.FindIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    UserService userService;

    @Autowired
    ClassroomService classroomService;

    public School addSchool(School school) {
        String creatorID = school.getCreatorID();
        userService.existsUser(creatorID);
        schoolDao.insert(school);
        userService.addSchool(creatorID, school.getId());
        return school;
    }

    public void addAdministrator(String schoolID, String adminID) {
        Optional<School> s = schoolDao.findById(schoolID);
        if (s.isEmpty()) throw new NotFoundException(School.class, schoolID);
        s.get().addAdministrator(adminID);
        schoolDao.save(s.get());
    }

    public List<School> getAllSchools() {
        return schoolDao.findAll();
    }

    public Optional<School> getSchoolById(String id) {
        return schoolDao.findById(id);
    }

    public School getSchoolByName(String schoolName) {
        return schoolDao.findByName(schoolName);
    }

    public void deleteSchool(String id, String adminID) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        List<String> admins = s.get().getAdministratorsID();
        if(!admins.contains(adminID)) throw new IsNotAnAdministratorException(User.class, id);
        List<Classroom> c = classroomService.getClassroomsBySchoolID(id);
        for(Classroom cl : c) {
            classroomService.deleteClassroom(cl.getId());
        }
        schoolDao.deleteById(id);
    }

    public void updateSchoolName(String id, String update) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        s.get().setName(update);
        schoolDao.save(s.get());
    }

    public void updateSchoolState(String id, String update) {
        Optional<School> s = schoolDao.findById(id);
        if (s.isEmpty()) throw new NotFoundException(School.class, id);
        s.get().setState(update);
        schoolDao.save(s.get());
    }

}