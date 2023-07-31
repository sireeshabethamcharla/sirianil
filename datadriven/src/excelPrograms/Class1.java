package excelPrograms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Class1 {

	public static void main(String[] args) throws IOException, InterruptedException {

		File file = new File("excel\\myexcel.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("sheet1");

		XSSFRow row = sheet.getRow(0);
		XSSFCell usernamecell = row.getCell(0);
		XSSFCell pwdcell = row.getCell(1);
		String uid = usernamecell.getStringCellValue();
		String pwd = pwdcell.getStringCellValue();

		// browserlaunch
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--start-maximized");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(3000);
		JavascriptExecutor je = (JavascriptExecutor)driver;
		

		// functionalities mantion

		// uid
		WebElement uiddata = driver.findElement(By.xpath("//*[@name='username']"));
		uiddata.sendKeys(uid);
		je.executeScript("arguments[0].style.border='4px solid red'",uiddata );
		Thread.sleep(3000);
		
		String getuiddata=uiddata.getAttribute("value");
		System.out.println("my uid is :"+getuiddata);
		
	
		
		//PWD
		WebElement pwddata= driver.findElement(By.xpath("(//*[contains(@type,'p')])[1]"));
		pwddata.sendKeys(pwd);
		je.executeScript("arguments[0].style.border='4px solid green'" ,pwddata );
		
		String getpwddata=pwddata.getAttribute("value");
		System.out.println("my pwd is :"+getpwddata);
		
		
		Thread.sleep(3000);
		//login
		WebElement logindata= driver.findElement(By.xpath("//button[@type='submit']"));
	
		String getlogin=logindata.getAttribute("type");
		System.out.println("my login  is :"+getlogin);
		logindata.click();
		
		
		Thread.sleep(3000);
		workbook.close();
		System.out.println("my excel data succesfully done");
		
		driver.close();
		

	}

}
