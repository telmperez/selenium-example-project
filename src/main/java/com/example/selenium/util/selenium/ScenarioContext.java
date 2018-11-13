package com.example.selenium.util.selenium;

import org.openqa.selenium.WebDriver;

import java.util.Date;

public class ScenarioContext {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private Date scenarioStartTime = new Date();
  private WebDriver webDriver;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public ScenarioContext(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

  public Date getScenarioStartTime() {
    return scenarioStartTime;
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  public void setWebDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

} // end of class