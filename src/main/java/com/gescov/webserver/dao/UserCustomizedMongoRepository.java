package com.gescov.webserver.dao;

import com.gescov.webserver.model.School;

import java.util.List;

public interface UserCustomizedMongoRepository<T, ID> {

    List<School> getUserSchools(String id);

}