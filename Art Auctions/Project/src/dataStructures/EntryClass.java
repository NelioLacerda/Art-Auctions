package dataStructures;

class EntryClass<K, V> implements Entry<K, V> {

     /**
      * Serial Version UID of the Class
      */
     static final long serialVersionUID = 0L;

    /**
     * Key in entry.
     */
    private K key;

    /**
     * Value in entry.
     */
    private V value;

    /**
     * Constructor of an Entry, containing a Key and respective value.
     * @param key key in entry.
     * @param value value in entry.
     */
    public EntryClass(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    /**
     * Sets the Key to a new giving Key.
     * @param newKey new Key.
     */
    public void setKey(K newKey){
        key = newKey;
    }

    /**
     * Sets the Value to a new giving Value.
     * @param newValue new Value.
     */
    public void setValue(V newValue){
        value = newValue;
    }
}
