package dataStructures;

import java.io.Serializable;

/**
 * BT node implementation.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 * @version 1.0.
 * @param <E> Generic type Element.
 */
class BTNode<E> implements Serializable {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * Element stored in the node.
     */
    private E element;

    /**
     * (Pointer to) the left child.
     */
    private BTNode<E> leftChild;

    /**
     * (Pointer to) the right child.
     */
    private BTNode<E> rightChild;

    /**
     * BTNode constructor.
     * @param theElement stored in the node.
     * @param theLeft (Pointer to) the left child.
     * @param theRight (Pointer to) the right child.
     */
    public BTNode(E theElement, BTNode<E> theLeft, BTNode<E> theRight) {
        element = theElement;
        leftChild = theLeft;
        rightChild = theRight;
    }

    /**
     * BTNode constructor.
     * @param theElement stored in the node.
     */
    public BTNode(E theElement){
        this(theElement, null, null);
    }

    /**
     * Retrieves the element stored in this BTNode.
     * @return The element stored in this BTNode.
     */
    public E getElement() {
        return element;
    }

    /**
     * Retrieves the left child of this BTNode.
     * @return The left child of this BTNode.
     */
    public BTNode<E> getLeft() {
        return leftChild;
    }

    /**
     * Retrieves the right child of this BTNode.
     * @return The right child of this BTNode.
     */
    public BTNode<E> getRight() {
        return rightChild;
    }

    /**
     * Sets the element of this BTNode to a new value.
     * @param newElement The new value to set as the element.
     */
    public void setElement(E newElement) {
        element = newElement;
    }

    /**
     * Sets the left child of this BTNode to a new node.
     * @param newLeft The new BTNode to set as the left child.
     */
    public void setLeft(BTNode<E> newLeft) {
        leftChild = newLeft;
    }

    /**
     * Sets the right child of this BTNode to a new node.
     * @param newRight The new BTNode to set as the right child.
     */
    public void setRight(BTNode<E> newRight) {
        rightChild = newRight;
    }

    /**
     * Returns true if the node is a leaf.
     * @return true if the node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
