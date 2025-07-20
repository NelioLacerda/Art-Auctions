package dataStructures;

public class SearchableHashTable<K extends Comparable<K>, V> extends SepChainHashTable<K, V> {

    /**
     * Constructor of the SearchableHashTable.
     * This constructor initializes all the position of the table to one SearchableOrderedDoubleList.
     * @param capacity capacity.
     */
    @SuppressWarnings("unchecked")
    public SearchableHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for (int i = 0; i < arraySize; i++)
            table[i] = new SearchableOrderedDoubleList<>();
        maxSize = capacity;
        currentSize = 0;
    }

    /**
     * Constructor of the SearchableHashTable.
     * This constructor initializes all the position of the table to one SearchableOrderedDoubleList.
     */
    public SearchableHashTable(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public V find(K key) {
        return this.table[hash(key)].find(key);
    }

    /**
     * Returns the value of the entry to insert if the entry didn't exist already,
     * otherwise doesn't to anything and returns null.
     * @param key key.
     * @param value newValue.
     * @return the value of the entry to insert if the entry dint exists already, otherwise returns null.
     */
    @Override
    public V insert(K key, V value) {
        if (this.isFull()) this.rehash();
        V v = this.table[hash(key)].insert(key, value);
        if (v != null) currentSize++;
        return v;
    }

    @SuppressWarnings("unchecked")
    protected void rehash() {
        int capacity = maxSize * 2;
        int newSize = HashTable.nextPrime((int) (1.1 * capacity));
        Dictionary<K, V>[] newTable = (Dictionary<K, V>[]) new Dictionary[newSize];
        for (int i = 0; i < newSize; i++) newTable[i] = new SearchableOrderedDoubleList<>();
        Iterator<Entry<K, V>> it = new SepChainHashTableIterator<>(table, table.length);
        table = newTable;
        while (it.hasNext()){
            Entry<K, V> entry = it.next();
            K key = entry.getKey();
            V value = entry.getValue();
            table[this.hash(key)].insert(key, value);
        }
        maxSize = capacity;
    }
}
