package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleTag extends Tag {

    private double value;

    public DoubleTag(@Nullable String name) {
        super(name);
    }

    public DoubleTag(@Nullable String name, double value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.DOUBLE;
    }

    public double getValue() {
        return value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeDouble(value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readDouble();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof DoubleTag && ((DoubleTag) o).getValue() == this.getValue();
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final double val = this.value;
        result = result * PRIME + Double.hashCode(val);
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
