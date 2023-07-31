package excelPrograms;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CreateExcel {

	public static void main(String[] args) throws Exception {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote--allow--origins=*");
		co.addArguments("--start--maximized");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("loginData");
		// Failed Style
		XSSFCellStyle fstyle = wb.createCellStyle();
		fstyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
		fstyle.setFillPattern(FillPatternType.FINE_DOTS);
		// Pass Style
		XSSFCellStyle pstyle = wb.createCellStyle();
		pstyle.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		pstyle.setFillPattern(FillPatternType.FINE_DOTS);

		Object loginData[][] = { { "User name", "Password" }, 
								 { "Admin", "admin123" },
								 {"sireesha","admin123"},
								 {"anil","anilsireesha"},
								 {"Admin","admin123"},
		};

		int row = loginData.length;
		int col = loginData[0].length;
		// System.out.print(row+""+col);
		for (int i = 0; i < row; i++) {
			XSSFRow rows = sheet.createRow(i);
			for (int c = 0; c < col; c++) {
				XSSFCell cell = rows.createCell(c);
				Object value = loginData[i][c];
				// System.out.print(value);
				if (value instanceof String) {
					cell.setCellValue((String) value);
				}
				if (value instanceof Integer) {
					cell.setCellValue((Integer) value);
				}
				if (value instanceof Boolean) {
					cell.setCellValue((boolean) value);
				}
			}
		}
		sheet.getRow(0).createCell(col).setCellValue("Result");
		FileOutputStream fout = new FileOutputStream("./excel\\loginData.xlsx");
		wb.write(fout);

		// lanuch browser

	
		  WebDriver driver = new ChromeDriver();
		  driver.get("https://opensource-demo.orangehrmlive.com/"); 
		  Thread.sleep(3000);
		  String homePage = driver.getCurrentUrl(); 
		  for(int r=1;r<row;r++) { 
			  XSSFRow rrow = sheet.getRow(r); 
			  XSSFCell uData = rrow.getCell(0); 
			  String username = uData.getStringCellValue(); 
			  XSSFCell pData = rrow.getCell(1); 
			  String password = pData.getStringCellValue(); 
			  XSSFCell out = rrow.createCell(2);
			  //System.out.println(username+" "+pData); 
			  //login 
			  Thread.sleep(2000);
			  WebElement uName = driver.findElement(By.xpath("//input[@name='username']"));
			  WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
			  WebElement submit= driver.findElement(By.xpath("//button[@type='submit']"));
			  uName.sendKeys(username); 
			  Thread.sleep(2000); 
			  pwd.sendKeys(password);
			  Thread.sleep(2000); 
			  submit.click(); 
			  Thread.sleep(2000);
			  String newPage =  driver.getCurrentUrl(); 
			  //logout 
			  //homePage.l 
			  if(homePage.equals(newPage)) {
				  //String res =
				 // driver.findElement(By.xpath("//div[@role='alert']")).getText();
				  out.setCellValue("Failed");
				  out.setCellStyle(fstyle);
		  
			  }
			  else { 
				  Thread.sleep(2000);
				  driver.findElement(By.xpath("//li[@class='oxd-userdropdown']")).click();
				  Thread.sleep(2000);
				  driver.findElement(By.xpath("//a[text()='Logout']")).click();
				  Thread.sleep(2000);
				  out.setCellValue("Sucess"); 
				  out.setCellStyle(pstyle);
				  
			  } 
		} 
		  FileOutputStream fout1 = new FileOutputStream("./excel\\loginData.xlsx"); 
		  wb.write(fout1);
		  wb.close();
		
	}

}
