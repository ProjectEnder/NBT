package org.projectender.nbt.tag;

import org.projectender.nbt.NBTUtils;
import org.projectender.nbt.TagType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class Tag {

    private static final Logger logger = LoggerFactory.getLogger(Tag.class);

    private final String name;

    public Tag(@Nullable String name) {
        this.name = name;
    }

    public abstract TagType getType();

    abstract void write(DataOutput out) throws IOException;

    abstract void read(DataInput in) throws IOException;

    public byte getId() {
        return (byte) getType().ordinal();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return NBTUtils.toString(this);
    }

    public static void writeTag(Tag tag, DataOutput outputStream) {
        try {
            outputStream.write(tag.getType().getByte());
            if (tag.getName() != null) {
                byte[] bytes = tag.getName().getBytes();
                outputStream.writeShort(bytes.length);
                outputStream.write(bytes);
            }
            tag.write(outputStream);
        } catch (IOException e) {
            logger.error("Failed to write {} ({}) to stream", tag.getType().getName(), tag.getName(), e);
        }
    }

    @Nonnull
    public static Tag readTag(DataInput in) {
        Tag tag = new EndTag();
        try {
            byte b = in.readByte();
            if (b == 0) return new EndTag();

            tag = newTag(b, in.readUTF());

            tag.read(in);
        } catch (IOException e) {
            logger.error("Failed to read from stream", e);
        }
        return tag;
    }

    public static Tag newTag(byte type, @Nullable String name) {
        TagType tagType = TagType.class.getEnumConstants()[type];
        switch (tagType) {
            case END:
                return new EndTag();
            case BYTE:
                return new ByteTag(name);
            case SHORT:
                return new ShortTag(name);
            case INT:
                return new IntTag(name);
            case LONG:
                return new LongTag(name);
            case FLOAT:
                return new FloatTag(name);
            case DOUBLE:
                return new DoubleTag(name);
            case BYTE_ARRAY:
                return new ByteArrayTag(name);
            case STRING:
                return new StringTag(name);
            case LIST:
                return new ListTag<>(name);
            case COMPOUND:
                return new CompoundTag(name);
            case INT_ARRAY:
                return new IntArrayTag(name);
            case LONG_ARRAY:
                return new LongArrayTag(name);
            default:
                logger.warn("Invalid tag with ID of {}!!", type);
                return new EndTag();
        }
    }
}
