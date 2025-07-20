package dataStructures;

import java.io.Serializable;

/**
 * TreeSet Interface.
 * Uses a similar approach to AVLTree with a single element.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 * @version 1.0.
 * @param <E> Generic Element.
 */
public interface TreeSet<E extends Comparable<E>> extends Serializable {

    /**
     *  Returns true iff the stack contains no elements.
     * @return true iff the stack contains no elements, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the Tree Set.
     * @return number of elements in the Tree Set.
     */
    int size();

    /**
     * Add the element to the tree set.
     * @param element the element to be added.
     * @return true if the element is insert, false otherwise.
     */
    void add(E element);

    /**
     * Removes the giving element from the tree set.
     * @param element the element to be removed.
     * @return true if the element is remove, false otherwise.
     */
    boolean remove(E element);

    /**
     * Returns an iterator.
     * @return iterator.
     */
    Iterator<E> iterator();
}
