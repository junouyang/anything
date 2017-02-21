package com.singularity.ee.util.javaspecific.collections;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Copyright: AppDynamics
 * User: mvajapeyam
 * Date: 10/13/11
 * Time: 3:40 PM
 */

/* This class is NOT thread-safe. It is intended to be used within a single thread/thread-local
*
*  Note that only IMMUTABLE objects should be added here, otherwise caching the hashcode is meaningless. There is no compile-time check for this, such
 *  as forcing the generic type <E> to implement some IImmutable interface. The reason is that we want to allow immutable JDK classes in, such as String.
*
* */
public class ADImmutableObjectList<E> extends ArrayList<E> {

    private int cachedHashCode;
    private boolean dirtyHash = true; //only compute the hash code if needed

    public ADImmutableObjectList() {
        super();
    }

    public ADImmutableObjectList(int initialCapacity) {
        super(initialCapacity);
    }

    public ADImmutableObjectList(Collection<? extends E> c) {
        super(c);
    }

    public E set(int index, E element) {
        dirtyHash = true;
        return super.set(index, element);
    }

    public boolean add(E e) {
        dirtyHash = true;
        return super.add(e);
    }

    public void add(int index, E element) {
        dirtyHash = true;
        super.add(index, element);
    }

    public E remove(int index) {
        dirtyHash = true;
        return super.remove(index);
    }

    public boolean remove(Object o) {
        dirtyHash = true;
        return super.remove(o);
    }

    public void clear() {
        dirtyHash = true;
        super.clear();
    }

    public boolean addAll(Collection<? extends E> c) {
        dirtyHash = true;
        return super.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        dirtyHash = true;
        return super.addAll(index, c);
    }

    public int hashCode() {
        if (dirtyHash) {
            cachedHashCode = super.hashCode();
            dirtyHash = false;
        }

        return cachedHashCode;
    }
}
