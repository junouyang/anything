package com.singularity.ee.util.serialize;

/**
 * Copyright (c) Singularity Technologies
 * @author manoj.acharya
 * @version Aug 18, 2009
 */
public class ObjectSerializerFactory {
    private ObjectSerializer serializer;

    private static ObjectSerializerFactory serializerFactory = new ObjectSerializerFactory();

    /**
     * The factory supports one type of serializer per JVM
     *
     * Currrent support is for JDKSerializers, hence hard wired. If we were to addd support
     * for more serializers the factory can initialize the serializer based on a
     * config property
     */
    private ObjectSerializerFactory() {
        serializer = new JavaSerializer();
    }


    public static final ObjectSerializer getSerializer() {
        return serializerFactory.serializer;
    }
}
