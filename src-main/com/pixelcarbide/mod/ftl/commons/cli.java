package com.pixelcarbide.mod.ftl.commons;

import com.pixelcarbide.mod.ftl.commons.annotations.Immutable;
import com.pixelcarbide.mod.ftl.commons.annotations.NotAliased;
import com.pixelcarbide.mod.ftl.commons.annotations.NotNull;
import com.pixelcarbide.mod.ftl.commons.annotations.Valid;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;

@UtilityClass @Immutable @SuppressWarnings("unused")
public final class cli {
	public static final class OptionGroupEx extends OptionGroup {
		private static @NotNull final HashMap<String, Option> storedOptions = new HashMap<>();

		@NotNull @Contract(value = "null -> fail; !null -> this", pure = true)
		public OptionGroupEx addOptions(@Valid Option... options) {
			if (options == null) {
				throw new IllegalArgumentException("OptionGroupEx::addOptions::options is null");
			}

			for (val option : options) {
				this.addOption(option);
				storedOptions.put(option.getArgName(), option);
			}
			return this;
		}
		@NotNull @Contract(value = "null -> fail; !null -> this", pure = true)
		public OptionGroupEx addOptions(@Valid Option.Builder... options) {
			if (options == null) {
				throw new IllegalArgumentException("OptionGroupEx::addOptions::options is null");
			}

			for (val builder : options) {
				val option = builder.build();
				this.addOption(option);
				storedOptions.put(option.getArgName(), option);
			}
			return this;
		}

		@NotNull @Contract(value = "null -> fail; !null -> new", pure = true) @NotAliased
		public static OptionGroupEx of(@Valid Option... options) {
			val retValue = new OptionGroupEx();
			retValue.addOptions(options);
			return retValue;
		}

		@NotNull @Contract(value = "null -> fail; !null -> new", pure = true) @NotAliased
		public static OptionGroupEx of(@Valid Option.Builder... options) {
			val retValue = new OptionGroupEx();
			retValue.addOptions(options);
			return retValue;
		}

		@NotNull @Contract(value = "-> !null", pure = true)
		public Option getSelection() {
			val selectedName = super.getSelected();
			val option = storedOptions.get(selectedName);
			if (option == null) {
				throw new RuntimeException("OptionGroupEx::getSelected somehow is returning null");
			}
			return option;
		}
	}
}
