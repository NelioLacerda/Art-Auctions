package dataStructures;

class SearchableOrderedDoubleList<K extends Comparable<K>, V> extends OrderedDoubleList<K, V> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Constructor of SearchableOrderedDoubleList,
     */
    public SearchableOrderedDoubleList() {
        super();
    }

    /**
     * If the Entry with the specific key exists returns is value, otherwise returns null.
     * @param key key.
     * @return if the Entry with the specific key exists returns is value, otherwise returns null.
     */
    @Override
    public V find(K key) {
        DoubleListNode<Entry<K, V>> node = head;
        while (node != null) {
            if (node.getElement().getKey().compareTo(key) == 0)
                return node.getElement().getValue();
            node = node.getNext();
        }
        return null;
    }

    /**
     * Puts the new entry in the dictionary.
     * @param key key.
     * @param value newValue.
     * @return value if the entry doesn't exist, null otherwise.
     */
    @Override
    public V insert(K key, V value) {
        DoubleListNode<Entry<K,V>> node = findNode(key);
        if (node != null && node.getElement().getKey().compareTo(key) == 0) {
            return null;
        } else {
            Entry<K, V> newEntry = new EntryClass<>(key, value);
            if (node == head) addFirst(newEntry);
            else if(node == null) addLast(newEntry);
            else super.addBeforeNode(newEntry, node);
            return value;
        }
    }
}
