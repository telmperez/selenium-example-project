package com.example.selenium.features;

import com.example.selenium.util.selenium.ScenarioManager;
import com.example.selenium.util.selenium.SeleniumUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public abstract class BaseFeature {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final transient Logger LOG = LoggerFactory.getLogger(BaseFeature.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  @BeforeMethod(alwaysRun = true)
  public void beforeScenario(Method method) {
    LOG.info("Before Scenario [{}] - {}", method.getAnnotation(Test.class).description());

    // Reset scenario (just in case)
    ScenarioManager.endScenario();

    // Start the scenario by initializing selenium
    WebDriver webDriver = SeleniumUtil.createWebDriver();
    if (webDriver == null) {
      Assert.fail("web driver was null, test is a failure");
    }
    ScenarioManager.startScenario(webDriver);
  }

  @AfterMethod(alwaysRun = true)
  public void afterScenario(ITestResult result, Method method) {
    LOG.info("After Scenario [{}] - {}", method.getAnnotation(Test.class).description());

    WebDriver webDriver = ScenarioManager.webDriver();

    // Close the web driver
    webDriverCleanup(result, webDriver);

    // End the scenario, reset the thread local
    ScenarioManager.endScenario();
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  private void webDriverCleanup(ITestResult result, WebDriver webDriver) {
    if (webDriver == null) {
      LOG.warn("Not cleaning up, webDriver was null");
      return;
    }

    // Leave browser window open on failure
    if (result.isSuccess()) {
      webDriver.quit();
    } else {
      LOG.info(webDriver.getPageSource());
    }
  }

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

} // end of class
