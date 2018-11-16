package io.aries.testpod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextRestController {
	@Autowired
	private DBDao dbDao;
	
	private String hostName;
	
	public TextRestController() {
		hostName = runCmd(new String("hostname").split(" "));
	}
	
	@RequestMapping(value="/read/txt", method=RequestMethod.GET)
	public String writeTextToFile(@RequestParam(value="id") String id, @RequestParam(value="msg") String msg) {
		File file = new File("/kube/txt/log.txt");
		
		try {
			BufferedReader bd = new BufferedReader(new FileReader(file));
			String result = "";
			while(true) {
				String tmp = bd.readLine();
				if(tmp == null) {
					break;
				}
				
				if(tmp.contains(id)) {
					result += tmp;
				}
			}
			
			bd.close();
			return result;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		return "OK";
	}
	
	@RequestMapping(value="/read/db", method=RequestMethod.GET)
	public String writeTextToDB(@RequestParam(value="id") String id) {
		dbDao.readContent(id);
		return "";
	}
	
	private String runCmd(String[] cmdArr) {
		StringBuilder stringBuilder = new StringBuilder();
		
		try {			
			ProcessBuilder builder = new ProcessBuilder(cmdArr);
			
			builder.start();
			
			Process process = builder.start();
			BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String s = "";
			
			while ((s = stdOut.readLine()) != null) {
				stringBuilder.append(s);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}
}
