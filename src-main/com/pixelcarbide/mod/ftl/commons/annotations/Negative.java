package com.pixelcarbide.mod.ftl.commons.annotations;

import javax.validation.constraints.Max;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@com.fasterxml.jackson.annotation.JacksonAnnotationsInside
@javax.validation.constraints.Negative
@Max(-1)
@SuppressWarnings("unused")
public @interface Negative { }
