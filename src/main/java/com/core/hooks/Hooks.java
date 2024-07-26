package com.core.hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Hooks {
    public static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    public static ThreadLocal<Browser> browser = new ThreadLocal<>();
    public static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    public static ThreadLocal<Page> page = new ThreadLocal<>();
    private static final AtomicInteger GLOBAL_ITERATOR = new AtomicInteger(0);
    private static final List<String> BROWSERS = Arrays.asList("Chromium", "Firefox", "Webkit");

    private boolean isApiTest(Scenario scenario) {
        return scenario.getSourceTagNames().contains("@API");
    }


    @Before
    public void setUp(Scenario scenario) {
        if (!isApiTest(scenario)) {
            String browserType;

            if (System.getProperty("crossBrowserActivation") != null && Boolean.parseBoolean(System.getProperty("crossBrowserActivation"))){
                int currentIndex = GLOBAL_ITERATOR.getAndIncrement() % BROWSERS.size();
                System.out.println("Setting up browser for scenario: " + scenario.getName());
                browserType = BROWSERS.get(currentIndex);
                System.out.println("Browser type: " + browserType);

                if (playwright.get() == null) {
                    playwright.set(Playwright.create());
                }

                if (browser.get() == null) {
                    browser.set(createPlaywrightBrowserInstance(browserType));
                }
            } else {
                if (playwright.get() == null) {
                    playwright.set(Playwright.create());
                }

                if (browser.get() == null) {
                    browserType = System.getProperty("browser");
                    browser.set(createPlaywrightBrowserInstance(browserType));
                }
            }

            context.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)));
            page.set(context.get().newPage());
            page.get().setDefaultTimeout(15000);
            System.out.println("Browser setup complete. Page object created: " + (page.get() != null));
        } else {
            System.out.println("API test detected. Skipping browser setup.");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (!isApiTest(scenario)) {
            if (scenario.isFailed()) {
                byte[] screenshot = page.get().screenshot();
                scenario.attach(screenshot, "image/png", "screenshot");
            }
            if (page.get() != null) {
                page.get().close();
            }
            if (context.get() != null) {
                context.get().close();
            }
        }
    }

    @AfterAll
    public static void tearDownAll() {
        if (browser.get() != null) {
            browser.get().close();
        }
        if (playwright.get() != null) {
            playwright.get().close();
        }
    }

    public static Browser createPlaywrightBrowserInstance(String browserTypeAsString) {
        Hooks.playwright.get();

        BrowserType browserType;
        switch (browserTypeAsString.toLowerCase()) {
            case "firefox":
                browserType = playwright.get().firefox();
                break;
            case "webkit":
                browserType = playwright.get().webkit();
                break;
            case "chrome":
            case "chromium":
            default:
                browserType = playwright.get().chromium();
                break;
        }

        Browser browser;
        if (System.getProperty("executionMode").equalsIgnoreCase("Local")) {
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else {
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
        }

        return browser;
    }
}