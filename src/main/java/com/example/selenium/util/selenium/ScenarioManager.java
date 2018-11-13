package com.example.selenium.util.selenium;

import org.openqa.selenium.WebDriver;

public class ScenarioManager {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final ThreadLocal<ScenarioContext> THREAD_LOCAL = new ThreadLocal();

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public static void startScenario(WebDriver webDriver) {
    THREAD_LOCAL.set(new ScenarioContext(webDriver));
  }

  public static WebDriver webDriver() {
    ScenarioContext scenarioContext = THREAD_LOCAL.get();
    if (scenarioContext != null) {
      return scenarioContext.getWebDriver();
    }
    return null;
  }

  public static void endScenario() {
    THREAD_LOCAL.remove();
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

}
