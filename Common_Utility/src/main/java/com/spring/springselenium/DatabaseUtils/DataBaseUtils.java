package com.spring.springselenium.DatabaseUtils;

import com.spring.springselenium.Configuraion.annotation.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
@Page
public class DataBaseUtils {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepo repo;

    public  List<Student> FetchDatabaseRecordsEntity(String query){
        List<Student> students=jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Student.class));
        return students;
    }

    public  List<Map<String, Object>> FetchDatabaseRecords(String query){
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        return results;
    }
}
