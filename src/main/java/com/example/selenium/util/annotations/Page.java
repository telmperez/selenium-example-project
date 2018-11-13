package com.example.selenium.util.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Page {

  String url() default "";

  String context() default "";

  String title() default "";

}
