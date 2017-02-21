package com.singularity.ee.util.serialize;

import java.io.IOException;

/**
 * Copyright (c) Singularity Technologies
 *
 * Different serializer implementations must implement this interface.
 * @author manoj.acharya
 * @version Aug 18, 2009
 */
public interface ObjectSerializer {
    public byte[] marshalObject(Object obj) throws IOException;

    public Object unmarshalObject(byte[] content) throws IOException, ClassNotFoundException;

    public String getContentType();
}
