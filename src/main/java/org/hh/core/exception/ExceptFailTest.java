package org.hh.core.exception;

/**
 * 
 * Exception for test
 * 
 * @author Alexander Bukhvostov
 *
 */
public class ExceptFailTest extends Throwable
{
	private static final long serialVersionUID = 1L;
	
	String sMessageText;
	public ExceptFailTest(String sMessage)
	{
		this.sMessageText = sMessage;
	}
	
	public String toString()
	{
		System.out.println(sMessageText);
		return "Test produce: " + sMessageText;
	}
}
