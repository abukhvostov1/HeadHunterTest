/**
 * 
 */
package org.hh.core.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * hh.ru/price main page
 * 
 * @author Alexander Bukhvostov
 * 
 */
public class PriceMain extends AbstractPage {

	@FindBy(xpath = "//input[@id='main-search-applicant']")
	private WebElement searchString;

	@FindBy(xpath = "//input[@class='searchbox-submit']//a")
	private WebElement searchButton;

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

	/**
	 * Login to private room
	 */
	public void clickLogin() {
		openPage("http://hh.ru/login");
	}

	/**
	 * 
	 * Search functionality
	 * 
	 * @param keyword
	 * @return
	 */
	public SearchPage vacancy(String keyword) {

		System.out.println("¬водим в поле поиска - " + keyword);
		sendText(searchString, keyword);
		clickElement(searchButton);
		return PageFactory.initElements(driver, SearchPage.class);
	}

	/**
	 * 
	 * Search functionality
	 * 
	 * @param keyword
	 * @return
	 */

	public SearchPage vacancy(String keyword, String skills) {

		System.out.println("¬водим в поле поиска - " + keyword);
		sendText(searchString, keyword);
		clickElement(searchButton);
		return PageFactory.initElements(driver, SearchPage.class);
	}

	/**
	 * 
	 * Search functionality
	 * 
	 * @param keyword
	 * @return
	 */
	public SearchPage vacancy(String keyword, String skills, String salary) {

		System.out.println("¬водим в поле поиска - " + keyword);
		sendText(searchString, keyword);
		clickElement(searchButton);
		return PageFactory.initElements(driver, SearchPage.class);
	}

	public void getdbAccessPage() {
		openPage("http://hh.ru/price#dbaccess");
	}

	public void getPublicationsPage() {
		openPage("http://hh.ru/price#publications");
	}

	public void getAdditionalPage() {
		openPage("http://hh.ru/price#publications");
	}

	public void getFavVacancies() {
		openPage("http://hh.ru/applicant/favorite_vacancies");
	}
}
