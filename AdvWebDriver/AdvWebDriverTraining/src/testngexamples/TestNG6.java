package testngexamples;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestNG6 {
	
	@Test(dataProvider="logindp")
	public void login(String uid,String pwd)
	{
		System.out.println(uid + "\t" + pwd);
	}
	
	@DataProvider(name="logindp")
	public Object[][] getData()
	{
	Object[][] data = { 
		                 	{"u1","p1"},
			                {"u2","p2"},
			                {"u3","p3"}
	                  };
	
	return data;
	}
	
}