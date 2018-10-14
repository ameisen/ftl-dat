package com.pixelcarbide.mod.ftl.commons.annotations;

import org.checkerframework.checker.index.qual.NonNegative;

import javax.annotation.Nonnegative;
import javax.validation.constraints.Min;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@com.fasterxml.jackson.annotation.JacksonAnnotationsInside
@javax.validation.constraints.Positive
@org.checkerframework.checker.index.qual.Positive
@Nonnegative
@NonNegative
@Min(1)
@SuppressWarnings("unused")
public @interface Positive { }
