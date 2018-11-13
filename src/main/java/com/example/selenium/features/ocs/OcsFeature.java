package com.example.selenium.features.ocs;

import com.example.selenium.features.BaseFeature;
import com.example.selenium.pages.ocs.OcsHomePage;
import org.testng.annotations.Test;

@Test(description = "OCS Test")
public class OcsFeature extends BaseFeature {

  @Test(description = "Verify User Age and Enter Site")
  public void verifyUserAge() {

    OcsHomePage ocsHomePage = new OcsHomePage();
    ocsHomePage.visit();
    ocsHomePage.enterVerificationAge();
    ocsHomePage.verifyAllMonthsAreInDropdown();
    ocsHomePage.clickVerify();

//    Assert.fail();
  }

}
