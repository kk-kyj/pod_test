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
 
    public void insertNewContent(String id, String msg) {
        template.update("INSERT INTO text.data(id, msg) VALUES(?, ?)", id, msg);
    }
    
    public String readContent(String id) {
        List<Map<String, Object>> list = template.queryForList("SELECT * FROM text.data WHERE id=" + id + "");
        StringBuilder builder = new StringBuilder();
        for(Map<String, Object> tmp : list) {
        	for(String key : tmp.keySet()) {
        		builder.append(key + "::" + tmp.get(key));
        		builder.append(",");
        	}
        	builder.append("\n");
        }
        return builder.toString();
    }
    
    public void createSchema() {
    	template.update("CREATE DATABASE IF NOT EXISTS text;");
    }
    
    public void createTable() {
    	template.update("CREATE TABLE IF NOT EXISTS data(id varchar(32) not null, msg varchar(128) not null, primary key (id));");
    }
}
