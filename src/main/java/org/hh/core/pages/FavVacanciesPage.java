/**
 * 
 */
package org.hh.core.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

/**
 * @author User
 * 
 */
public class FavVacanciesPage extends AbstractPage {

	/**
	 * @param driver
	 */
	public FavVacanciesPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hh.core.pages.AbstractPage#openPage(java.lang.String)
	 */
	@Override
	public void openPage(String sUrl) {

	}

	public ArrayList<String> getFavacancies() {
		if (checkElement(
				"//div[@class='HH-FavoriteVacancies-NoVacanciesText']",
				"vacancies")) {
			return new ArrayList<>();
		} else {
			System.out.println("ArrayList with element should be returned");
			return new ArrayList<>();
		}

	}

}
