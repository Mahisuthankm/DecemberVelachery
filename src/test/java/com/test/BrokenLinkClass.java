package com.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkClass {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\mahisuthan\\eclipse-workspace\\BrokenLink\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
		
		//to find all the links
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		
		//Iterate the list to get individual webelement
		for (int i = 0; i < allLinks.size(); i++) {
			//to get individual Link
			WebElement individualLink = allLinks.get(i);
			
			String attribute = individualLink.getAttribute("href");
			
			//convert a string to URL
			URL u = new URL (attribute);
			
			//open the connection
			URLConnection openConnection = u.openConnection();
			
			//HttpURL connection -  to get response code (Type cast)
			HttpURLConnection httpURLConnection =  (HttpURLConnection)openConnection;
			
			//get response code
			int responseCode = httpURLConnection.getResponseCode();
			
			if (responseCode >=200 && responseCode<=299) {
				System.out.println("Valid Link "+ attribute);
			}
			else if (responseCode >=300 && responseCode<=399) {
				System.out.println("Redirectional Link "+ attribute);
			}
			else if (responseCode >=400 && responseCode<=499) {
				System.out.println("Broken Link "+ attribute);
			}
			else {
				System.out.println("Broken Link "+ attribute);
			}
			
			
		}
		System.out.println("Hi am a tester");
		System.out.println("Am a QA");
	}

}
