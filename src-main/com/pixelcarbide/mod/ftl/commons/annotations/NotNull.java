package com.pixelcarbide.mod.ftl.commons.annotations;

import org.eclipse.jdt.annotation.NonNull;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@com.fasterxml.jackson.annotation.JacksonAnnotationsInside
@javax.validation.constraints.NotNull
@org.jetbrains.annotations.NotNull
@NonNull
@org.checkerframework.checker.nullness.qual.NonNull
@Nonnull
@SuppressWarnings("unused")
public @interface NotNull { }
