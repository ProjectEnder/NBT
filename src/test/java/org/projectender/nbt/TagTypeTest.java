package org.projectender.nbt;

import org.junit.Assert;
import org.junit.Test;
import org.projectender.nbt.tag.ByteArrayTag;
import org.projectender.nbt.tag.ByteTag;
import org.projectender.nbt.tag.CompoundTag;
import org.projectender.nbt.tag.DoubleTag;
import org.projectender.nbt.tag.EndTag;
import org.projectender.nbt.tag.FloatTag;
import org.projectender.nbt.tag.IntArrayTag;
import org.projectender.nbt.tag.IntTag;
import org.projectender.nbt.tag.ListTag;
import org.projectender.nbt.tag.LongArrayTag;
import org.projectender.nbt.tag.LongTag;
import org.projectender.nbt.tag.ShortTag;
import org.projectender.nbt.tag.StringTag;

public class TagTypeTest {

    @Test
    public void testTagTypes() {
        Assert.assertEquals(TagType.END, new EndTag().getType());
        Assert.assertEquals(TagType.BYTE, new ByteTag(null).getType());
        Assert.assertEquals(TagType.SHORT, new ShortTag(null).getType());
        Assert.assertEquals(TagType.INT, new IntTag(null).getType());
        Assert.assertEquals(TagType.LONG, new LongTag(null).getType());
        Assert.assertEquals(TagType.FLOAT, new FloatTag(null).getType());
        Assert.assertEquals(TagType.DOUBLE, new DoubleTag(null).getType());
        Assert.assertEquals(TagType.BYTE_ARRAY, new ByteArrayTag(null).getType());
        Assert.assertEquals(TagType.STRING, new StringTag(null).getType());
        Assert.assertEquals(TagType.LIST, new ListTag<>(null).getType());
        Assert.assertEquals(TagType.COMPOUND, new CompoundTag(null).getType());
        Assert.assertEquals(TagType.INT_ARRAY, new IntArrayTag(null).getType());
        Assert.assertEquals(TagType.LONG_ARRAY, new LongArrayTag(null).getType());
    }
}
