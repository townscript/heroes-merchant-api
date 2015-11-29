package com.townscript.hero.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {

	private Properties properties;
	
	private static ProjectProperties projectProperties;

	private ProjectProperties() {
		// TODO Auto-generated constructor stub

		try {
			properties = new Properties();
			String path =System.getenv("CATALINA_HOME");
			properties.load(new FileInputStream(path+"/conf/merchantdatabase_project.properties"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static ProjectProperties getInstance() {
		if(projectProperties == null) {
			projectProperties = new ProjectProperties();
		}
		
		return projectProperties;
	}


	public boolean isProduction() {
		String env=properties.getProperty("environment");
		if(env.equals("production")) {
			return true;
		}
		return false;
	}

}
