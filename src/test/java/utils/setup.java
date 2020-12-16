package utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class setup {

public static Properties CONFIG=null;
	
	
	public void initConfigurations(){
		
		 CONFIG = new Properties();
		try {
			FileInputStream fs = new FileInputStream(Constants.CONFIG_FILE_PATH);
			CONFIG.load(fs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	@BeforeSuite
	public void init(){
		initConfigurations();
			
	}
	
	
	
}

