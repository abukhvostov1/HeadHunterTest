package org.hh.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.hh.core.utils.ListenerThatHiglilightsElements;
import org.hh.core.utils.Proper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Help class for main test
 * 
 * @author Alexander Bukhvostov
 * 
 */

public class BaseTest {
	protected WebDriver driver;
	protected EventFiringWebDriver event_driver;

	/**
	 * Web drive starter
	 * 
	 * @param n
	 * @return
	 */
	private FirefoxProfile GetFireFoxProfile(int n) {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.http.use-cache", false);
		profile.setPreference("permissions.default.image", n);
		profile.setPreference("dom.ipc.plugins.enabled", false);
		profile.setPreference("webdriver.load.strategy", "unstable");
		return profile;
	}

	/**
	 * Return web driver from jvm (not mutex)
	 * 
	 * @param n
	 * @return
	 */
	public WebDriver GetWebDriver(int n) {
		if (driver == null) {
			driver = new FirefoxDriver(GetFireFoxProfile(n));
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			if (Proper.GetProperty("lightElement").equals("yes")) {
				event_driver = new EventFiringWebDriver(this.driver);
				event_driver.register(new ListenerThatHiglilightsElements(
						"#FFFF00", 1, 250, TimeUnit.MILLISECONDS));
				return event_driver;
			} else {
				return driver;
			}
		} else {
			if (Proper.GetProperty("lightElement").equals("yes"))
				return event_driver;
			else
				return driver;
		}

		/*
		 * IE switching on
		 * 
		 * System.setProperty("webdriver.ie.driver",
		 * "d:\\selenium-2.31.0\\IEDriverServer.exe");
		 * 
		 * return new InternetExplorerDriver();}
		 */
	}
	
	/**
	 * We should print any <generic> object
	 * @param obj
	 */
	public <T> void print(T obj) {
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println(obj);
	}

	/**
	 * Grabs screen and save it into run folder
	 * 
	 * @param sName
	 */
	protected void CaptureScreenshot(String sName) {
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String path = "src\\" + screenshot.getName();
		System.out.println(path);

		try {
			FileUtils.copyFile(screenshot, new File(/* "src\\" + */sName
					+ ".png"));
		} catch (IOException e) {
			print("Can't save captured screen");
		}
	}
}
