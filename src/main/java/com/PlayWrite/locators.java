package com.PlayWrite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class locators {

	public static void main(String[] args) {
		 Playwright playwright = Playwright.create();

	            // Launch browser with slow motion and visible window
	            Browser browser = playwright.chromium().launch(
	                    new BrowserType.LaunchOptions()
	                            .setHeadless(false)
	                            .setSlowMo(500)
	           );
	            
	            
	           Page page=browser.newPage();
	       
	      //    page.locator("text=Book a Free Demo").first().click();
	           page.navigate("https://www.google.com/");

	           Locator demoBtn = page.locator("text=About").first();
	           demoBtn.click();
	           
	           //counting a how many About text locaotrs
	           for (int i = 0; i < demoBtn.count(); i++) {
	        	    String ss = demoBtn.nth(i).textContent();
	        	    System.out.println(ss);
	        	}
	           
	        // Go back to the previous page
	           page.goBack();
	       
	}

}
