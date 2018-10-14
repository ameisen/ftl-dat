package com.pixelcarbide.mod.ftl.commons.annotations;

import jdk.jfr.Unsigned;
import org.checkerframework.checker.index.qual.NonNegative;

import javax.annotation.Nonnegative;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@com.fasterxml.jackson.annotation.JacksonAnnotationsInside
@Nonnegative
@NonNegative
@PositiveOrZero
@Min(0)
@Unsigned
@org.checkerframework.checker.signedness.qual.Unsigned
@SuppressWarnings("unused")
public @interface NotNegative { }
