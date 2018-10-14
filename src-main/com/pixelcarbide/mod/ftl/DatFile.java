package com.pixelcarbide.mod.ftl;

import com.pixelcarbide.mod.ftl.commons.MemoryMappedFile;
import com.pixelcarbide.mod.ftl.commons.MemoryMappedInputFile;
import com.pixelcarbide.mod.ftl.commons.annotations.*;
import lombok.NonNull;
import lombok.extern.slf4j.XSlf4j;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.file.Path;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings({"unused, WeakerAccess"})
@Immutable
public final class DatFile implements AutoCloseable {
	private @NotNull @NotAliased final MemoryMappedFile file;

	@Contract("null -> fail") @NotAliased
	public DatFile(@NonNull @Valid final Path filePath) throws IllegalArgumentException, IOException {
		file = MemoryMappedInputFile.of(filePath);
	}

	@Override
	public void close() {
		file.close();
	}

	@NotNull @Contract("null -> fail; !null -> new") @NotAliased
	public static DatFile of(@Valid final Path filePath) throws IllegalArgumentException, IOException {
		return new DatFile(filePath);
	}

	@Nullable @Contract("null -> null") @NotAliased
	public static DatFile ofSafe(@Valid final Path filePath) throws IllegalArgumentException {
		try {
			//noinspection Contract
			return new DatFile(filePath);
		}
		catch (@NotNull final IOException unused) {
			return null;
		}
	}
}
