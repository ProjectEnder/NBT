package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class IntArrayTag extends Tag {

    private int[] value;

    public IntArrayTag(@Nullable String name) {
        super(name);
    }

    public IntArrayTag(@Nullable String name, int[] value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.INT_ARRAY;
    }

    public int[] getValue() {
        return value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeInt(this.value.length);
        for (int val : this.value)
            out.writeInt(val);
    }

    @Override
    void read(DataInput in) throws IOException {
        int size = in.readInt();
        this.value = new int[size];
        for (int i = 0; i < size; i++)
            value[i] = in.readInt();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof IntArrayTag && Arrays.equals(((IntArrayTag) o).getValue(), this.getValue());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final int[] val = this.value;
        result = result * PRIME + (val == null ? 43 : Arrays.hashCode(val));
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
