package dataStructures;

import dataStructures.exceptions.EmptyStackException;

/**
 * Stack In List Implementation
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class StackInList<E> implements Stack<E> {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
    /**
     * Memory of the stack: a list.
     */
    protected List<E> list;

    public StackInList( )
    {     
        list = new DoubleList<E>();
    }

    public boolean isEmpty( )
    {     
        return list.isEmpty();
    }

    public int size( )
    {     
        return list.size();
    }

    public E top( ) throws EmptyStackException {
        if ( list.isEmpty() )
            throw new EmptyStackException();
        return list.getFirst();
    }

    public void push( E element )
    { 
        list.addFirst(element);
    }

    public E pop() throws EmptyStackException {
        if ( list.isEmpty() )
            throw new EmptyStackException();
        return list.removeFirst();
    }

    public Iterator<E> iterator(){
        return list.iterator();
    }
}
