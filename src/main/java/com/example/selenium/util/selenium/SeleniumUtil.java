package com.example.selenium.util.selenium;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SeleniumUtil {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final transient Logger LOG = LoggerFactory.getLogger(SeleniumUtil.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public static WebDriver createWebDriver() {
    try {

      if (SystemUtils.IS_OS_WINDOWS) {
        // TODO: put in location of google executable
      }

      WebDriver driver = new ChromeDriver();

      // Set Window Options
      driver.manage().window().setSize(new Dimension(1400, 900));

      // Set Timeouts Options
      WebDriver.Timeouts timeouts = driver.manage().timeouts();
      timeouts.pageLoadTimeout(60, TimeUnit.SECONDS);
      timeouts.implicitlyWait(5, TimeUnit.SECONDS);
      timeouts.setScriptTimeout(30, TimeUnit.SECONDS);

      return driver;
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
    return null;
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

} // end of class