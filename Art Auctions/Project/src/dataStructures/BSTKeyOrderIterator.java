package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * BSTKeyOrderIterator implementation.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 * @version 1.0.
 * @param <K> Generic type Key, must extend comparable.
 * @param <V> Generic type Value.
 */
class BSTKeyOrderIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>>{

    /**
     * Stack containing all nodes by order.
     */
    protected Stack<BSTNode<K, V>> stackNodes;

    /**
     * Root node.
     */
    protected BSTNode<K, V> root;

    /**
     * Constructor of BSTKeyOrderIterator.
     * @param root root node.
     */
    public BSTKeyOrderIterator(BSTNode<K, V> root){
        this.root = root;
        stackNodes = new StackInList<>();
        inorderLeft(root);
    }

    /**
     * Put tree nodes in the stack by order.
     * @param node node.
     */
    protected void inorderLeft(BSTNode<K, V> node) {
        if (node != null){
            stackNodes.push(node);
            this.inorderLeft(node.getLeft());
        }
    }

    @Override
    public boolean hasNext() {
        return !stackNodes.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException();
        BSTNode<K, V> node = stackNodes.pop();
        if (node.getRight() != null)
            inorderLeft(node.getRight());
        return node.getEntry();
    }

    @Override
    public void rewind() {
        stackNodes = new StackInList<>();
        inorderLeft(root);
    }
}
