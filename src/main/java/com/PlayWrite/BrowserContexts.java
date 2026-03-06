package com.PlayWrite;

import java.nio.file.Paths;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class BrowserContexts {

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

        // Click Login only once
        Locator loginLink = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Login"));

        System.out.println("Login count: " + loginLink.count());
        
        loginLink.click();
     
        
        // Second Browser Context
        BrowserContext context2 = browser.newContext();
        Page page2 = context2.newPage();   // ✅ Correct
        page2.navigate("https://www.google.com/");
    
        
        // Stop tracing and save to trace.zip
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip"))
        );

        System.out.println("Trace saved to trace.zip");
    
    }
    
    
}