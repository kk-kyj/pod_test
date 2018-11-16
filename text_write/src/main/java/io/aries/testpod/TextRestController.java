package io.aries.testpod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
	
	@RequestMapping(value="/write/txt", method=RequestMethod.POST)
	public String writeTextToFile(@RequestParam(value="id") String id, @RequestParam(value="msg") String msg) {
		File file = new File("/kube/txt/log.txt");
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			bw.write(id + "::" + hostName + "," + msg + "\n");
			bw.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		return "OK";
	}
	
	@RequestMapping(value="/write/db", method=RequestMethod.POST)
	public String writeTextToDB(@RequestParam(value="id") String id, @RequestParam(value="msg") String msg) {
		dbDao.insertNewContent(id, hostName + "," + msg);
		
		return "OK";
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