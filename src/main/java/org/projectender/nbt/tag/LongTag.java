package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongTag extends Tag {

    private long value;

    public LongTag(@Nullable String name) {
        super(name);
    }

    public LongTag(@Nullable String name, long value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.LONG;
    }

    public long getValue() {
        return this.value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeLong(value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readLong();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof LongTag && ((LongTag) o).getValue() == this.getValue();
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long val = this.value;
        result = result * PRIME + Long.hashCode(val);
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
