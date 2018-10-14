package com.pixelcarbide.mod.ftl;

import static com.pixelcarbide.mod.ftl.commons.cli.*;

import com.pixelcarbide.mod.ftl.commons.MemoryMappedFile;
import com.pixelcarbide.mod.ftl.commons.MemoryMappedInputFile;
import com.pixelcarbide.mod.ftl.commons.annotations.*;
import lombok.NonNull;
import lombok.extern.slf4j.XSlf4j;
import lombok.val;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.file.Path;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings({"unused, WeakerAccess"})
@Immutable
public final class DatFile implements AutoCloseable {
	private @NotNull @NotAliased final MemoryMappedFile file;

	@Contract("null -> fail")
	public static void main(@NotNull String[] args) {
		val commandGroup = OptionGroupEx.of(
			Option.builder("u")
				.argName("Unpack")
				.desc("Unpack .dat file")
				.hasArg()
				.longOpt("unpack")
				.numberOfArgs(1)
				.type(Path.class)
				.build(),
			Option.builder("p")
				.argName("Pack")
				.desc("Pack .dat file")
				.hasArg()
				.longOpt("pack")
				.numberOfArgs(1)
				.type(Path.class)
				.build()
		);
		commandGroup.setRequired(true);

		val options = new Options();
		options.addOptionGroup(commandGroup);

		try {
			val parser = new DefaultParser();
			val commandLine = parser.parse(options, args);

			log.info("selected: {}", commandGroup.getSelection().getArgName());
		}
		catch (@NotNull ParseException ex) {
			System.err.println("Command Line Error");
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
	}

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
