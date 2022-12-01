package com.spring.springselenium;

import com.spring.springselenium.DatabaseUtils.DataBaseUtils;
import com.spring.springselenium.DatabaseUtils.Student;
import com.spring.springselenium.DatabaseUtils.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


@SpringBootTest
public class SpringTestNGTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepo repo;
    @Autowired
    private DataBaseUtils dataBaseUtils;

    @Test
    public void fetchDatabaseRecords(){
        String sql ="select * from student";
        String query = "select * from student";

        System.out.println("printing Map Values +++++++++++++");
        List<Map<String, Object>> results = dataBaseUtils.FetchDatabaseRecords(query);
        for (Map<String, Object> result : results) {
            for (String key : result.keySet()) {
                System.out.print(key + " = " + result.get(key) + "; ");
            }
            System.out.println();
        }

        System.out.println("printing List Values +++++++++++++");
        List<Student> students=dataBaseUtils.FetchDatabaseRecordsEntity(sql);
        for (Student student : students) {
            System.out.println(student.getSno() + " , " + student.getName() + " , " + student.getAddress()+";");
        }

      }

    @Test(enabled = false)
    public void InsertRecords(){
        repo.save(new Student(15,"saida","vinukonda"));
    }


}
