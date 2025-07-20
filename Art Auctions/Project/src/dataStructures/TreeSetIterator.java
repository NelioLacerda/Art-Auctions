package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class TreeSetIterator<E extends Comparable<E>> implements Iterator<E>{

    /**
     * Stack containing all nodes by order.
     */
    protected Stack<BTNode<E>> stackNodes;

    /**
     * Root node.
     */
    protected BTNode<E> root;

    /**
     * TreeSetIterator implementation.
     * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
     * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
     * @version 1.0.
     * @param root root node..
     */
    public TreeSetIterator(BTNode<E> root){
        this.root = root;
        stackNodes = new StackInList<>();
        inorderLeft(root);
    }

    /**
     * Put tree nodes in the stack by order.
     * @param node node.
     */
    protected void inorderLeft(BTNode<E> node) {
        if (node != null) {
            stackNodes.push(node);
            this.inorderLeft(node.getLeft());
        }
    }

    @Override
    public boolean hasNext() {
        return !stackNodes.isEmpty();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException();
        BTNode<E> node = stackNodes.pop();
        if (node.getRight() != null)
            inorderLeft(node.getRight());
        return node.getElement();
    }

    @Override
    public void rewind() {
        stackNodes = new StackInList<>();
        inorderLeft(root);
    }
}
