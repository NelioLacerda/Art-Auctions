package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Separate Chaining Hash Table Iterator implementation.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 * @version 1.0.
 * @param <K> Generic Key, must extend comparable.
 * @param <V> Generic Value.
 */
class SepChainHashTableIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>>{

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * Dictionary to iterate.
     */
    protected Dictionary<K, V>[] table;

    /**
     * Current index in the table.
     */
    private int currentIndex;

    /**
     * Table size.
     */
    private final int size;

    /**
     * Iterator of Entries for the non-empty table index's.
     */
    private Iterator<Entry<K, V>> iterator;

    /**
     * Constructor of the SepChainHashTableIterator.
     * @param table table dictionary.
     * @param size table size.
     */
    public SepChainHashTableIterator(Dictionary<K, V>[] table, int size){
        this.table = table;
        this.size = size;
        this.currentIndex = 0;
        this.iterator = null;
        findNextNonEmptyIndex();
    }

    /**
     * Find the next non-empty index and assign it the iterator.
     */
    protected void findNextNonEmptyIndex() {
        while (currentIndex < size && table[currentIndex].isEmpty())
            currentIndex++;
        if (currentIndex < size) iterator = table[currentIndex].iterator();
        else iterator = null;
    }

    @Override
    public boolean hasNext() {
        return iterator != null;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException();
        Entry<K, V> entry = iterator.next();
        if (!iterator.hasNext()) {
            currentIndex++;
            findNextNonEmptyIndex();
        }
        return entry;
    }

    @Override
    public void rewind() {
        this.currentIndex = 0;
        this.iterator = null;
        findNextNonEmptyIndex();
    }
}
