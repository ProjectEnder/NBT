package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class CompoundTag extends Tag {

    private final Map<String, Tag> tags = new LinkedHashMap<>();

    public CompoundTag() {
        super("");
    }

    public CompoundTag(@Nullable String name) {
        super(name);
    }

    @Override
    public TagType getType() {
        return TagType.COMPOUND;
    }

    public Map<String, Tag> getValue() {
        return this.tags;
    }

    @Override
    void write(DataOutput out) throws IOException {
        for (Tag tag : tags.values())
            Tag.writeTag(tag, out);

        out.writeByte(TagType.END.getByte());
    }

    @Override
    void read(DataInput in) {
        tags.clear();
        Tag tag;
        while ((tag = Tag.readTag(in)).getId() != TagType.END.getByte()) {
            tags.put(tag.getName(), tag);
        }
    }

    public Collection<Tag> getAllTags() {
        return tags.values();
    }

    public void put(@Nonnull String name, Tag tag) {
        tags.put(name, tag);
    }

    public void putByte(@Nonnull String name, byte value) {
        tags.put(name, new ByteTag(name, value));
    }

    public void putShort(@Nonnull String name, short value) {
        tags.put(name, new ShortTag(name, value));
    }

    public void putInt(@Nonnull String name, int value) {
        tags.put(name, new IntTag(name, value));
    }

    public void putLong(@Nonnull String name, long value) {
        tags.put(name, new LongTag(name, value));
    }

    public void putFloat(@Nonnull String name, float value) {
        tags.put(name, new FloatTag(name, value));
    }

    public void putDouble(@Nonnull String name, double value) {
        tags.put(name, new DoubleTag(name, value));
    }

    public void putString(@Nonnull String name, String value) {
        tags.put(name, new StringTag(name, value));
    }

    public void putByteArray(@Nonnull String name, byte[] value) {
        tags.put(name, new ByteArrayTag(name, value));
    }

    public void putIntArray(@Nonnull String name, int[] value) {
        tags.put(name, new IntArrayTag(name, value));
    }

    public void putLongArray(@Nonnull String name, long[] value) {
        tags.put(name, new LongArrayTag(name, value));
    }

    public void putList(@Nonnull String name, ListTag<? extends Tag> list) {
        tags.put(name, list);
    }

    public void putCompound(CompoundTag value) {
        tags.put(value.getName(), value);
    }

    public void putBoolean(String string, boolean val) {
        putByte(string, val ? (byte) 1 : 0);
    }

    public Tag get(@Nonnull String name) {
        return tags.get(name);
    }

    public boolean contains(@Nonnull String name) {
        return tags.containsKey(name);
    }

    public byte getByte(@Nonnull String name) {
        ByteTag tag = (ByteTag) this.tags.get(name);
        return tag != null ? tag.getValue() : 0;
    }

    public short getShort(@Nonnull String name) {
        ShortTag tag = (ShortTag) this.tags.get(name);
        return tag != null ? tag.getValue() : 0;
    }

    public int getInt(@Nonnull String name) {
        IntTag tag = (IntTag) this.tags.get(name);
        return tag != null ? tag.getValue() : 0;
    }

    public long getLong(@Nonnull String name) {
        LongTag tag = (LongTag) this.tags.get(name);
        return tag != null ? tag.getValue() : 0;
    }

    public float getFloat(@Nonnull String name) {
        FloatTag tag = (FloatTag) this.tags.get(name);
        return tag != null ? tag.getValue() : 0;
    }

    public double getDouble(@Nonnull String name) {
        DoubleTag tag = (DoubleTag) this.tags.get(name);
        return tag != null ? tag.getValue() : 0;
    }

    public String getString(@Nonnull String name) {
        StringTag tag = (StringTag) this.tags.get(name);
        return tag != null ? tag.getValue() : "";
    }

    public byte[] getByteArray(@Nonnull String name) {
        ByteArrayTag tag = (ByteArrayTag) this.tags.get(name);
        return tag != null ? tag.getValue() : new byte[0];
    }

    public int[] getIntArray(@Nonnull String name) {
        IntArrayTag tag = (IntArrayTag) this.tags.get(name);
        return tag != null ? tag.getValue() : new int[0];
    }

    public long[] getLongArray(@Nonnull String name) {
        LongArrayTag tag = (LongArrayTag) this.tags.get(name);
        return tag != null ? tag.getValue() : new long[0];
    }

    public CompoundTag getCompound(@Nonnull String name) {
        CompoundTag tag = (CompoundTag) this.tags.get(name);
        return tag != null ? tag : new CompoundTag(name);
    }

    @SuppressWarnings("unchecked")
    public ListTag<? extends Tag> getList(@Nonnull String name) {
        ListTag tag = (ListTag) this.tags.get(name);
        return tag != null ? tag : new ListTag<>();
    }

    public boolean getBoolean(String name) {
        return getByte(name) != 0;
    }

    /////////////////////////////////////////////////////////////
    // Optionals
    /////////////////////////////////////////////////////////////
    public Optional<Byte> getOptionalByte(@Nonnull String name) {
        ByteTag tag = (ByteTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<Short> getOptionalShort(@Nonnull String name) {
        ShortTag tag = (ShortTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<Integer> getOptionalInt(@Nonnull String name) {
        IntTag tag = (IntTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<Long> getOptionalLong(@Nonnull String name) {
        LongTag tag = (LongTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<Float> getOptionalFloat(@Nonnull String name) {
        FloatTag tag = (FloatTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<Double> getOptionalDouble(@Nonnull String name) {
        DoubleTag tag = (DoubleTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<String> getOptionalString(@Nonnull String name) {
        StringTag tag = (StringTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<byte[]> getOptionalByteArray(@Nonnull String name) {
        ByteArrayTag tag = (ByteArrayTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<int[]> getOptionalIntArray(@Nonnull String name) {
        IntArrayTag tag = (IntArrayTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<long[]> getOptionalLongArray(@Nonnull String name) {
        LongArrayTag tag = (LongArrayTag) this.tags.get(name);
        return tag == null ? Optional.empty() : Optional.of(tag.getValue());
    }

    public Optional<CompoundTag> getOptionalCompound(@Nonnull String name) {
        return Optional.ofNullable((CompoundTag) this.tags.get(name));
    }

    @SuppressWarnings("unchecked")
    public Optional<ListTag<? extends Tag>> getOptionalList(@Nonnull String name) {
        return Optional.ofNullable((ListTag) this.tags.get(name));
    }

    public Optional<Boolean> getOptionalBoolean(String name) {
        return getOptionalByte(name).map(b -> b == 0);
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof CompoundTag && ((CompoundTag) o).getValue().equals(this.getValue());
    }
}
