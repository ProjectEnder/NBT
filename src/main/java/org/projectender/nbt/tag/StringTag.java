package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringTag extends Tag {

    private String value;

    public StringTag(@Nullable String name) {
        super(name);
    }

    public StringTag(@Nullable String name, @Nonnull String value) {
        super(name);
        this.value = value;
    }

    @Override
    public TagType getType() {
        return TagType.STRING;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    void write(DataOutput out) throws IOException {
        out.writeUTF(this.value);
    }

    @Override
    void read(DataInput in) throws IOException {
        this.value = in.readUTF();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof StringTag && ((StringTag) o).getValue().equals(this.getValue());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final String val = this.value;
        result = result * PRIME + val.hashCode();
        result = result * PRIME + this.getType().ordinal();
        return result;
    }
}
