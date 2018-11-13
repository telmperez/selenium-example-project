package com.example.selenium.pages.google.forms;

import javax.validation.constraints.NotNull;

public class GoogleSearchForm {

  @NotNull
  public String searchTerms;

  public String getSearchTerms() {
    return searchTerms;
  }

  public void setSearchTerms(String searchTerms) {
    this.searchTerms = searchTerms;
  }

}
