package com.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProUtil {
	public static Properties pro(String ProURL){
		 Properties p = new Properties();  
		 InputStream in = Proper.class.getResourceAsStream(ProURL);
		 try {
			p.load(in);
			in.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return p;
	}
}
