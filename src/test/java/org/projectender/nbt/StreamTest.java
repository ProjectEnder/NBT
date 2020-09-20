package org.projectender.nbt;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.projectender.nbt.tag.CompoundTag;
import org.projectender.nbt.tag.ListTag;
import org.projectender.nbt.tag.Tag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StreamTest {

    private static final File test = new File("test.dat");
    private static CompoundTag tag;

    @BeforeClass
    public static void init() {
        if (!test.exists()) {
            try {
                test.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Assert.fail();
            }
        }

        tag = new CompoundTag();
        CompoundTag data = new CompoundTag("data");
        // objectives
        ListTag<CompoundTag> objectives = new ListTag<>("Objectives");

        CompoundTag test = new CompoundTag();
        test.putString("CriteriaName", "dummy");
        test.putString("DisplayName", "{\"text\": \"Testing123\"}");
        test.putString("Name", "obj");
        test.putString("RenderType", "integer");
        objectives.add(test);

        data.putList("Objectives", objectives);

        // scores
        ListTag<CompoundTag> scores = new ListTag<>("PlayerScores");
        data.putList("PlayerScores", scores);
        // teams
        ListTag<CompoundTag> teams = new ListTag<>("Teams");
        data.putList("Teams", teams);
        // display slots
        CompoundTag displaySlots = new CompoundTag("DisplaySlots");
        data.putCompound(displaySlots);

        tag.putCompound(data);
    }

    @Test
    public void testA_FileWriting() throws IOException {
        try (DataOutputStream output = new DataOutputStream(new DeflaterOutputStream(new FileOutputStream(test),
            new Deflater(Deflater.DEFAULT_COMPRESSION)))
        ) {
            Tag.writeTag(tag, output);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testB_FileReading() throws IOException {
        byte[] data = Files.readAllBytes(test.toPath());
        try (DataInputStream in = new DataInputStream(new InflaterInputStream(new FileInputStream(test),
            new Inflater(), 2048))
        ) {
//        try (DataInputStream in = new DataInputStream(new DeflaterInputStream(new FileInputStream(test),
//            new Deflater(Deflater.DEFAULT_COMPRESSION)))
//        ) {
            CompoundTag root = (CompoundTag) Tag.readTag(in);
            Assert.assertEquals(tag, root);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
