package org.projectender.nbt;

public enum TagType {

    END("TAG_End"),
    BYTE("TAG_Byte"),
    SHORT("TAG_Short"),
    INT("TAG_Int"),
    LONG("TAG_Long"),
    FLOAT("TAG_Float"),
    DOUBLE("TAG_Double"),
    BYTE_ARRAY("TAG_Byte_Array"),
    STRING("TAG_String"),
    LIST("TAG_List"),
    COMPOUND("TAG_Compound"),
    INT_ARRAY("Tag_Int_Array"),
    LONG_ARRAY("TAG_Long_Array");

    private final String name;

    TagType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getByte() {
        return this.ordinal();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
