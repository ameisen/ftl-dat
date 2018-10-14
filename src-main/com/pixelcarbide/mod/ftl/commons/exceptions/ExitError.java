package com.pixelcarbide.mod.ftl.commons.exceptions;

import com.pixelcarbide.mod.ftl.commons.annotations.*;
import com.pixelcarbide.mod.ftl.commons.utilities;
import lombok.extern.slf4j.XSlf4j;
import org.jetbrains.annotations.Contract;

import javax.validation.constraints.NotEmpty;

@Immutable
@SuppressWarnings({"unused, WeakerAccess"})
@XSlf4j(topic="ftl.DatFile")
public final class ExitError extends Error {
	public final int errorCode;
	public final @Nullable @NotEmpty String message;
	public final @Nullable Throwable cause;

	private ExitError(final int errorCode, @Nullable @NotEmpty final String message, @Nullable final Throwable cause) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.cause = cause;

		// We don't need the return value, since it modifies the exception being passed in.
		utilities.trimFrames(this, 1);
	}

	@Contract(value = "!null, !null -> new; null, _ -> fail; _, null -> fail", pure = true)
	@Valid @Immutable @NotAliased
	private static String formatSafe(@Immutable @Valid final String format, @Immutable @NotNull final Object ... args) {
		try {
			return String.format(format, args);
		}
		catch (@NotNull final Throwable ex) {
			log.warn("ExitError::formatSafe encountered an exception", ex);
			return format;
		}
	}

	@Contract(value = "_ -> new", pure = true)
	@NotNull @Immutable @NotAliased
	public static ExitError of(final int errorCode) {
		return new ExitError(errorCode, null, null);
	}

	@Contract(value = "_, !null -> new; _, null -> fail", pure = true)
	@NotNull @Immutable @NotAliased
	public static ExitError of(final int errorCode, @Immutable @NotNull final Throwable ex) {
		return new ExitError(errorCode, null, ex);
	}

	@Contract(value = "_, !null, !null -> new; _, null, _ -> fail; _, _, null -> fail", pure = true)
	@NotNull @Immutable @NotAliased
	public static ExitError of(
		final int errorCode,
		@Immutable @Valid final String format,
		@Immutable @NotNull final Object ... args
	) {
		return new ExitError(errorCode, formatSafe(format, args), null);
	}

	@Contract(value = "_, !null, !null, !null -> new; _, null, _, _ -> fail; _, _, null, _ -> fail; _, _, _, null -> fail", pure = true)
	@NotNull @Immutable @NotAliased
	public static ExitError of(
		final int errorCode,
		@Immutable @NotNull final Throwable ex,
		@Immutable @Valid final String format,
		@Immutable @NotNull final Object ... args
	) {
		return new ExitError(errorCode, formatSafe(format, args), null);
	}
}
