package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import java.io.DataInput;
import java.io.DataOutput;

public class EndTag extends Tag {

    public EndTag() {
        super(null);
    }

    @Override
    public TagType getType() {
        return TagType.END;
    }

    @Override
    void write(DataOutput out) {}

    @Override
    void read(DataInput in) {}

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof EndTag;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        return PRIME + this.getType().ordinal();
    }
}
