package com.example.selenium.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.lang.reflect.Type;

public class PodamUtil {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final transient Logger LOG = LoggerFactory.getLogger(PodamUtil.class);

  private static PodamFactory podamFactory = new PodamFactoryImpl();

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public static <T> T manufacture(Class<T> classType) {
    return podamFactory.manufacturePojo(classType);
  }

  public static <T> T manufacture(Class<T> classType, Type... genericTypes) {
    return podamFactory.manufacturePojo(classType, genericTypes);
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods

  // Private Methods ---------------------------------------------------------------------------------- Private Methods

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

} // end of class