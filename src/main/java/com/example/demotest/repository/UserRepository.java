package com.example.demotest.repository;

import com.example.demotest.mapper.UserMapper;
import com.example.demotest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    @Autowired
    public UserRepository(
            JdbcTemplate jdbcTemplate,
            UserMapper userMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public int addUser(User user) {
        String query =  "INSERT INTO USERS(username, firstname, lastname) VALUES (?, ?, ?)";

        return jdbcTemplate.update(query, user.getUsername(), user.getFirstname(), user.getLastname());
    }

    public List<User> findAll(){
        String query = "SELECT id, username, firstname, lastname FROM users";

        return  jdbcTemplate.query(query, userMapper);
    }

    public Optional<User> findById(int id){
        String query = "SELECT id, username, firstname, lastname FROM users WHERE id = ?";

        return jdbcTemplate.query(query, userMapper, id).stream().findFirst();
    }
}
