package com.singularity.ee.util.javaspecific.collections;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.singularity.ee.util.collections.ConcurrentReferenceHashMap;

/**
 * Copyright: Singularity Technologies
 * User: Mu
 * Date: 1/28/11
 * Time: 4:56 PM
 */
public class ADConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private volatile boolean sizeDirty = true;
    private int sizeCached;

    private static final Object DUMMY = new Object();
    private static final ConcurrentReferenceHashMap<ADConcurrentHashMap, Object> instanceTracker =
            new ConcurrentReferenceHashMap<ADConcurrentHashMap, Object>(
                    ConcurrentReferenceHashMap.ReferenceType.WEAK,
                    ConcurrentReferenceHashMap.ReferenceType.WEAK);

    public ADConcurrentHashMap() {
        super();
        instanceTracker.put(this, DUMMY);
    }

    public ADConcurrentHashMap(int initialCapacity) {
        super(initialCapacity);
        instanceTracker.put(this, DUMMY);
    }

    /**
     * Returns a concurrent set of weakly references ADCHM for tracking
     * @return
     */
    public static ConcurrentReferenceHashMap<ADConcurrentHashMap, Object> getInstanceTracker() {
        return instanceTracker;
    }

    public ADIterator<K> keySetIterator() {
        return new ADIterator<K>(keySet().iterator());
    }

    public ADIterator<V> valuesIterator() {
        return new ADIterator<V>(values().iterator());
    }

    public Collection<K> getAllKeys() {
        return super.keySet();
    }

    public Collection<V> getAllValues() {
        return super.values();
    }

    @Override
    public V put(K k, V v) {
        try {
            return super.put(k, v);
        } finally {
            sizeDirty = true;
        }
    }

    @Override
    public V putIfAbsent(K k, V v) {
        try {
            return super.putIfAbsent(k, v);
        } finally {
            sizeDirty = true;
        }
    }

    /*
    No need to override putAll, calls put internally
    public void putAll(Map<? extends K, ? extends V> map)
    {
        try
        {
            super.putAll(map);
        }
        finally
        {
            sizeDirty = true;
        }
    }
    */

    @Override
    public V remove(Object o) {
        try {
            return super.remove(o);
        } finally {
            sizeDirty = true;
        }
    }

    @Override
    public boolean remove(Object o, Object o1) {
        try {
            return super.remove(o, o1);
        } finally {
            sizeDirty = true;
        }
    }

    @Override
    public void clear() {
        try {
            super.clear();
        } finally {
            sizeDirty = true;
        }
    }

    /* Because the different dirty/size state variables are not updated atomically, and the put/remove methods don't lock,
     we could get race conditions below, say if another thread changes the map & dirty flag right before the thread running
     fastApproximateSize is about to clear the sizeDirty flag. This, however, should be rare, and it is ok for the size to
      be off by a few when using it to check against limits.
     */
    public int fastApproximateSize() {
        if (sizeDirty) {
            boolean cachedSizeDirty = sizeDirty;
            sizeCached = super.size();
            if (cachedSizeDirty == sizeDirty) {
                sizeDirty = false;
            }
        }
        return sizeCached;
    }

    /* This method is needed for translation, we have a rule to replace isEmpty() with size() == 0 */
    public boolean isADCHMEmpty() {
        return isEmpty();
    }
}
