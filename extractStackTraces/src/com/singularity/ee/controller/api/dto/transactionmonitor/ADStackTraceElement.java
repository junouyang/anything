package com.singularity.ee.controller.api.dto.transactionmonitor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.singularity.ee.controller.api.dto.SingularitySerializable;

/**
 * Copyright (c) AppDynamics, Inc.
 * @author Pranta Das
 *         November 2, 2011.
 */

public class ADStackTraceElement implements SingularitySerializable {
    private static final long serialVersionUID = 1L;
    private String className;
    private String methodName;
    private String fileName;
    private int lineNumber;

    public ADStackTraceElement() {

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    private void writeObject(ObjectOutputStream outStream) throws IOException {
        outStream.writeObject(getClassName());
        outStream.writeObject(getMethodName());
        outStream.writeObject(getFileName());
        outStream.writeObject(getLineNumber());
    }

    private void readObject(ObjectInputStream inStream) throws ClassNotFoundException, IOException {
        setClassName((String) inStream.readObject());
        setMethodName((String) inStream.readObject());
        setFileName((String) inStream.readObject());
        setLineNumber(((Integer) inStream.readObject()).intValue()); //explicit unboxing for dotnet
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ADStackTraceElement");
        sb.append("[className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", fileName=").append(fileName);
        sb.append(", lineNumber=").append(lineNumber);
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof ADStackTraceElement))
            return false;
        ADStackTraceElement e = (ADStackTraceElement) obj;
        return e.className.equals(className) && e.lineNumber == lineNumber
                && eq(methodName, e.methodName) && eq(fileName, e.fileName);
    }

    private boolean eq(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public int hashCode() {
        int result = 31 * className.hashCode() + methodName.hashCode();
        result = 31 * result + (fileName == null ? 0 : fileName.hashCode());
        result = 31 * result + lineNumber;
        return result;
    }
}
