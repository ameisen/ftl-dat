package com.pixelcarbide.mod.ftl.commons;

import com.pixelcarbide.mod.ftl.commons.annotations.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.XSlf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

@UtilityClass @Immutable
@XSlf4j
@SuppressWarnings({"unused, WeakerAccess"})
public final class utilities {

	@NotNull @Pure @Immutable @Contract(value = "null -> fail; !null -> param1", pure = true)
	public static <E extends Throwable> E trimFrame(
		@NotNull @Mutable final E exception
	) {
		return trimFrames(exception, 1);
	}

	@NotNull @Pure @Immutable @Contract(value = "null, _ -> fail; !null, _ -> param1", pure = true)
	public static <E extends Throwable> E trimFrames(
		@NotNull @Mutable final E exception,
		@NotNegative final int frames
	) {
		assert exception != null;
		assert frames >= 0;
		if (frames == 0) {
			return exception;
		}
		@Valid val stackTrace = exception.getStackTrace();
		if (stackTrace.length <= 1) {
			return exception;
		}

		@NotNegative val traceOffset = StrictMath.min(stackTrace.length - 1, frames);
		@Valid val newStackTrace = Arrays.copyOfRange(stackTrace, traceOffset, stackTrace.length);
		exception.setStackTrace(newStackTrace);
		return exception;
	}

	@Valid @Pure @Immutable @Contract(value = "-> new", pure = true)
	public static String getScopeName() {
		@Valid var stack = Thread.currentThread().getStackTrace();
		@NotNegative val offset = StrictMath.max(stack.length - 1, 0);
		stack = Arrays.copyOfRange(stack, offset, stack.length);
		return getScopeName(stack);
	}

	@Valid @Pure @Immutable @Contract(value = "!null -> new; null -> fail", pure = true)
	public static String getScopeName(@NotNull @NotEmpty @Immutable StackTraceElement[] stack) {
		try {
			return stack[0].getClassName() + "::" + stack[0].getMethodName();
		}
		catch (@NotNull Throwable ex) {
			log.debug("utilities::getScopeName could not get stack trace", ex);
			return "unknown";
		}
	}
}
