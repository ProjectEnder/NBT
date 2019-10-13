package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntTag extends Tag {

    private int value;

    public IntTag(@Nullable String name) {
        super(name);
    }

    public IntTag(@Nullable String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.INT;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeInt(value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readInt();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof IntTag && ((IntTag) o).getValue() == this.getValue();
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final int val = this.value;
        result = result * PRIME + val;
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
