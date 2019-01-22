package testngexamples;

import org.testng.annotations.Test;

public class TestNG3
{
	
	@Test(enabled=false)//In Testng to disable
	public void t1()
	{
		System.out.println("t1...");
	}
	
	@Test(invocationCount=10)
	public void t2()
	{
		System.out.println("t2...");
	}
	
	@Test
	public void t3()
	{
		System.out.println("t3...");
		
	}
	
	@Test
	public void t4()
	{
		System.out.println("t4...");
	}

}