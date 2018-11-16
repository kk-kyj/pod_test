package io.aries.testpod;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBDao {
	@Autowired
    private JdbcTemplate template;
 
    public void readContent(String id) {
        List<Map<String, Object>> list = template.queryForList("SELECT * FROM text.data WHERE id='" + id + "'");
        list.forEach(System.out::println);
    }
    
    public void createSchema() {
    	template.update("CREATE DATABASE IF NOT EXISTS text");
    }
    
    public void createTable() {
    	template.update("CREATE TABLE IF NOT EXISTS data(id varchar(32) not null, msg varchar(128) not null, primary key id)");
    }
}
