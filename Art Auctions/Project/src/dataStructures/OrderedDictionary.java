package dataStructures;

import dataStructures.exceptions.EmptyDictionaryException;

/**
 * Ordered Dictionary interface.
 * @version 1.0.
 * @param <K> Generic type Key, must extend comparable.
 * @param <V> Generic type Value.
 */
public interface OrderedDictionary<K extends Comparable<K>, V> extends Dictionary<K , V> {

    /**
     * Returns the entry with the smallest key in the dictionary.
     * @return the entry with the smallest key in the dictionary.
     * @throws EmptyDictionaryException - if the dictionary is empty.
     */
    Entry<K , V> minEntry() throws EmptyDictionaryException;

    /**
     * Returns the entry with the largest key in the dictionary.
     * @return the entry with the largest key in the dictionary.
     * @throws EmptyDictionaryException - if the dictionary is empty.
     */
    Entry<K, V> maxEntry() throws EmptyDictionaryException;

    Iterator<Entry<K, V>> iterator();
}
