package com.example.selenium.pages.ocs;

import com.example.selenium.pages.BasePage;
import com.example.selenium.util.annotations.Page;
import com.example.selenium.util.selenium.SelectedOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

@Page(url = "https://ocs.ca")
public class OcsHomePage extends BasePage {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final transient Logger LOG = LoggerFactory.getLogger(OcsHomePage.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public void enterVerificationAge() {
    select(By.id("dob__day"), new SelectedOption("1", "01"));
    select(By.id("dob__month"), new SelectedOption("4", "May - 05"));
    select(By.id("dob__year"), new SelectedOption("1969", "1969"));
  }

  public void clickVerify() {
    click(By.cssSelector(".dob button"));
  }

  public void verifyAllMonthsAreInDropdown() {
    WebElement webElement = webDriver().findElement(By.id("dob__month"));
    Select select = new Select(webElement);
    List<WebElement> options = select.getOptions();
    for (WebElement option : options) {
      LOG.info(option.getText());
    }
    Assert.assertEquals(options.size(), 13);
    Assert.assertEquals(options.get(0).getText(), "Select");
    Assert.assertEquals(options.get(1).getText(), "February - 02");

  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

} // end of class