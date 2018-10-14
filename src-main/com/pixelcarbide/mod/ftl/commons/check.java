package com.pixelcarbide.mod.ftl.commons;

import com.pixelcarbide.mod.ftl.commons.annotations.*;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.function.Predicate;

@UtilityClass @Immutable
@SuppressWarnings({"unused, WeakerAccess"})
public final class check {
	@Nullable @Pure @Contract(value = "null -> null; !null -> fail", pure = true)
	static <T> T checkNull (@Nullable @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNull);
	}

	@NotNull @Pure @Contract(value = "null -> fail; !null -> param1", pure = true)
	static <T> T checkNotNull (@Nullable @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNotNull);
	}

	@Pure @Contract(value = "null -> fail", pure = true)
	static <T extends Collection> T checkEmpty (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isEmpty);
	}

	@Pure @Contract(value = "null -> fail", pure = true)
	static <T extends String> T checkEmpty (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isEmpty);
	}

	@NotEmpty @Pure @Contract(value = "null -> fail", pure = true)
	static <T extends Collection> T checkNotEmpty (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNotEmpty);
	}

	@NotEmpty @Pure @Contract(value = "null -> fail", pure = true)
	static <T extends String> T checkNotEmpty (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNotEmpty);
	}

	@NotNull @NotEmpty @Pure @Contract(value = "null -> fail; !null -> param1", pure = true)
	static <T> T checkValid (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isValid);
	}

	@NotNull @NotEmpty @Pure @Contract(value = "null -> fail", pure = true)
	static <T extends Collection> T checkValid (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isValid);
	}

	@NotNull @NotEmpty @Pure @Contract(value = "null -> fail", pure = true)
	static <T extends String> T checkValid (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isValid);
	}

	@Nullable @Pure @Contract(value = "null -> null; !null -> fail", pure = true)
	static <T> T checkNotValid (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNotValid);
	}

	@Nullable @Pure @Contract(value = "null -> null; !null -> fail", pure = true)
	static <T extends Collection> T checkNotValid (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNotValid);
	}

	@Nullable @Pure @Contract(value = "null -> null; !null -> fail", pure = true)
	static <T extends String> T checkNotValid (@NotNull @Immutable final T object) throws IllegalArgumentException {
		return checkPredicate(object, predicates::isNotValid);
	}

	@Nullable @Pure @Contract(value = "_, null -> fail", pure = true)
	static <T, P extends Predicate<T>> T checkPredicate (
		@Nullable @Immutable final T object,
		@NotNull @Immutable final P predicate
	) throws IllegalArgumentException {
		if (predicate.test(object)) {
			return object;
		}
		throw new IllegalArgumentException();
	}
}
