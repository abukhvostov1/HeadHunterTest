/**
 * 
 */
package org.hh.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * To Login to HH
 * 
 * @author Alexander Bukhvostov
 * 
 */
public class LoginPage extends AbstractPage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hh.core.pages.AbstractPage#OpenPage(java.lang.String)
	 */
	@Override
	public void openPage(String url) {
	}

	/**
	 * This function have some tips and tricks to use page elements........... I
	 * think this is selenium problem
	 * 
	 * @param userName
	 * @param password
	 * @param remember
	 * @return
	 */
	public PriceMain login(String userName, String password, String remember) {
		sleep(1000);
		WebElement webAction = driver.findElement(By.name("action"));
		sendText("loginform-input-username", userName);
		sendText("loginform-input-password", password);
		sendText("loginform-input-remember", remember);
		webAction.submit();
		return PageFactory.initElements(driver, PriceMain.class);
	}
}
