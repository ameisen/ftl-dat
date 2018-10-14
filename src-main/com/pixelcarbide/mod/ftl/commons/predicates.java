package com.pixelcarbide.mod.ftl.commons;

import com.pixelcarbide.mod.ftl.commons.annotations.*;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;

import java.util.Collection;

@UtilityClass @Immutable
@SuppressWarnings({"unused, WeakerAccess"})
public final class predicates {
	@Contract(value = "_ -> true", pure = true) @Pure
	public static <T> boolean alwaysTrue(@Immutable @Nullable final T unused) {
		return true;
	}

	@Contract(value = "_ -> false", pure = true) @Pure
	public static <T> boolean alwaysFalse(@Immutable @Nullable final T unused) {
		return false;
	}

	@Contract(value = "false -> false; true -> true", pure = true) @Pure
	public static boolean isTrue(final boolean value) {
		return value;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean isTrue(@Immutable @NotNull final T object) {
		assert object != null;
		return object;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Comparable<Boolean>> boolean isTrue(@Immutable @NotNull final T object) {
		assert object != null;
		return object.compareTo(true) == 0;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean areTrue(@Immutable @Valid final boolean ... objects) {
		assert objects != null;
		for (val object : objects) {
			if (!object) {
				return false;
			}
		}
		return true;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean areTrue(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (!object.booleanValue()) {
				return false;
			}
		}
		return true;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Comparable<Boolean>> boolean areTrue(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (object.compareTo(true) != 0) {
				return false;
			}
		}
		return true;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean anyTrue(@Immutable @Valid final boolean ... objects) {
		assert objects != null;
		for (val object : objects) {
			if (object) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean anyTrue(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (object.booleanValue()) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Comparable<Boolean>> boolean anyTrue(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (object.compareTo(true) == 0) {
				return true;
			}
		}
		return false;
	}

	@Contract(value = "false -> true; true -> false", pure = true) @Pure
	public static boolean isFalse(final boolean value) {
		return !value;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean isFalse(@Immutable @NotNull final T object) {
		assert object != null;
		return !object.booleanValue();
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Comparable<Boolean>> boolean isFalse(@Immutable @NotNull final T object) {
		assert object != null;
		return object.compareTo(false) == 0;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean areFalse(@Immutable @Valid final boolean ... objects) {
		assert objects != null;
		for (val object : objects) {
			if (object) {
				return false;
			}
		}
		return true;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean areFalse(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (object.booleanValue()) {
				return false;
			}
		}
		return true;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Comparable<Boolean>> boolean areFalse(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (object.compareTo(true) == 0) {
				return false;
			}
		}
		return true;
	}

	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean anyFalse(@Immutable @Valid final boolean ... objects) {
		assert objects != null;
		for (val object : objects) {
			if (!object) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Boolean> boolean anyFalse(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (!object.booleanValue()) {
				return true;
			}
		}
		return false;
	}

	@SafeVarargs
	@Contract(value = "null -> fail", pure = true) @Pure
	public static <T extends Comparable<Boolean>> boolean anyFalse(@Immutable @Valid final T ... objects) {
		assert objects != null;
		for (val object : objects) {
			assert object != null;
			if (object.compareTo(true) != 0) {
				return true;
			}
		}
		return false;
	}

	@Contract(value = "null -> true; !null -> false", pure = true) @Pure
	public static <T> boolean isNull(@Immutable @Nullable final T object) {
		return object == null;
	}

	@Contract(value = "null -> false; !null -> true", pure = true) @Pure
	public static <T> boolean isNotNull(@Immutable @Nullable final T object) {
		return object != null;
	}

	@Contract(value = "null -> fail; !null -> _", pure = true) @Pure
	public static <T extends Collection> boolean isEmpty(@Immutable @NotNull final T object) {
		return object.isEmpty();
	}

	@Contract(value = "null -> fail; !null -> _", pure = true) @Pure
	public static <T extends Collection> boolean isEmpty(@Immutable @NotNull final T[] object) {
		assert object != null;
		return object.length > 0;
	}

	@Contract(value = "null -> fail; !null -> _", pure = true) @Pure
	public static <T extends String> boolean isEmpty(@Immutable @NotNull final T object) {
		return object.isEmpty();
	}

	@Contract(value = "null -> fail; !null -> _", pure = true) @Pure
	public static <T extends Collection> boolean isNotEmpty(@Immutable @NotNull final T object) {
		return !object.isEmpty();
	}

	@Contract(value = "null -> fail; !null -> _", pure = true) @Pure
	public static <T extends Collection> boolean isNotEmpty(@Immutable @NotNull final T[] object) {
		assert object != null;
		return object.length <= 0;
	}

	@Contract(value = "null -> fail; !null -> _", pure = true) @Pure
	public static <T extends String> boolean isNotEmpty(@Immutable @NotNull final T object) {
		return !object.isEmpty();
	}

	@Contract(value = "null -> false; !null -> true", pure = true) @Pure
	public static <T> boolean isValid(@Immutable @Nullable final T object) {
		return object != null;
	}

	@Contract(value = "null -> false; !null -> true", pure = true) @Pure
	public static <T> boolean isValid(@Immutable @Nullable final T[] object) {
		return object != null && object.length > 0;
	}

	@Contract(value = "null -> false; !null -> _", pure = true) @Pure
	public static <T extends Collection> boolean isValid(@Immutable @Nullable final T object) {
		return object != null && !object.isEmpty();
	}

	@Contract(value = "null -> false; !null -> _", pure = true) @Pure
	public static <T extends String> boolean isValid(@Immutable @Nullable final T object) {
		return object != null && !object.isEmpty();
	}

	@Contract(value = "null -> true; !null -> false", pure = true) @Pure
	public static <T> boolean isNotValid(@Immutable @Nullable final T object) {
		return object == null;
	}

	@Contract(value = "null -> true; !null -> false", pure = true) @Pure
	public static <T> boolean isNotValid(@Immutable @Nullable final T[] object) {
		return object == null || object.length <= 0;
	}

	@Contract(value = "null -> true; !null -> _", pure = true) @Pure
	public static <T extends Collection> boolean isNotValid(@Immutable @Nullable final T object) {
		return object == null || object.isEmpty();
	}

	@Contract(value = "null -> true; !null -> _", pure = true) @Pure
	public static <T extends String> boolean isNotValid(@Immutable @Nullable final T object) {
		return object == null || object.isEmpty();
	}

	@Contract(value = "_, null -> fail; null, !null -> false", pure = true) @Pure
	public static <T> boolean isCompatible(@Immutable @Nullable final T object, @Immutable @NotNull Class<?> clazz) {
		assert clazz != null;
		if (object == null) {
			return false;
		}
		return clazz.isInstance(object);
	}

	@Contract(value = "_, null -> fail; null, !null -> true", pure = true) @Pure
	public static <T> boolean isNotCompatible(@Immutable @Nullable final T object, @Immutable @NotNull Class<?> clazz) {
		assert clazz != null;
		if (object == null) {
			return true;
		}
		return !clazz.isInstance(object);
	}

	@Contract(value = "null, !null -> false; !null, null -> false; null, null -> true", pure = true) @Pure
	public static <T, U> boolean areCompatible(@Immutable @Nullable final T object, @Immutable @Nullable final U object2) {
		if ((object == null) ^ (object2 == null)) {
			return false;
		}
		if (object == object2) {
			return true;
		}

		return object2.getClass().isInstance(object);
	}

	@Contract(value = "null, !null -> true; !null, null -> true; null, null -> false", pure = true) @Pure
	public static <T, U> boolean areNotCompatible(@Immutable @Nullable final T object, @Immutable @Nullable final U object2) {
		if ((object == null) ^ (object2 == null)) {
			return true;
		}
		if (object == object2) {
			return false;
		}

		return !object2.getClass().isInstance(object);
	}
}
