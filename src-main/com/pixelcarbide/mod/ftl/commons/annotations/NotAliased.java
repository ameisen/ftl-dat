package com.pixelcarbide.mod.ftl.commons.annotations;

import org.checkerframework.common.aliasing.qual.Unique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@com.fasterxml.jackson.annotation.JacksonAnnotationsInside
@Unique
@SuppressWarnings("unused")
public @interface NotAliased { }
