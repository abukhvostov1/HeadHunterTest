package org.hh.core.pages;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.hh.core.exception.ExceptFailTest;
import org.hh.core.utils.Proper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Abstract page
 * 
 * Implemented total functions and procedure. Also manage web driver working
 * 
 * @author Alexander Bukhvostov
 * 
 */

public abstract class AbstractPage {
	protected WebDriver driver;
	protected int nTimeWait;
	protected String url;

	/**
	 * Gets web driver
	 * 
	 * TODO: need to check IE, Chrome, Opera; if need
	 * 
	 * @param driver
	 */
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		nTimeWait = Integer.parseInt(Proper.getProperty("nTimeWait"));
		this.driver.manage().timeouts()
				.implicitlyWait(nTimeWait, TimeUnit.SECONDS);
	}

	/**
	 * To close web driver
	 * 
	 * TODO: Sometimes can't work, reason is some problems with full page
	 * loading, need to investigate
	 */
	public void closeDriver() {
		driver.close();
	}

	/**
	 * Open implemented page
	 * 
	 * @param sUrl
	 */
	public abstract void openPage(String sUrl);

	/**
	 * Check presence of elements by different parameters
	 * 
	 * @param nKey
	 * @param sLocator
	 * @throws ExceptFailTest
	 */
	protected void checkElementPresent(final int nKey, final String sLocator)
			throws ExceptFailTest {
		WebElement wElement = null;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, nTimeWait);
		try {
			wElement = wWaitDriver.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver wd) {
					WebElement wEl = null;
					switch (nKey) {
					case 1:
						wEl = wd.findElement(By.xpath(sLocator));
						break;
					case 2:
						wEl = wd.findElement(By.id(sLocator));
						break;
					case 3:
						wEl = wd.findElement(By.name(sLocator));
						break;
					case 4:
						wEl = wd.findElement(By.className(sLocator));
						break;
					case 5:
						wEl = wd.findElement(By.linkText(sLocator));
						break;
					case 6:
						wEl = wd.findElement(By.cssSelector(sLocator));
						break;
					}
					return wEl;
				}
			});
		} catch (TimeoutException exc) {
			System.out.println("TimeOut element for " + sLocator);
		}
		if (wElement == null) {
			throw new ExceptFailTest("Element for " + sLocator + " not found");
		}
	}

	/**
	 * Makes double click
	 * 
	 * @param wElement
	 */
	protected void doubleClickElement(WebElement wElement) {
		Actions builder;
		Action dClick;
		builder = new Actions(driver);
		builder.doubleClick(wElement);
		dClick = builder.build();
		dClick.perform();
	}

	/**
	 * I hope u understand that's it do...
	 * 
	 * @param wElement
	 */
	protected void clickElement(WebElement wElement) {
		Actions builder;
		Action cClick;
		builder = new Actions(driver);
		builder.click(wElement);
		cClick = builder.build();
		cClick.perform();
	}

	/**
	 * Simply, key stroke
	 * 
	 * @param wElement
	 * @param key
	 * @param n
	 */
	protected void keyPress(WebElement wElement, Keys key, int n) {
		while (n > 0) {
			wElement.sendKeys(key);
			n--;
		}
	}

	/**
	 * Important method to send data to web element!
	 * 
	 * @param wElement
	 * @param sText
	 */
	protected void sendText(WebElement wElement, String sText) {
		wElement.clear();
		wElement.sendKeys(sText);
	}

	/**
	 * I don't know why I do it, may be that voice of selenium one....
	 * 
	 * TODO: Just some thoughts....
	 * 
	 * @param wElement
	 */
	@SuppressWarnings("deprecation")
	protected void scrollToElement(WebElement wElement) {
		((Locatable) wElement).getLocationOnScreenOnceScrolledIntoView();
	}

	/**
	 * Capture screen
	 * 
	 * TODO: sometimes can't works, why.....
	 * 
	 * @param sName
	 */
	protected void captureScreenshot(String sName) {
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(sName + ".png"));
		} catch (IOException e) {
			print("Can't save screenshot");
		}
	}

	/**
	 * Check text into element
	 * 
	 * TODO: need to investigate Sleep for that, because of have problems in
	 * text location
	 * 
	 * @param wElement
	 * @param sText
	 * @throws ExceptFailTest
	 */
	protected void checkTextForElement(WebElement wElement, String sText)
			throws ExceptFailTest {
		try {
			if (wElement.getTagName().equals("input"))
				Assert.assertTrue(wElement.getAttribute("value").equals(sText));
			else
				Assert.assertTrue(wElement.getText().equals(sText));
		} catch (AssertionError exc) {
			throw new ExceptFailTest("Can't find text " + sText);
		}
	}

	/**
	 * Implemented by default, main goal of core to use Xpath. Should be
	 * removed: Experimental method
	 * 
	 * @param wElement
	 * @param sPropertyName
	 * @param sExcpextedCssValue
	 * @throws ExceptFailTest
	 */
	@Deprecated
	protected void checkCssElement(WebElement wElement, String sPropertyName,
			String sExcpextedCssValue) throws ExceptFailTest {
		try {
			Assert.assertTrue(wElement.getCssValue(sPropertyName).equals(
					sExcpextedCssValue));
		} catch (AssertionError exc) {
			throw new ExceptFailTest("Can't find parameter " + sPropertyName
					+ " for given element");
		}
	}

	/**
	 * Check element attributes
	 * 
	 * @param wElement
	 * @param sAtributeName
	 * @param sExcpextedAtributeValue
	 * @throws ExceptFailTest
	 */
	protected void checksAtributeElement(final WebElement wElement,
			final String sAtributeName, final String sExcpextedAtributeValue)
			throws ExceptFailTest {
		boolean bFlag = false;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, nTimeWait);
		try {
			bFlag = wWaitDriver.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver wd) {
					String s = wElement.getAttribute(sAtributeName);
					return s.equals(sExcpextedAtributeValue);
				}
			});
		} catch (TimeoutException exc) {
			System.out.println("Element with" + sExcpextedAtributeValue
					+ " not found");
		}

		if (!bFlag) {
			throw new ExceptFailTest("Can't find attribute " + sAtributeName);
		}
	}

	/**
	 * Check element property enable
	 * 
	 * @param wElement
	 * @param nOperation
	 * @throws ExceptFailTest
	 */
	protected void checkEnableElement(WebElement wElement, int nOperation)
			throws ExceptFailTest {
		if (!wElement.isEnabled()) {
			print("Can't get element \"" + wElement.getText() + "\"");
			throw new ExceptFailTest("Can't get element \""
					+ wElement.getText() + "\"");
		} else {
			print("Element \"" + wElement.getText() + "\" can be accessed");
			throw new ExceptFailTest("Element \"" + wElement.getText()
					+ "\" can be accessed");
		}
	}

	/**
	 * Check visibility of element
	 * 
	 * @param sLocator
	 * @param sName
	 * @throws ExceptFailTest
	 */
	protected void checkNotVisibleElement(String sLocator, String sName)
			throws ExceptFailTest {
		WebDriverWait wait = new WebDriverWait(driver, nTimeWait);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.xpath(sLocator)));
		} catch (TimeoutException exc) {
			print("Element" + sName + " visible.");
			throw new ExceptFailTest("Element" + sName + " visible.");
		}
	}

	/**
	 * The same as before. Had some thoughts... Experimental
	 * 
	 * @param wElement
	 * @param sName
	 * @throws ExceptFailTest
	 */
	@Deprecated
	protected void checkVisibleElement(WebElement wElement, String sName)
			throws ExceptFailTest {
		WebDriverWait wait = new WebDriverWait(driver, nTimeWait);
		try {
			wait.until(ExpectedConditions.visibilityOf(wElement));
		} catch (TimeoutException exc) {
			print("Element" + sName + " not visible.");
			throw new ExceptFailTest("Element" + sName + " not visible.");
		}
	}

	/**
	 * Returns all elements by locator
	 * 
	 * @param sLocator
	 * @return
	 * @throws ExceptFailTest
	 */
	protected WebElement[] getAllWebElements(final String sLocator)
			throws ExceptFailTest {

		WebElement wTemp[];
		ArrayList<WebElement> list;
		Iterator<WebElement> it;
		int i = 0;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, nTimeWait);

		try {
			list = wWaitDriver
					.until(new ExpectedCondition<ArrayList<WebElement>>() {
						public ArrayList<WebElement> apply(WebDriver wd) {
							return (ArrayList<WebElement>) wd.findElements(By
									.xpath(sLocator));
						}
					});
		} catch (TimeoutException exc) {
			System.out.println("Element with such locator can't be found "
					+ sLocator);
			throw new ExceptFailTest(
					"Element with such locator can't be found " + sLocator);
		}

		wTemp = new WebElement[list.size()];
		it = list.iterator();

		while (it.hasNext()) {
			wTemp[i] = it.next();
			i++;
		}
		return wTemp;
	}

	/**
	 * Check selection of element
	 * 
	 * @param wElement
	 * @param bResult
	 * @param sName
	 * @throws ExceptFailTest
	 */
	protected void checkIsSelectedElement(final WebElement wElement,
			final Boolean bResult, String sName) throws ExceptFailTest {
		WebDriverWait wait = new WebDriverWait(driver, nTimeWait);
		try {
			wait.until(ExpectedConditions.elementSelectionStateToBe(wElement,
					bResult));
		} catch (TimeoutException exc) {
			String s = (bResult == true) ? " selected" : " not selected";
			print("Element " + sName + s);
			throw new ExceptFailTest("Element " + sName + s);
		}
	}

	/**
	 * Check element presence by xpath
	 * 
	 * @param sLocator
	 * @param sName
	 * @return
	 */
	protected boolean checkElement(final String sLocator, String sName) {
		WebElement wElement = null;
		WebDriverWait wWaitDriver = new WebDriverWait(driver, 2);
		try {
			wElement = wWaitDriver.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver wd) {
					return wd.findElement(By.xpath(sLocator));
				}
			});
		} catch (TimeoutException exc) {
			System.out.println("Can't found element " + sName);
		}
		if (wElement == null)
			return false;
		else
			return true;
	}

	/**
	 * Write any object
	 * 
	 * @param obj
	 */
	public <T> void print(T obj) {
		PrintWriter pw = new PrintWriter(System.out, true);
		pw.println(obj);
	}

	/**
	 * Stop thread for some milliseconds
	 * 
	 * @param i
	 */
	public void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * For the future
	 * 
	 * @param nLimit
	 * @return
	 */
	protected int getRandomNumber(int nLimit) {
		Random r = new Random();
		return r.nextInt(nLimit);
	}
}
