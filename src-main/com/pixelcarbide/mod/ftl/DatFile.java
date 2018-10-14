package com.pixelcarbide.mod.ftl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Path;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings("unused")
public final class DatFile implements AutoCloseable {

	@Contract("null -> fail")
	public DatFile(@NotNull @NonNull @NotEmpty final Path filePath) throws IOException {
	}

	@Override
	public void close() throws IOException {

	}

	@NotNull @Contract("null -> fail, !null -> new")
	public DatFile of(@NotNull @NotEmpty final Path filePath) throws IOException {
		return new DatFile(filePath);
	}

	@Nullable @Contract("null -> null; !null -> _")
	public DatFile ofSafe(@NotNull @NotEmpty final Path filePath) {
		try {
			return new DatFile(filePath);
		}
		catch (@NotNull final IOException unused) {
			return null;
		}
	}
}
