package dataStructures;

import java.io.Serializable;

/**
 * Dictionary Abstract Data Type.
 * Includes description of general methods to be implemented by dictionaries.
 * @version 1.0.
 * @param <K> Generic Key.
 * @param <V> Generic Value.
 */
public interface Dictionary<K, V> extends Serializable {

    /**
     * Returns true if the dictionary contains no entries.
     * @return true if the dictionary contains no entries, false otherwise.
     */
    boolean isEmpty ();

    /**
     * Returns the number of entries in the dictionary.
     * @return the number of entries in the dictionary.
     */
    int size();

    /**
     * Returns an iterator of the entries in the dictionary.
     * @return an iterator of the entries in the dictionary.
     */
    Iterator<Entry<K,V>> iterator( );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     * @param key key.
     * @return value of the entry in the dictionary whose key is the specified key,
     * null otherwise.
     */
    V find( K key );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     * @param key key.
     * @param value newValue.
     * @return the old value or null.
     */
    V insert( K key, V value );

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value; otherwise, returns null.
     * @param key key to remove.
     * @return value in key, otherwise null.
     */
    V remove( K key );
}
