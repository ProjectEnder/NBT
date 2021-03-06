package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class ByteTag extends Tag {

    private byte value;

    public ByteTag(@Nullable String name) {
        super(name);
    }

    public ByteTag(@Nullable String name, byte value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.BYTE;
    }

    public byte getValue() {
        return this.value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.write(value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readByte();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof ByteTag && ((ByteTag) o).getValue() == this.getValue();
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final byte val = this.value;
        result = result * PRIME + val;
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
