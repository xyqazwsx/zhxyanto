package com.test.common;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


@Listeners({ActionListener.class})
public class NewTest {
	
	
	protected static WebDriver driver;
	/*
	 * 执行套件
	 */
	@BeforeSuite
	public void beforeSuite_runOnchrome() {
	   
	    try {

	    	 driver = new ChromeDriver();
			 Thread.sleep(2000);
			 driver.manage().window().maximize(); 
			 
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	/*
	 * 执行用例前执行
	 */	
	@Parameters({"url"})
	@BeforeClass
	public void beforeClass(String url) {
		
	    driver.get(url);
	    Set<Cookie> cool=driver.manage().getCookies();
	    System.out.println(cool);
	    
	    driver.manage().deleteAllCookies();
	    
	}
	/*
	 * 初始化两个账户
	 */
	@AfterMethod
	@Parameters({"username","password"})
	 public void postparames(String username,String password) {
		 
			
			try {
				
				WebElement username1=driver.findElement(By.id("UserName"));
				username1.sendKeys(username);
				WebElement password1=driver.findElement(By.id("Password"));
				password1.sendKeys(password);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
	 }
	
	public void login(){
		 
		  
	     try {
	    	 
			 Thread.sleep(2000);
			 driver.findElement(By.xpath("//section/div/section/section[2]/section[1]/form/div[4]/div/div/div[2]")).click(); 
		     Actions action = new Actions(driver);   
		     WebElement source = driver.findElement(By.xpath("//section/div/section/section[2]/section[1]/form/div[4]/div/div/div[2]"));  
		     action.clickAndHold(source).moveByOffset(600, 0).release(); 
		     Action actions = action.build();  
		     actions.perform();
		     WebElement buttion=driver.findElement(By.id("btnFormSubmit"));
			 buttion.click();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	 
		 
	 }

	  
	  @Parameters({"username","password"})
	  @Test
	  public void testlogin(String username,String password) {
		  try {
			 
		    this.postparames(username, password);
			this.login();
	    
	    }catch(Exception e1){  
	    	
	    	e1.printStackTrace();
	    }  
		  
		  
		
	  }
		 
	  @AfterSuite
	  public void afterSuite() {
		
		   
	      try {
	    	  
			Thread.sleep(3000);
			
			driver.close();
			SendMail smail=new SendMail();
			smail.main(null);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	      
	     
	  }
	 
	  
	  
	   
	
}
