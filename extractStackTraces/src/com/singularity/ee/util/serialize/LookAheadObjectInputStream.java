/*
 * Copyright (c) AppDynamics, Inc., and its affiliates
 * 2015
 * All Rights Reserved
 * THIS IS UNPUBLISHED PROPRIETARY CODE OF APPDYNAMICS, INC.
 * The copyright notice above does not evidence any actual or intended publication of such source code
 */

package com.singularity.ee.util.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * This class is used to validate that the objects being deserialized
 * come from trusted sources. A byte stream being deserialized can be
 * crafted in such a way that deserialization of the stream can have
 * unintended consequences in the server side. For instance, by adding
 * a DynamicProxy (via an invocation handler) to an Object that will
 * be deserialized at a server side, we can fool the server into
 * deserializing the associated proxy which could in turn execute any
 * code on the server environment. This class prevents such cases
 * by validating the objects being deserialized.
 *
 * Created by vivek.jeyakumar on 10/26/15.O
 */
public final class LookAheadObjectInputStream extends ObjectInputStream {
    private final String className;
    private static final String singularityPackageName = "com.singularity.ee";
    private static final String appdynamicsPackageName = "com.appdynamics";


    public LookAheadObjectInputStream(InputStream inputStream, String className)
            throws IOException {
        super(inputStream);
        this.className = className;
    }

    public LookAheadObjectInputStream(InputStream inputStream)
            throws IOException {
        super(inputStream);
        className = null;
    }

    /**
     * Only deserialize instances of expected class desc
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,
            ClassNotFoundException {
        String classDesc = desc.getName();
        int nameStartIndex = classDesc.indexOf('[') != -1 ? classDesc.lastIndexOf('[') + 2 : 0;
        classDesc = classDesc.substring(nameStartIndex);
        if (className != null && !classDesc.equals(className)) {
            throw new InvalidClassException(
                    "Unauthorized deserialization attempt",
                    desc.getName());
        }
        if (classDesc.length() > 0 && !classDesc.startsWith(singularityPackageName) && !classDesc.startsWith(appdynamicsPackageName)
                && !classDesc.startsWith("java.") && !classDesc.startsWith("com.google.common.collect.")) {
            throw new InvalidClassException(
                    "Unauthorized deserialization attempt",
                    desc.getName());
        }
        return super.resolveClass(desc);
    }
}
