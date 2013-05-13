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

	@Test(groups = { "test_Login" })
	@Parameters({ "url", "imageEnabled", "userName", "password", "remember" })
	public void test_Search1(String url, String imageEnabled, String userName,
			String password, String remember) throws ExceptFailTest {
		try {
			priceMain = PageFactory.initElements(
					getWebDriver(Integer.parseInt(imageEnabled)),
					PriceMain.class);
			print("\r\nPrice page");
			priceMain.openPage(url);
			priceMain.clickLogin();
			loginPage.login(userName, password, remember);
			priceMain.openPage(url);
			Thread.sleep(5000);
			print("Test acomplished!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

//	@Test(groups = { "test_Search1" })
//	@Parameters({ "url", "imageEnabled", "userName", "password", "remember",
//			"keyword", "skills", "salary" })
//	public void test_Search1(String url, String imageEnabled, String userName,
//			String password, String remember, String keyword, String skills,
//			String salary) throws ExceptFailTest {
//		try {
//			priceMain = PageFactory.initElements(
//					getWebDriver(Integer.parseInt(imageEnabled)),
//					PriceMain.class);
//			print("\r\nPrice page");
//			priceMain.openPage(url);
//			loginPage = priceMain.clickLogin();
//			priceMain = loginPage.login(userName, password, remember);
//			priceMain.vacancy(keyword, skills, salary);
//			Thread.sleep(5000);
//			print("Test acomplished!");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			driver.quit();
//		}
//	}
//
//	@Test(groups = { "test_Search2" })
//	@Parameters({ "url", "imageEnabled", "keyword", "skills", "salary" })
//	public void test_Search2(String url, String imageEnabled, String keyword,
//			String skills, String salary) {
//		try {
//			priceMain = PageFactory.initElements(
//					getWebDriver(Integer.parseInt(imageEnabled)),
//					PriceMain.class);
//			print("\r\nPrice page");
//			priceMain.openPage(url);
//			priceMain.vacancy(keyword, skills, salary);
//			Thread.sleep(5000);
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			driver.quit();
//		}
//	}
//
//	@Test(groups = { "test_Search3" })
//	@Parameters({ "url", "imageEnabled", "userName", "password", "remember",
//			"keyword" })
//	public void test_Search3(String url, String imageEnabled, String userName,
//			String password, String remember, String keyword)
//			throws ExceptFailTest {
//		try {
//			priceMain = PageFactory.initElements(
//					getWebDriver(Integer.parseInt(imageEnabled)),
//					PriceMain.class);
//			print("\r\nPrice page");
//			priceMain.openPage(url);
//			loginPage = priceMain.clickLogin();
//			priceMain = loginPage.login(userName, password, remember);
//			priceMain.vacancy(keyword);
//			Thread.sleep(5000);
//			print("Test acomplished!");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			driver.quit();
//		}
//	}
//
//	@Test(groups = { "test_Search4" })
//	@Parameters({ "url", "imageEnabled", "userName", "password", "remember",
//			"keyword", "skills" })
//	public void test_Search4(String url, String imageEnabled, String userName,
//			String password, String remember, String keyword, String skills)
//			throws ExceptFailTest {
//		try {
//			priceMain = PageFactory.initElements(
//					getWebDriver(Integer.parseInt(imageEnabled)),
//					PriceMain.class);
//			print("\r\nPrice page");
//			priceMain.openPage(url);
//			loginPage = priceMain.clickLogin();
//			priceMain = loginPage.login(userName, password, remember);
//			priceMain.vacancy(keyword, skills);
//			Thread.sleep(5000);
//			print("Test acomplished!");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			driver.quit();
//		}
//	}

}
