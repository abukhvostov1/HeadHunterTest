/**
 * 
 */
package org.hh.core.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

/**
 * hh.ru/price main page
 * 
 * @author Alexander Bukhvostov
 * 
 */
public class PriceMain extends AbstractPage {

	public PriceMain(WebDriver driver) {
		super(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hh.core.pages.AbstractPage#OpenPage(java.lang.String)
	 */
	@Override
	public void openPage(String url) {
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Will open page " + url);
	}

}
