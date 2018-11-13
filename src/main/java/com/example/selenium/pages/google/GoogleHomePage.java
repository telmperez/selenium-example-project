package com.example.selenium.pages.google;

import com.example.selenium.pages.BasePage;
import com.example.selenium.pages.google.forms.GoogleSearchForm;
import com.example.selenium.util.annotations.Page;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Page(url = "https://www.google.ca")
public class GoogleHomePage extends BasePage {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final transient Logger LOG = LoggerFactory.getLogger(GoogleHomePage.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  public void search(GoogleSearchForm googleSearchForm) {
    sendKeys(By.id("lst-ib"), googleSearchForm.getSearchTerms());
    LOG.info(webDriver().getPageSource());
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

}