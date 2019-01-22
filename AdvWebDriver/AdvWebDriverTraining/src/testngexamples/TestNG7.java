package testngexamples;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TestNG7 {
	
	@Test
	public void t1()
	{
		System.out.println("t1...");
	}
	@Test
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
		@BeforeMethod
		public void methodSetup()
		{
		System.out.println("methodSetup...");	
		}
		
		@AfterMethod
		public void methodTearDown()
		{
			System.out.println("methodTearDown...");
			
		}
		
		@BeforeClass
		public void ClassSetup()
		{
		System.out.println("classSetup...");	
		}
		
		@AfterClass
		public void classTearDown()
		{
			System.out.println("classTearDown...");
			
		}
			
	

}