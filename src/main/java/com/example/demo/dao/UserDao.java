package com.example.demo.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    Map getUser(String email);
    void insertUser(Map params);
}
