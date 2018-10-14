package com.pixelcarbide.mod.ftl.commons;

import static java.nio.channels.FileChannel.MapMode;

import com.pixelcarbide.mod.ftl.commons.annotations.*;
import lombok.NonNull;
import lombok.extern.slf4j.XSlf4j;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.file.Path;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings({"unused, WeakerAccess"})
@Immutable
public final class MemoryMappedInputFile extends MemoryMappedFile {
	@Contract("null -> fail") @NotAliased
	public MemoryMappedInputFile(@NonNull @Valid Path filePath) throws IOException, IllegalArgumentException {
		super(filePath, MapMode.READ_ONLY);
	}

	@NotNull @Contract("null -> fail; !null -> new") @NotAliased
	public static MemoryMappedInputFile of(@Valid Path filePath) throws IOException {
		return new MemoryMappedInputFile(filePath);
	}

	@Nullable @Contract("null -> null") @NotAliased
	public static MemoryMappedInputFile ofSafe(@Valid Path filePath) {
		try {
			//noinspection Contract
			return of(filePath);
		}
		catch (@NotNull final IOException unused) {
			return null;
		}
	}
}
