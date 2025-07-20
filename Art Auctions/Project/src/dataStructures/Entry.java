package dataStructures;

import java.io.Serializable;

/**
 * Entry Abstract Data Type.
 * Includes description of general methods to be implemented by an entry.
 * @version 1.0.
 * @param <K> Generic Key.
 * @param <V> Generic Value.
 */
public interface Entry <K, V> extends Serializable {

    /**
     * Returns the key in the entry.
     * @return the key in the entry.
     */
    K getKey();

    /**
     * Returns the value in the entry.
     * @return the value in the entry.
     */
    V getValue();
}
