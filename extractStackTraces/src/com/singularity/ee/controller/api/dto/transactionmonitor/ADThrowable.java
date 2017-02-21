package com.singularity.ee.controller.api.dto.transactionmonitor;

import com.singularity.ee.controller.api.dto.SingularitySerializable;
import com.singularity.ee.util.collections.CollectionHelper;

/**
 * Copyright (c) AppDynamics, Inc.
 * @author Pranta Das
 *         November 2, 2011.
 */

public class ADThrowable implements SingularitySerializable {
    private static final long serialVersionUID = 1L;

    private String className;

    private ADStackTraceElement[] adStackTraceElements;

    public ADThrowable() {

    }

    public ADThrowable(String className) {
        setClassName(className);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ADStackTraceElement[] getAdStackTraceElements() {
        return adStackTraceElements;
    }

    public void setAdStackTraceElements(ADStackTraceElement[] adStackTraceElements) {
        this.adStackTraceElements = adStackTraceElements;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ADThrowable");
        sb.append("[className=").append(className);
        sb.append(", adStackTraceElements='").append(CollectionHelper.arrayToString(adStackTraceElements));
        sb.append(']');
        return sb.toString();
    }
}
