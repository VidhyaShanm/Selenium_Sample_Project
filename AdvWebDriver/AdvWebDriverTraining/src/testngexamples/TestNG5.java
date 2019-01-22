package testngexamples;

import org.testng.Assert;
import org.testng.annotations.Test;


//Use of assertions
public class TestNG5
{
	
	@Test
	public void t1()
	{
		System.out.println("t1...");
		Assert.assertEquals("WebDriver", "WebDriver");//(Actual,expected)
	}
	
	@Test(dependsOnMethods="t1")
	public void t2()
	{
		System.out.println("t2...");
		Assert.assertEquals(10.0, 10.00001);//(Actual,expected)
	}
	
	@Test(dependsOnMethods="t2")
	public void t3()
	{
		System.out.println("t3...");
		Assert.assertTrue(10>20, "Invalid expression.... 10>20");//(Actual,expected)
		
	}
	
	@Test(dependsOnMethods="t3")
	public void t4()
	{
		System.out.println("t4...");
		Assert.fail("Forced this method to fail");
	}

}
