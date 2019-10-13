package org.projectender.nbt;

import org.junit.Assert;
import org.junit.Test;
import org.projectender.nbt.tag.ByteTag;
import org.projectender.nbt.tag.CompoundTag;
import org.projectender.nbt.tag.ListTag;

public class NBTUtilsTest {

    @Test
    public void testToString() {
        CompoundTag compoundTag = new CompoundTag("Test");
        compoundTag.putLong("TestLong", Long.MAX_VALUE);
        compoundTag.putLongArray("LongArr", new long[] {Long.MIN_VALUE, 0L, Long.MAX_VALUE});

        CompoundTag tag = new CompoundTag("Some Compound");

        ListTag<ByteTag> list = new ListTag<>();
        list.add(new ByteTag(null, (byte) 13));

        tag.putList("List", list);
        compoundTag.putCompound(tag);

        String expected = "TAG_Compound('Test'): 3 entries"
            + "\n{"
            + "\n  TAG_Long('TestLong'): 9223372036854775807"
            + "\n  TAG_Long_Array('LongArr'): [-9223372036854775808, 0, 9223372036854775807]"
            + "\n  TAG_Compound('Some Compound'): 1 entries"
            + "\n  {"
            + "\n    TAG_List(None): 1 entries"
            + "\n    {"
            + "\n      TAG_Byte(None): 13"
            + "\n    }"
            + "\n  }"
            + "\n}";

        Assert.assertEquals(expected, NBTUtils.toString(compoundTag));
    }
}
