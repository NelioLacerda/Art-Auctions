package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class ValueIterator<K, V> implements Iterator<V> {

    private final Iterator<Entry<K, V>> iterator;

    public ValueIterator(Iterator<Entry<K, V>> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public V next() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException();
        return iterator.next().getValue();
    }

    @Override
    public void rewind() {
        iterator.rewind();
    }
}
