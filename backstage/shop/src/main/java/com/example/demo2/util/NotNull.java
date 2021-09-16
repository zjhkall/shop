package com.example.demo2.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)//作用在属性身上field
public @interface NotNull { //作用在方法身上method
	
	String fieldName() default "";
	
}
