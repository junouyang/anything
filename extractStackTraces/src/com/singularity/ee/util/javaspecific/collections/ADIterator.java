package com.singularity.ee.util.javaspecific.collections;

import java.util.Iterator;

/**
 * Copyright: Singularity Technologies
 * User: Mu
 * Date: 1/27/11
 * Time: 3:33 PM
 */


/**
 * This class is "javaspecific", i.e. it is custom written for dotNet (not translated). That is because translation will
 * replace Iterator<T> with Enumeration<T>, which is not what we want -- we want to use the actual Iterator<T> interface,
 * which has been ported from Java's collections framework to dotNet. And the motivation for *that* is because in
 * the dotNet implementation of ADConcurrentHashMap, heavily drawn from Doug Lea's implementation, we implement custom
 * Iterators that are thread-safe; translating these iterators (as well as the rest of the ADConcurrentHashMap interface)
 * to dotNet enumerations/types is a lot of extra work, so ADConcurrentHashMap's interface is completely independent
 * from dotNet IDictionary and other System.Collections.
 * @param <T>
 */

public class ADIterator<T> {
    private Iterator<T> it;

    public ADIterator(Iterable<T> iterable) {
        this.it = iterable.iterator();
    }

    public ADIterator(Iterator<T> it) {
        this.it = it;
    }

    public boolean hasNext() {
        return it.hasNext();
    }

    public T next() {
        return it.next();
    }

    public void remove() {
        it.remove();
        // No need to set the map dirty here for size() time optimization
        // this internally calls map.remove()
    }
}
