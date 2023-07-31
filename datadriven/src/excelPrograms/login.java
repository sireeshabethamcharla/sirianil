package excelPrograms;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class login {

	public static void main(String[] args) throws Exception {
		//launch broswer
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--start-maximized");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		File file = new File("excel\\myexcel.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("tdata");
		XSSFRow row = sheet.getRow(0);
		XSSFCell ucell = row.getCell(0);
		XSSFCell pcell = row.getCell(1);
		String userName = ucell.getStringCellValue();
		String password = pcell.getStringCellValue();
		Thread.sleep(3000);
		//webElement 
		WebElement user = driver.findElement(By.xpath("//input[@name='username']"));
		WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement login = driver.findElement(By.xpath("//button[@type='submit']"));
		
		user.sendKeys(userName);
		Thread.sleep(1000);
		pwd.sendKeys(password);
		Thread.sleep(1000);
		login.click();
		
		
		
		
		
		

	}

}
