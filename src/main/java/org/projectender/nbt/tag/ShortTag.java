package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortTag extends Tag {

    private short value;

    public ShortTag(@Nullable String name) {
        super(name);
    }

    public ShortTag(@Nullable String name, short value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.SHORT;
    }

    public short getValue() {
        return this.value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeShort(value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readShort();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof ShortTag && ((ShortTag) o).getValue() == this.getValue();
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final short val = this.value;
        result = result * PRIME + val;
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
