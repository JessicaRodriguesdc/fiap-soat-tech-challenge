package br.com.fiap.tech_challenge.infra.entrypoint.controller.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = NullOrValidUUIDValidator.class)
public @interface NullOrValidUUID {

	String message() default "Not a valid UUID";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}