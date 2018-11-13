package com.example.selenium.util.selenium;

public class SelectedOption implements SeleniumSelect {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private String value;
  private String text;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public SelectedOption(String value, String text) {
    this.value = value;
    this.text = text;
  }

  public SelectedOption() {
    // Empty Constructor
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  @Override
  public String getOptionValue() {
    return value;
  }

  @Override
  public String getOptionText() {
    return text;
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

} // end of class