package com.singularity.ee.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import com.singularity.ee.util.javaspecific.collections.ADConcurrentHashMap;
import com.singularity.ee.util.javaspecific.collections.ADImmutableObjectList;

/**
 * Copyright (c) AppDynamics Technologies
 * @author manoj.acharya
 * @version Nov 15, 2010
 */
public class CollectionHelper {
    private CollectionHelper() {
    } //ensure class is "static"

    /* This wrapper is needed to prevent the translator from converting size() to Count -- which we don't want for this interface */
    public static <T> int queueSize(Queue<T> queue) {
        return queue.size();
    }

    public static <T> List<T> synchronizedList(List<T> list) {
        return Collections.synchronizedList(list);
    }

    public static <T> Collection<T> createSyncronizedCollection() {
        return new ConcurrentLinkedQueue<T>();
    }

    /* The "V" generic param in the function "map" param normally would be a '?',
    but dotNet does not support wildcard generics. Thus the param has to be listed
    explicitly in the function
       In dotNet this method is EXPENSIVE as it created a new object every time, so if the calling
    code runs often, we should add a CollectionHelper.containsKey method.
    */
    public static <K, V> Set<K> getAllKeys(Map<K, V> map) {
        return map.keySet();
    }

    /**
     * This method does the same as getAllKeys, and while unused at the time of creation, may be useful for dotNet in the future.
     * The motivation for this method is that in dotNet, returning the keys as a Set requires constructing
     * a new object, whereas returning a collection does not -- so the custom implementation of this method in
     * dotNet is cheaper. Thus, this getAllKeysAsSimpleCollection method is preferable to getAllKeys even in Java, unless
     * the caller needs to call methods contains or containsAll in the resulting collection.
     *
     * In the latter case, i.e. calling code using contains or containsAll, using a Set in Java (i.e. method CollectionHelper.getAllKeys)
     * is preferable because methods contains (lowecase 'c') and containsAll are not available in dotNet's ICollection, only in
     * the ADHashSet. That being said, this is only the case if the method is not called frequently -- if it is, we
     * should add a CollectionHelper.containsKey method, and calling code should use it instead.
     */
    public static <K, V> Collection<K> getAllKeysAsSimpleCollection(Map<K, V> map) {
        return map.keySet();
    }

    public static <K> HashSet<K> createHashSet(int size) {
        return new HashSet<K>(size);
    }

    public static <K> Set<K> createSynchronizedHashSet(K[] ks) {
        return createSynchronizedHashSet(CollectionHelper.<K>createHashSet(ks));
    }

    public static <K> Set<K> createSingletonSet(K item) {
        return Collections.singleton(item);
    }

    public static <K> Set<K> createSynchronizedHashSet(Set<K> ks) {
        return Collections.synchronizedSet(ks);
    }

    public static <K> Set<K> createUnmodifiableSet(Set<K> ks) {
        return Collections.unmodifiableSet(ks);
    }

    public static <K> Set<K> createHashSet(K[] ks) {
        Set<K> kSet = new HashSet<K>(ks.length, 1);
        kSet.addAll(Arrays.asList(ks));
        return kSet;
    }

    public static <K, V> Map<K, V> createHashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> createHashMap(int size) {
        return new HashMap<K, V>(size, 1);
    }

    public static <K> List<K> createLinkedList() {
        return new LinkedList<K>();
    }

    public static <K> void addAll(Collection<K> destination, Collection<? extends K> toBeAdded) {
        if (destination != null && toBeAdded != null) {
            destination.addAll(toBeAdded);
        }
    }


    public static <K> void addAll(Collection<K> destination, K[] toBeAdded) {
        if (destination != null && toBeAdded != null) {
            Collections.addAll(destination, toBeAdded);
        }
    }

    public static <K> void addAllToArray(K[] destination, Collection<K> toBeAdded) {
        if (destination != null && toBeAdded != null) {

            int inx = 0;
            for (Iterator<K> iterator = toBeAdded.iterator(); iterator.hasNext(); inx++) {
                K k = iterator.next();
                destination[inx] = k;
            }
        }
    }


    public static <K> List<K> asList(K... input) {
        if (input == null)
            return null;
        return Arrays.asList(input);
    }

    public static <T> T[] collectionToArray(Collection<T> l, T[] a) {
        return l.toArray(a);
    }

    public static <K, V> void putAll(Map<K, V> source, Map<K, V> newItems) {
        source.putAll(newItems);
    }

    public static <T> CopyOnWriteArrayList<T> createCopyOnWriteList() {
        return new CopyOnWriteArrayList<T>();
    }

    public static <K, V> CopyOnWriteMap<K, V> createCopyOnWriteMap() {
        return new CopyOnWriteMap<K, V>();
    }

    public static <T> List<T> subList(List<T> list, int len) {
        return list.subList(0, len);
    }

    public static <T> List<T> subList(List<T> list, int from, int to) {
        return list.subList(from, to);
    }

    public static void reverse(List<?> list) {
        Collections.reverse(list);
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
    }

    public static <T> void sort(T[] list, Comparator<T> comp) {
        Arrays.sort(list, comp);
    }

    public static <T> boolean remove(List<T> list, T value) {
        return list.remove(value);
    }

    public static <T> T removeAt(List<T> list, int pos) {
        return list.remove(pos);
    }

    public static <T> String arrayToString(T[] array) {
        return Arrays.toString(array);
    }

    public static <K, V> String mapToString(Map<K, V> map) {
        if (map == null)
            return null;
        return map.toString();
    }

    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> orig) {
        return Collections.unmodifiableMap(orig);
    }

    public static <T> Set<T> unmodifiableSet(Set<T> orig) {
        return Collections.unmodifiableSet(orig);
    }

    public static <T> List<T> unmodifiableList(List<T> orig) {
        return Collections.unmodifiableList(orig);
    }

    public static boolean arrayEquals(Object[] a1, Object[] a2) {
        return Arrays.equals(a1, a2);
    }

    public static <T> String collectionToString(Collection<T> collection) {
        if (collection == null)
            return null;
        return collection.toString();
    }

    public static <E> int getJavaAlgorithmCollectionHashCode(Collection<E> col) {
        if (col == null)
            return 0;

        //ADImmutableObjectList hashcode is optimized, call it directly
        if (col instanceof ADImmutableObjectList) {
            return col.hashCode();
        }

        if (col instanceof ArrayList || col instanceof Vector) {
            int result = 1;
            List<E> l = (List<E>) col;
            for (int i = 0; i < col.size(); i++) {
                E e = l.get(i);
                result = 31 * result + (e == null ? 0 : e.hashCode());
            }
            return result;
        } else {
            if (col.size() == 0)
                return 0; //optimization to avoid creating an iterator. Could not be done earlier in the method b/c the built-in for arraylist is 1, not 0
            return col.hashCode();
        }
    }

    public static int getJavaAlgorithmArrayHashCode(Object[] a) {
        return Arrays.hashCode(a);
    }

    /* This wrapper is needed because the in .net there are no wildcard generics, so a constructor like
    Java's ArrayList(Collection<? extends E> c) cannot be invoked directly.
     */
    public static <T, U extends T> ArrayList<T> getNewList(List<U> input) {
        return new ArrayList<T>(input);
    }

    /* This wrapper is needed b/c of a Tangible bug that doesn't translate ICollection APIs properly */
    public static <T> void addToCollection(Collection<T> col, T elem) {
        col.add(elem);
    }

    public static <K, V> Collection<V> getAllValues(ADConcurrentHashMap<K, V> map) {
        return map.values();
    }

    /**
     * @param elements
     * @param maxNumber
     * @return the maxNumber of elements in ids and rest are skipped
     */
    public static <T> Set<T> getElementsOfMaxSize(Set<T> elements, int maxNumber) {
        // The returned set should maintain the same order as the input elements
        Set<T> maxIds = new LinkedHashSet<T>();

        if (maxNumber <= 0) {
            return maxIds;
        }

        for (T id : elements) {
            maxIds.add(id);

            maxNumber--;

            if (maxNumber <= 0) {
                break;
            }

        }

        elements.removeAll(maxIds);

        return maxIds;
    }

    public static void main(String[] args) {
        HashSet<Long> ids = new HashSet<Long>(Arrays.asList(2L, 3L));
        System.out.println(getElementsOfMaxSize(ids, 1));
        System.out.println(ids);
    }

    public static <T, U> int getJavaAlgorithmMapHashCode(Map<T, U> map) {
        if (map == null || map.size() == 0)
            return 0;
        return map.hashCode();
    }

    public static String intArrayToString(int[] intArray) {
        return Arrays.toString(intArray);
    }

    public static <T> Set<T> emptySerializableSet() {
        return Collections.<T>emptySet();
    }

    public static <T> List<T> emptySerializableList() {
        return Collections.<T>emptyList();
    }

    public static <K, V> Map<K, V> emptySerializableMap() {
        return Collections.<K, V>emptyMap();
    }

    /**
     * Calculates a value case insensitive hash code for a String v String map. Method placed here to
     * reduce .NET translation overhead
     * @param map
     * @return
     */
    public static int calculateCaseInsensitiveValueStringVsStringMapHash(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException();
        }

        int result = 0;
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry<String, String> next = iter.next();
            int entryHash = (next.getKey() == null ? 0 : next.getKey().hashCode()) ^
                    (next.getValue() == null ? 0 : next.getValue().toLowerCase().hashCode());
            result += entryHash;
        }
        return result;
    }


    public static long[] collectionToLongArray(Collection<Long> allKeys) {
        if (allKeys == null)
            return null;
        long[] values = new long[allKeys.size()];
        int count = 0;
        for (Long allKey : allKeys) {
            values[count++] = allKey.longValue();
        }
        return values;
    }

    public static Set<String> asSet(String[] names) {
        Set<String> set = createHashSet(names.length);
        Collections.addAll(set, names);
        return set;
    }

    /**
     * Returns the provided list as is if it is not null else returns a List of size 0.
     * @param list
     * @param <E>
     * @return
     */
    public static <E> List<E> getListNoNull(List<E> list) {
        return list == null ? new ArrayList<E>() : list;
    }

    static public boolean IsArrayListInstance(Object object) {
        return object instanceof ArrayList;
    }

    public static <E> boolean addIsAccepted(ArrayList<E> list, E e) {
        return list.add(e);
    }
}
