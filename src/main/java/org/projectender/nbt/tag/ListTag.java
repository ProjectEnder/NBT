package org.projectender.nbt.tag;

import org.projectender.nbt.TagType;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTag<T extends Tag> extends Tag implements Iterable<T> {

    private List<T> list = new ArrayList<>();
    private byte type;

    public ListTag() {
        super(null);
    }

    public ListTag(String name) {
        super(name);
    }

    @Override
    public TagType getType() {
        return TagType.LIST;
    }

    public List<T> getValue() {
        return this.list;
    }

    @Override
    void write(DataOutput out) throws IOException {
        if (list.size() > 0)
            type = list.get(0).getId();
        else
            type = 1;

        out.writeByte(type);
        out.writeInt(list.size());
        for (T tag : list)
            tag.write(out);
    }

    @SuppressWarnings("unchecked")
    @Override
    void read(DataInput in) throws IOException {
        type = in.readByte();
        int size = in.readInt();

        list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Tag tag = Tag.newTag(type, null);
            tag.read(in);
            list.add((T) tag);
        }
    }

    public void add(T tag) {
        type = tag.getId();
        list.add(tag);
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        return o instanceof ListTag && ((ListTag) o).getValue().equals(this.getValue());
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $list = this.list;
        result = result * PRIME + ($list == null ? 43 : $list.hashCode());
        result = result * PRIME + this.getType().ordinal();
        return result;
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return this.list.iterator();
    }
}
