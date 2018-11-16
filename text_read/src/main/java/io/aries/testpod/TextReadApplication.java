package io.aries.testpod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TextReadApplication {
	@Autowired
	private static DBDao dbDao;

	public static void main(String[] args) {
		SpringApplication.run(TextReadApplication.class, args);
		
		dbDao.createSchema();
		dbDao.createTable();
	}
}
