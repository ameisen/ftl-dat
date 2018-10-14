package com.pixelcarbide.mod.ftl;

import com.pixelcarbide.mod.ftl.commons.MemoryMappedInputFile;
import com.pixelcarbide.mod.ftl.commons.annotations.Immutable;
import com.pixelcarbide.mod.ftl.commons.annotations.NotNull;
import com.pixelcarbide.mod.ftl.commons.annotations.Pure;
import com.pixelcarbide.mod.ftl.commons.annotations.Valid;
import com.pixelcarbide.mod.ftl.commons.cli;
import com.pixelcarbide.mod.ftl.commons.exceptions.ExitError;
import com.pixelcarbide.mod.ftl.commons.predicates;
import lombok.extern.slf4j.XSlf4j;
import lombok.val;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings({"unused, WeakerAccess"})
@Immutable
public final class DatFileExec {
	private enum ExecutionMode {
		Unpack,
		Pack
	}

	private static @Valid Path filePath;
	private static @Valid Path destPath;
	private static @NotNull ExecutionMode executionMode;

	@Contract(value = "null -> fail", pure = true) @Pure
	private static void processArguments(@Valid String[] args) throws ExitError {
		assert predicates.isValid(args);

		val commandGroup = cli.OptionGroupEx.of(
			Option.builder("u")
				.argName("Unpack")
				.desc("Unpack .dat file")
				.hasArg()
				.longOpt("unpack")
				.numberOfArgs(2)
				.type(Path.class),
			Option.builder("p")
				.argName("Pack")
				.desc("Pack .dat file")
				.hasArg()
				.longOpt("pack")
				.numberOfArgs(2)
				.type(Path.class)
		);
		commandGroup.setRequired(true);

		val options = new Options();
		options.addOptionGroup(commandGroup);

		String path = "";
		try {
			val parser = new DefaultParser();
			val commandLine = parser.parse(options, args);

			val commandOption = commandGroup.getSelection();
			log.info("selected: {}", commandOption.getArgName());

			switch (commandOption.getArgName()) {
				case "Unpack":
					executionMode = ExecutionMode.Unpack;
					break;
				case "Pack":
					executionMode = ExecutionMode.Pack;
					break;
				default:
					throw ExitError.of(1, "Command Line Error: command not legal: '%s'", commandOption.getArgName());
			}

			path = commandOption.getValue(0);
			assert path != null;
			filePath = Paths.get(path);

			path = commandOption.getValue(1);
			assert path != null;
			destPath = Paths.get(path);
		}
		catch (@NotNull ParseException ex) {
			throw ExitError.of(1, ex,"Command Line Error");
		}
		catch (@NotNull InvalidPathException ex) {
			throw ExitError.of(2, ex, "Invalid File Path: '%s'", path);
		}
	}

	@Contract("_ -> fail") @TerminatesExecution
	private static void handleExitError(@Immutable @NotNull final ExitError ex) {
		log.error("ExitError {}", ex.errorCode);
		if (ex.message != null) {
			log.error("ExitError {}", ex.message);
		}
		if (ex.cause != null) {
			log.error("ExitError {}", ex.cause.getLocalizedMessage(), ex.cause);
		}
		System.exit(ex.errorCode);
		// Execution terminates after this point.
	}

	@Contract("null -> fail")
	public static void main(@Valid String[] args) {
		assert predicates.isValid(args);

		try {
			processArguments(args);
			switch (executionMode) {
				case Unpack:
					unpack(filePath, destPath);
					break;
				case Pack:
				default:
					throw ExitError.of(-1, "Unimplemented Execution Mode: %s", executionMode.name());
			}
		}
		catch (@NotNull ExitError ex) {
			handleExitError(ex);
			// Execution terminates after this point.
		}
	}

	public static void unpack(@Immutable @Valid Path inputFile, @Immutable @Valid Path destination) throws ExitError {
		assert inputFile != null;
		assert destination != null;

		if (Files.notExists(inputFile)) {
			throw ExitError.of(3, "Input File '%s' does not exist", inputFile.toString());
		}

		try {
			val inputData = MemoryMappedInputFile.of(inputFile);
		}
		catch (@NotNull IOException ex) {
			throw ExitError.of(4, ex, "IOException during unpack: %s", ex.getLocalizedMessage());
		}
	}

}
