package org.hh.test;

import org.hh.core.exception.ExceptFailTest;
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

	@Test(groups = { "Test_Price" })
	@Parameters({ "url", "imageEnabled" })
	public void Test_Price(String url, String imageEnabled)
			throws ExceptFailTest {
		try {
			System.out.println(imageEnabled);
			priceMain = PageFactory.initElements(
					getWebDriver(Integer.parseInt(imageEnabled)),
					PriceMain.class);
			print("\r\nPrice page");
			priceMain.openPage(url);
			print("Test acomplished!");
		} finally {
			driver.quit();
		}
	}

}
