package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class LongArrayTag extends Tag {

    private long[] value;

    public LongArrayTag(@Nullable String name) {
        super(name);
    }

    public LongArrayTag(@Nullable String name, long[] value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.LONG_ARRAY;
    }

    public long[] getValue() {
        return this.value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeInt(this.value.length);
        for (long val : this.value)
            out.writeLong(val);
    }

    @Override
    void read(DataInput in) throws IOException {
        int size = in.readInt();
        this.value = new long[size];
        for (int i = 0; i < size; i++)
            value[i] = in.readLong();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof LongArrayTag && Arrays.equals(((LongArrayTag) o).getValue(), this.getValue());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long[] val = this.value;
        result = result * PRIME + (val == null ? 43 : Arrays.hashCode(val));
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
