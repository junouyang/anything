package com.singularity.ee.util.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Copyright (c) Singularity Technologies
 * @author manoj.acharya
 * @version Aug 18, 2009
 */
public class JavaSerializer implements com.singularity.ee.util.serialize.ObjectSerializer {
    private static final int BUFFER_SIZE = 1024;

    private static final String CONTENT_TYPE = "application/singularity-native-serializer";

    public byte[] marshalObject(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(BUFFER_SIZE);
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
        } finally {
            if (oos != null) {
                //does flush
                oos.close();
            }
        }
        return bos.toByteArray();
    }

    public Object unmarshalObject(byte[] content) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(content);
        LookAheadObjectInputStream ois = null;

        try {
            ois = new LookAheadObjectInputStream(bis);
            return ois.readObject();
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    public String getContentType() {
        return CONTENT_TYPE;
    }
}
