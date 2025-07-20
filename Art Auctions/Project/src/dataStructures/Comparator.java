package dataStructures;

import java.io.Serializable;

/**
 * Comparator Interface.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 * @version 1.0.
 * @param <E> Generic Element.
 */
public interface Comparator<E> extends Serializable {

    /**
     * Compares its two arguments for order.
     * Returns a negative integer, zero or a positive integer
     * as the first argument is less than, equal to, or greater than the second.
     * @param element1 element1.
     * @param element2 element2.
     * @return a negative integer, zero or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     */
    int compare(E element1, E element2 );
}
