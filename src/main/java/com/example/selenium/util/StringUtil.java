package com.example.selenium.util;

public class StringUtil {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors\

  private StringUtil() {
    // Empty Constructor
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public static boolean isNotNullOrEmpty(String string) {
    return !isNullOrEmpty(string);
  }

  public static boolean isNullOrEmpty(String string) {
    return string == null || string.trim().isEmpty();
  }

  public static boolean isNotNullButEmpty(String string) {
    return string != null && string.trim().isEmpty();
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

}
