package com.example.selenium.pages;

import com.example.selenium.util.RandomUtil;
import com.example.selenium.util.selenium.ScenarioManager;
import com.example.selenium.util.selenium.SeleniumSelect;
import com.example.selenium.util.selenium.SelectedOption;
import com.example.selenium.util.annotations.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final transient Logger LOG = LoggerFactory.getLogger(BasePage.class);

  private static final int DEFAULT_ELEMENT_PAUSE_TIME = 1000;

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public void visit() {
    String url = page().url();
    visitURL(url);
  }

  public Page page() {
    return this.getClass().getAnnotation(Page.class);
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  protected String textFromElement(By by) {
    List<WebElement> elements = webDriver().findElements(by);
    if (elements.size() == 1) {
      return elements.get(0).getText();
    } else {
      LOG.error("More than 1 element found");
      return null;
    }
  }

  protected void checkbox(WebElement webElement, boolean selected) {
    if ((selected && !webElement.isSelected()) || (!selected && webElement.isSelected())) {
      LOG.info("Clicking Checkbox {} setting to selected [{}]", prettyPrintElement(webElement), selected);
      clickWebElementWithRetry(webElement);
      pause(DEFAULT_ELEMENT_PAUSE_TIME);

    } else {
      LOG.info("Checkbox {} not clicked, already selected [{}]", prettyPrintElement(webElement), selected);
    }
  }

  // ########################## Send keys ##########################

  protected void sendKeys(WebElement webElement, String keys, boolean clearExistingText) {
    for (int i = 0; i < 3; i++) {
      try {
        if (!webElement.isDisplayed()) {
          LOG.warn("Unable to send keys [{}] to {} since element is hidden on [{}]", keys, prettyPrintElement(webElement), pageClassName());
          return;
        }
        LOG.info("Sending keys [{}] to {} on [{}]", keys, prettyPrintElement(webElement), pageClassName());
        if (clearExistingText) {
          webElement.clear();
        }
        webElement.sendKeys(keys);
        break;
      } catch (Exception e) {
        if (i == 2) {
          throw e;
        } else {
          LOG.info(e.getMessage());
          pause(200);
        }
      }
    }
    pause(DEFAULT_ELEMENT_PAUSE_TIME);
  }

  protected void sendKeys(By by, String keys, boolean clearExistingText) {
    List<WebElement> elements = webDriver().findElements(by);
    for (WebElement webElement : elements) {
      sendKeys(webElement, keys, clearExistingText);
    }
  }

  protected void sendKeys(By by, String keys) {
    List<WebElement> elements = webDriver().findElements(by);
    for (WebElement webElement : elements) {
      sendKeys(webElement, keys, false);
    }
  }

  protected void click(WebElement webElement) {
    LOG.info("Clicking {} on [{}]", prettyPrintElement(webElement), pageClassName());
    clickWebElementWithRetry(webElement);
    pause(DEFAULT_ELEMENT_PAUSE_TIME);
  }

  protected SelectedOption selectRandomOption(WebElement webElement) {
    Select dropdown = new Select(webElement);
    List<WebElement> options = dropdown.getOptions();

    // Select a random option excluding the first option
    int randomValue = RandomUtil.randInt(1, options.size() - 1);
    WebElement optionWebElement = options.get(randomValue);
    SelectedOption selectedOption = new SelectedOption();
    selectedOption.setText(optionWebElement.getText());
    selectedOption.setValue(optionWebElement.getAttribute("value"));
    LOG.info("Setting {} with value option[value={}][text={}]", prettyPrintElement(webElement), selectedOption.getValue(), selectedOption.getText());

    // Select the element
    dropdown.selectByValue(selectedOption.getValue());
    pause(DEFAULT_ELEMENT_PAUSE_TIME);

    return selectedOption;
  }

  protected SelectedOption selectRandomOption(By by) {
    return selectRandomOption(webDriver().findElement(by));
  }

  protected void select(By by, SeleniumSelect seleniumSelect) {
    select(webDriver().findElement(by), seleniumSelect);
  }

  protected void select(WebElement webElement, SeleniumSelect seleniumSelect) {
    Select dropdown = new Select(webElement);
    LOG.info("Setting {} with value [{}][{}]", prettyPrintElement(webElement), seleniumSelect.getOptionValue(), seleniumSelect.getOptionText());

    dropdown.selectByValue(seleniumSelect.getOptionValue());
    pause(DEFAULT_ELEMENT_PAUSE_TIME);
  }

  protected void scrollToElement(WebElement webElement) {
    ((JavascriptExecutor) webDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
    ((JavascriptExecutor) webDriver()).executeScript("window.scrollBy(0, -150);");
    pause(500);
  }

  protected void click(By by) {
    List<WebElement> elements = webDriver().findElements(by);
    for (WebElement element : elements) {
      if (element.isDisplayed()) {
        click(element);
      }
    }
  }

  protected void checkbox(By by, boolean selected) {
    List<WebElement> elements = webDriver().findElements(by);
    for (WebElement element : elements) {
      checkbox(element, selected);
    }
  }

  protected <T extends BasePage> T createPage(Class<T> clazz) {
    try {
      pause(500);
      T page = clazz.newInstance();
      return page;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void pause(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  protected WebDriver webDriver() {
    return ScenarioManager.webDriver();
  }

  protected void waitForPageToLoad() {
    webDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  protected void deleteCookies() {
    LOG.info("Clearing all browser cookies.");
    webDriver().manage().deleteAllCookies();
  }

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  private void clickWebElementWithRetry(WebElement webElement) {
    for (int i = 0; i < 3; i++) {
      try {
        webElement.click();
        break;
      } catch (Exception e) {
        if (i == 2) {
          throw e;
        } else {
          LOG.info("Retrying Element");
          pause(200);
        }
      }
    }
  }

  private void visitURL(String url) {
    pause(1000);
    webDriver().get(url);
  }

  private void addAttributeToMap(String name, WebElement webElement, Map<String, String> attributeMap) {
    String value = webElement.getAttribute(name);
    if (value != null && !value.isEmpty()) {
      attributeMap.put(name, value);
    }
  }

  private String prettyPrintElement(WebElement webElement) {
    StringBuilder sb = new StringBuilder();
    sb.append(webElement.getTagName());
    Map<String, String> attributeMap = new HashMap<>();
    addAttributeToMap("id", webElement, attributeMap);
    if (attributeMap.isEmpty()) {
      addAttributeToMap("class", webElement, attributeMap);
    }
    if (!attributeMap.isEmpty()) {
      for (Map.Entry<String, String> entry : attributeMap.entrySet()) {
        sb.append("[");
        sb.append(entry.getKey());
        sb.append("=");
        sb.append(entry.getValue());
        sb.append("]");
      }
    }
    return sb.toString();
  }

  private String pageClassName() {
    return this.getClass().getSimpleName();
  }

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

} // end of class
