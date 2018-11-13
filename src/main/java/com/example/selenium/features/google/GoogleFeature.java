package com.example.selenium.features.google;

import com.example.selenium.features.BaseFeature;
import com.example.selenium.pages.google.GoogleHomePage;
import com.example.selenium.pages.google.forms.GoogleSearchForm;
import com.example.selenium.util.PodamUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(description = "Google Example Test")
public class GoogleFeature extends BaseFeature {

  @Test(description = "Perform Google Search")
  public void exampleScenario() {

    GoogleHomePage googleHomePage = new GoogleHomePage();
    googleHomePage.visit();

    GoogleSearchForm googleSearchForm = PodamUtil.manufacture(GoogleSearchForm.class);
    googleHomePage.search(googleSearchForm);

    Assert.fail();

  }

}
