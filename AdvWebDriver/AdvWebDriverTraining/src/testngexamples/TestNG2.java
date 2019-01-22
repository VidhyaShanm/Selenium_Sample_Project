package testngexamples;

import org.testng.annotations.Test;

public class TestNG2
{
	
	@Test(priority=123)
	public void t1()
	{
		System.out.println("t1...");
	}
	
	@Test(priority=15)
	public void t2()
	{
		System.out.println("t2...");
	}
	
	@Test(priority=-10)
	public void t3()
	{
		System.out.println("t3...");
		
	}
	
	@Test
	public void a4()
	{
		System.out.println("a4...");
	}

}
