package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayTag extends Tag {

    private byte[] value;

    public ByteArrayTag(@Nullable String name) {
        super(name);
    }

    public ByteArrayTag(@Nullable String name, byte[] value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.BYTE_ARRAY;
    }

    public byte[] getValue() {
        return value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeInt(this.value.length);
        out.write(this.value);
    }

    @Override
    void read(DataInput in) throws IOException {
        int size = in.readInt();
        this.value = new byte[size];
        in.readFully(this.value);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof ByteArrayTag && Arrays.equals(((ByteArrayTag) o).getValue(), this.getValue());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final byte[] val = this.value;
        result = result * PRIME + (val == null ? 43 : Arrays.hashCode(val));
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
