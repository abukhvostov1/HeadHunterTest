package org.hh.test;

import org.hh.core.exception.ExceptFailTest;
import org.hh.core.pages.LoginPage;
import org.hh.core.pages.PriceMain;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 
 * TODO: Makes real tests! TODO: Do not forget implement :-)
 * 
 * @author Alexander Bukhvostov
 * 
 */
public class TestRunner extends BaseTest {

	private PriceMain priceMain;
	private LoginPage loginPage;

	@Test(groups = { "test_Price" })
	@Parameters({ "url", "imageEnabled", "userName", "password", "remember" })
	public void test_Price(String url, String imageEnabled, String userName,
			String password, String remember) throws ExceptFailTest {
		try {
			priceMain = PageFactory.initElements(
					getWebDriver(Integer.parseInt(imageEnabled)),
					PriceMain.class);
			print("\r\nPrice page");
			priceMain.openPage(url);
			loginPage = priceMain.clickLogin();
			priceMain = loginPage.login(userName, password, remember);
			Thread.sleep(5000);
			print("Test acomplished!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
