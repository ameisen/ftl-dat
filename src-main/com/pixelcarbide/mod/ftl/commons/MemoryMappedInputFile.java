package com.pixelcarbide.mod.ftl.commons;

import lombok.extern.slf4j.XSlf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.nio.file.Path;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings("unused")
public class MemoryMappedInputFile {
	@Contract("null -> fail")
	public MemoryMappedInputFile(@NotNull @NotEmpty Path filePath) throws IOException {
	}

	@NotNull @Contract("null -> fail; !null -> new")
	public MemoryMappedInputFile of(@NotNull @NotEmpty Path filePath) throws IOException {
	}
}
