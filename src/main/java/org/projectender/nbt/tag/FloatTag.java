package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatTag extends Tag {

    private float value;

    public FloatTag(@Nullable String name) {
        super(name);
    }

    public FloatTag(@Nullable String name, float value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.FLOAT;
    }

    public float getValue() {
        return value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeFloat(value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readFloat();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof FloatTag && ((FloatTag) o).getValue() == this.getValue();
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final float val = this.value;
        result = result * PRIME + Float.hashCode(val);
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
