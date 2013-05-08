package org.hh.core.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Property reader
 * 
 * @author Alexander Bukhvostov
 *
 */
public class Proper
{
	protected static Properties prop;
	static
	{
		prop = new Properties();
		try
		{
			prop.load(ClassLoader.getSystemResourceAsStream("config.properties"));
		}
		catch(IOException exc)
		{
			exc.printStackTrace(); 
			System.out.println("Can't read properties or properties problem");
		}
	}
	
	public static String GetProperty(String sKey)
	{
		return prop.getProperty(sKey);
	}
}

