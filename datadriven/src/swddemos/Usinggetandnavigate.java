package swddemos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Usinggetandnavigate 
{

	public static void main(String[] args)
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com");
		driver.navigate().to("https://amazon.in");
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.close();


	}

}
