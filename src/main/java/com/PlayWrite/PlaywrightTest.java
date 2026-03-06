package com.PlayWrite;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;

public class PlaywrightTest {
	   public static void main(String[] args) {
		try(   Playwright playwright = Playwright.create()){

	            // Launch browser with slow motion and visible window
	            Browser browser = playwright.chromium().launch(
	                    new BrowserType.LaunchOptions()
	                            .setHeadless(false)
	                            .setSlowMo(500)
	            );

	            BrowserContext context = browser.newContext();

	            // Start tracing
	            context.tracing().start(new Tracing.StartOptions()
	                    .setScreenshots(true)
	                    .setSnapshots(true)
	                    .setSources(true)
	            );

	            // Open a new page
	            Page page = context.newPage();

	            // Navigate to the website
	            page.navigate(
	                    "https://academy.naveenautomationlabs.com/",
	                    new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
	            );

	            System.out.println("Title: " + page.title());

	            // Click on Login link
	            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();

	            // Use FrameLocator to interact inside iframe
	            FrameLocator loginFrame = page.frameLocator("#microfe-popup-login");

	            // Fill Name
	            loginFrame.getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Name")).fill("sanket");

	            // Fill Email
	            loginFrame.getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Email address")).fill("demo1@gmail.com");

	            // Fill Password
	            loginFrame.getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Password")).fill("Pass@123");

	            // Select country code
	            loginFrame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("India: +")).click();
	            loginFrame.getByText("+91").click();

	            // Enter phone number
	            loginFrame.getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Enter your number")).fill("+91 1234567891");

	            // Optional: click Next button if exists
	            // loginFrame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Next")).click();

	            // Stop tracing and save to trace.zip
	            context.tracing().stop(new Tracing.StopOptions()
	                    .setPath(Paths.get("trace.zip"))
	            );

	            System.out.println("Trace saved to trace.zip");
	        }
		
		
	    }
	}