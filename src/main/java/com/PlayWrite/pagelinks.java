package com.PlayWrite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;

public class pagelinks {

	public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );
        // Start tracing
        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        );


        BrowserContext context1 = browser.newContext();
   

        // Click on Login link
        Page page = context.newPage();
        page.navigate("https://academy.naveenautomationlabs.com/");


        //handle single web element
        Locator loginLink = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Login"));

        System.out.println("Login count: " + loginLink.count());

        loginLink.click();
   
        // Count all links on the page
        Locator allLinks = page.locator("a");

        int linkCount = allLinks.count();
        System.out.println("Total links on page: " + linkCount);

        // Print all links text + URL
        for (int i = 0; i < linkCount; i++) {

            Locator link = allLinks.nth(i);

            String text = link.textContent();
            String href = link.getAttribute("href");

            //hanlde multile web element
            Locator countryOptions=page.locator("div[role='button']");
            
            System.out.println(countryOptions.count());
            /* 
           // 1
            for(int i=0; i<countryOptions.count();i++) {
            	String text=countryOptions.nth(i).textContent();
            	System.out.println(text);      
            	}
            
         
            //2
            List<String> optionsTextList=countryOptions.allTextContents();
            for(String e: optionsTextList) {
            	System.out.println(e);
            }
            //3
            List<String> optionsTextList1=countryOptions.allTextContents();
            for(String a: optionsTextList1)
            	System.out.println(a);
            */

            System.out.println("Link " + (i + 1));
            System.out.println("Text: " + text);
            System.out.println("URL : " + href);
            System.out.println("-------------------------");
        }
	}}
