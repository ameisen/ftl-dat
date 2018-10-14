package com.pixelcarbide.mod.ftl.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@com.fasterxml.jackson.annotation.JacksonAnnotationsInside
@javax.annotation.Nullable
@org.jetbrains.annotations.Nullable
@org.checkerframework.checker.nullness.qual.Nullable
@org.eclipse.jdt.annotation.Nullable
@SuppressWarnings("unused")
public @interface Nullable { }
