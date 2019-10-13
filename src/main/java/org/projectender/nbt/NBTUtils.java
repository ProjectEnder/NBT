package org.projectender.nbt;

import org.projectender.nbt.tag.ByteArrayTag;
import org.projectender.nbt.tag.ByteTag;
import org.projectender.nbt.tag.CompoundTag;
import org.projectender.nbt.tag.DoubleTag;
import org.projectender.nbt.tag.FloatTag;
import org.projectender.nbt.tag.IntArrayTag;
import org.projectender.nbt.tag.IntTag;
import org.projectender.nbt.tag.ListTag;
import org.projectender.nbt.tag.LongArrayTag;
import org.projectender.nbt.tag.LongTag;
import org.projectender.nbt.tag.ShortTag;
import org.projectender.nbt.tag.StringTag;
import org.projectender.nbt.tag.Tag;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class NBTUtils {

    private static final Pattern SPLIT_NEW_LINE = Pattern.compile("\n");

    private NBTUtils() {}

    public static String toString(Tag tag) {
        StringBuilder sb = new StringBuilder(tag.getType().getName())
            .append('(').append(tag.getName() == null || tag.getName().isEmpty()
                ? "None" : '\'' + tag.getName() + '\'').append(')');

        if (tag.getType() == TagType.END)
            return sb.toString();

        sb.append(": ");

        switch (tag.getType()) {
            case BYTE:
                sb.append(((ByteTag) tag).getValue());
                break;
            case SHORT:
                sb.append(((ShortTag) tag).getValue());
                break;
            case INT:
                sb.append(((IntTag) tag).getValue());
                break;
            case LONG:
                sb.append(((LongTag) tag).getValue());
                break;
            case FLOAT:
                sb.append(((FloatTag) tag).getValue());
                break;
            case DOUBLE:
                sb.append(((DoubleTag) tag).getValue());
                break;
            case BYTE_ARRAY:
                sb.append(Arrays.toString(((ByteArrayTag) tag).getValue()));
                break;
            case STRING:
                sb.append('\'').append(((StringTag) tag).getValue()).append('\'');
                break;
            case LIST:
                @SuppressWarnings("unchecked")
                ListTag<Tag> list = (ListTag<Tag>) tag;
                sb.append(list.getValue().size()).append(" entries").append("\n{\n")
                    .append(list.getValue().stream().map(NBTUtils::toStringMapper)
                        .collect(Collectors.joining("\n")))
                    .append("\n}");
                break;
            case COMPOUND:
                CompoundTag compound = (CompoundTag) tag;
                sb.append(compound.getValue().size()).append(" entries").append("\n{\n")
                    .append(compound.getValue().values().stream().map(NBTUtils::toStringMapper)
                        .collect(Collectors.joining("\n")))
                    .append("\n}");
                break;
            case INT_ARRAY:
                sb.append(Arrays.toString(((IntArrayTag) tag).getValue()));
                break;
            case LONG_ARRAY:
                sb.append(Arrays.toString(((LongArrayTag) tag).getValue()));
                break;
            default:
                break;
        }

        return sb.toString();
    }

    private static String toStringMapper(Tag tag) {
        String mapping = tag.toString();

        if (mapping.contains("\n"))
            return Arrays.stream(SPLIT_NEW_LINE.split(mapping))
                .map(line -> "  " + line)
                .collect(Collectors.joining("\n"));
        else
            return "  " + mapping;
    }
}
