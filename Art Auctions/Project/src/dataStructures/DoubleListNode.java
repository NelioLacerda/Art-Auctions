package dataStructures;

import java.io.Serializable;

/**
 * Double List Node Implementation: This class should replace the one associated with DoubleList.
 * @version 1.0.
 * @param <E> Generic Element.
 */
class DoubleListNode<E> implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Element stored in the node.
     */
    private E element;

    /**
     * (Pointer to) the previous node.
     */
    private DoubleListNode<E> previous;

    /**
     * (Pointer to) the next node.
     */
    private DoubleListNode<E> next;

    /**
     * Constructor of the one node.
     * @param theElement - The element to be contained in the node.
     * @param thePrevious - the previous node.
     * @param theNext - the next node.
     */
    public DoubleListNode( E theElement, DoubleListNode<E> thePrevious, DoubleListNode<E> theNext ){
        element = theElement;
        previous = thePrevious;
        next = theNext;
    }

    /**
     *  Constructor of the one node, this node will not have a pointer to
     *  is previous or to is next, it's a 'empty' node.
     * @param theElement to be contained in the node.
     */
    public DoubleListNode( E theElement ) {
        this(theElement, null, null);
    }

    /**
     * Returns the element contained in the node.
     * @return the element contained in the node.
     */
    public E getElement( ){
        return element;
    }

    /**
     * Returns the previous node.
     * @return the previous node.
     */
    public DoubleListNode<E> getPrevious( ){
        return previous;
    }

    /**
     * Returns the next node.
     * @return the next node.
     */
    public DoubleListNode<E> getNext( ){
        return next;
    }

    /**
     * Replaces the element in the node to a new element.
     * @param newElement - New element to replace the current element.
     */
    public void setElement( E newElement ){
        element = newElement;
    }

    /**
     * Replaces the current previous node to a new node.
     * @param newPrevious - node to replace the current previous node.
     */
    public void setPrevious( DoubleListNode<E> newPrevious ){
        previous = newPrevious;
    }

    /**
     * Replaces the current next node to a new node.
     * @param newNext - node to replace the next node
     */
    public void setNext( DoubleListNode<E> newNext ){
        next = newNext;
    }
}
