package com.pixelcarbide.mod.ftl.commons;

import static java.nio.channels.FileChannel.MapMode;

import com.google.common.io.Files;
import com.pixelcarbide.mod.ftl.commons.annotations.*;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import lombok.NonNull;
import lombok.extern.slf4j.XSlf4j;
import lombok.val;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@XSlf4j(topic="ftl.DatFile")
@SuppressWarnings({"unused, WeakerAccess"})
@Immutable
public abstract class MemoryMappedFile implements AutoCloseable {
	private final @NotAliased @NotNull MappedByteBuffer buffer;
	private @NotNegative int currentOffset = 0;
	public final @NotNegative int length;

	@Contract("null, _ -> fail; _, null -> fail") @NotAliased
	protected MemoryMappedFile(@NonNull @Valid final Path filePath, @NotNull @NonNull final MapMode mode)
		throws IllegalArgumentException, IOException {
		try {
			//noinspection UnstableApiUsage
			buffer = Files.map(filePath.toFile(), mode);
			// Not sure if this is correct. Will the original byte order be LE on a BE system? Will it know?
			// Need to check on one. Gotta find one, first.
			buffer.order(ByteOrder.nativeOrder());
			length = buffer.remaining();
		}
		catch (@NotNull final UnsupportedOperationException ex) {
			throw new IOException(ex);
		}
	}

	@Override
	public void close() {
		// Google's IO library doesn't give us something to close, once it GCs it will close. Not ideal.
	}

	@Contract(pure = true)
	public final void rewind() {
		buffer.rewind();
		currentOffset = 0;
	}

	@Contract(pure = true)
	public final void setOffset (@NotNegative final int offset) throws IndexOutOfBoundsException {
		//noinspection ConstantConditions
		if (offset < 0) {
			throw new IndexOutOfBoundsException(
				String.format("MemoryMappedFile::setOffset offset '%d' is negative", offset)
			);
		}
		if (offset >= length) {
			throw new IndexOutOfBoundsException(
				String.format("MemoryMappedFile::setOffset offset '%d' is larger than length '%d'", offset, length)
			);
		}

		if (offset == currentOffset) {
			return;
		}

		buffer.position(offset);
		currentOffset = offset;
	}

	@Contract(pure = true)
	public final void skip (@NotNegative final int offset) throws IndexOutOfBoundsException {
		setOffset(currentOffset + offset);
	}

	@Contract(pure = true)
	public final boolean has (@NotNegative final int size) throws IllegalArgumentException {
		assert size >= 0;

		return size <= (length - currentOffset);
	}

	@NotNegative @Contract(pure = true)
	public final int offset () {
		return currentOffset;
	}

	@Contract("null -> fail")
	public final void read(@Valid @Mutable final byte[] dest) throws BufferUnderflowException {
		if (dest.length == 0) {
			return;
		}

		buffer.get(dest);
		skip(dest.length);
	}

	@Contract("_, null -> fail")
	public final void read(@NotNegative final int offset, @Valid @Mutable final byte[] dest)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		if (dest.length == 0) {
			return;
		}

		setOffset(offset);

		read(dest);
	}

	@Valid @Contract("_ -> new") @NotAliased
	public final ByteBuffer read(@Positive final int size)
		throws BufferUnderflowException, IllegalArgumentException {
		assert size > 0;

		@Valid @NotAliased val bytes = new byte[size];
		buffer.get(size);
		return ByteBuffer.wrap(bytes).asReadOnlyBuffer();
	}

	@Valid @Contract("_, _ -> new") @NotAliased
	public final ByteBuffer read(@NotNegative final int offset, @Positive final int size)
		throws BufferUnderflowException, IllegalArgumentException {
		assert size > 0;

		setOffset(offset);

		return read(size);
	}

	public final byte readByte() throws BufferUnderflowException {
		val retValue = buffer.get();
		skip(Byte.BYTES);
		return retValue;
	}

	public final byte readByte(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readByte();
	}

	public final char readChar() throws BufferUnderflowException {
		val retValue = buffer.getChar();
		skip(Character.BYTES);
		return retValue;
	}

	public final char readChar(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readChar();
	}

	public final short readShort() throws BufferUnderflowException {
		val retValue = buffer.getShort();
		skip(Short.BYTES);
		return retValue;
	}

	public final short readShort(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readShort();
	}

	public final int readInt() throws BufferUnderflowException {
		val retValue = buffer.getInt();
		skip(Integer.BYTES);
		return retValue;
	}

	public final int readInt(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readInt();
	}

	public final long readLong() throws BufferUnderflowException {
		val retValue = buffer.getLong();
		skip(Long.BYTES);
		return retValue;
	}

	public final long readLong(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readLong();
	}

	public final float readFloat() throws BufferUnderflowException {
		val retValue = buffer.getFloat();
		skip(Float.BYTES);
		return retValue;
	}

	public final float readFloat(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readFloat();
	}

	public final double readDouble() throws BufferUnderflowException {
		val retValue = buffer.getDouble();
		skip(Double.BYTES);
		return retValue;
	}

	public final double readDouble(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);
		return readDouble();
	}

	// I'm not entirely sure of the best way to write this. Technically, we should be able to get a byte[] array
	// but I am wary of a potential copy if Java is dumb about it.
	@NotNull @Contract("-> new") @NotAliased
	public final String readCString() throws BufferUnderflowException {
		val bytes = new ByteArrayList();

		buffer.mark();
		try {
			byte c;
			while ((c = buffer.get()) != (byte)0) {
				bytes.add(c);
			}

			@NotNull @NotAliased val retValue = new String(bytes.elements(), StandardCharsets.UTF_8);

			skip(bytes.elements().length + 1); // + 1 for null terminator

			return retValue;
		}
		finally {
			buffer.reset();
		}
	}

	@NotNull @Contract("_ -> new") @NotAliased
	public final String readCString(@NotNegative final int offset)
		throws BufferUnderflowException, IndexOutOfBoundsException {
		setOffset(offset);

		return readCString();
	}
}
