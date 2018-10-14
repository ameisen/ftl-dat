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

@UtilityClass @Immutable @SuppressWarnings({"unused, WeakerAccess"})
public final class cli {
	public static final class OptionGroupEx extends OptionGroup {
		private static @NotNull final HashMap<String, Option> storedOptions = new HashMap<>();

		@Contract(value = "null -> fail", pure = true)
		public void addOptions(@Valid Option... options) {
			assert predicates.isValid(options);

			for (val option : options) {
				assert option != null;
				this.addOption(option);
				storedOptions.put(option.getArgName(), option);
			}
		}
		@Contract(value = "null -> fail", pure = true)
		public void addOptions(@Valid Option.Builder... options) {
			assert predicates.isValid(options);

			for (val builder : options) {
				assert builder != null;
				val option = builder.build();
				this.addOption(option);
				storedOptions.put(option.getArgName(), option);
			}
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
			assert option != null;
			return option;
		}
	}
}
