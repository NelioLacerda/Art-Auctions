package dataStructures;

import dataStructures.exceptions.EmptyDictionaryException;

/**
 * Doubly linked list Implementation.
 * @version 1.0.
 * @param <K> Generic Key that extends Comparable.
 * @param <V> Generic Value.
 */
class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V>{
     /**
      * Serial Version UID of the Class
      */
     static final long serialVersionUID = 0L;

     /**
      *  Node at the head of the list.
      */
     protected DoubleListNode<Entry<K,V>> head;

     /**
      * Node at the tail of the list.
      */
     protected DoubleListNode<Entry<K,V>> tail;

     /**
      * Number of elements in the list.
      */
     protected int currentSize;

     /**
      * Constructor of an empty ordered double linked list.
      * head and tail are initialized as null.
      * currentSize is initialized as 0.
      */
     public OrderedDoubleList() {
         head=null;
         tail=null;
         currentSize=0;
     }

     /**
      * Inserts the Entry element before node after.
      * Precondition: after is not the head of the ordered double list.
      * @param element - Entry to be inserted.
      * @param after - Node to be next to the new node.
      */
     protected void addBeforeNode(Entry<K,V> element, DoubleListNode<Entry<K,V>> after){
         DoubleListNode<Entry<K, V>> previousNode = after.getPrevious();
         DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(element, previousNode, after);
         previousNode.setNext(newNode);
         after.setPrevious(newNode);
         currentSize++;
     }

     /**
      * Inserts the Entry element at the first position in the list.
      * @param element - Entry to be inserted.
      */
     protected void addFirst( Entry<K,V> element){
         DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(element, null, head);
         if ( this.isEmpty() ) tail = newNode;
         else head.setPrevious(newNode);
         head = newNode;
         currentSize++;
     }

     /**
      * Inserts the Entry element at the last position in the list.
      * @param element - Entry to be inserted.
      */
     protected void addLast( Entry<K,V> element){
         DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(element, tail, null);
         if (this.isEmpty()) head = newNode;
         else tail.setNext(newNode);
         tail = newNode;
         currentSize++;
     }

     @Override
     public Entry<K, V> maxEntry() throws EmptyDictionaryException {//na modificada isto pode funcionar
         if (this.isEmpty()) throw new EmptyDictionaryException();
         return tail.getElement();
     }

     @Override
     public Entry<K, V> minEntry() throws EmptyDictionaryException{
         if (this.isEmpty()) throw new EmptyDictionaryException();
         return head.getElement();
     }

     /**
      * Returns the node with the Entry with key in the list, if the list contains this entry.
      * Otherwise, returns null.
      * @param key - Key of type K to be searched.
      * @return DoubleListNode<E> where the Entry with key was found, or the one with the key immediately after.
      */
     protected DoubleListNode<Entry<K,V>> findNode (K key){
         DoubleListNode<Entry<K, V>> node = head;
         while (node != null && node.getElement().getKey().compareTo(key) < 0)
             node = node.getNext();
         return node;
     }

     @Override
     public V find(K key){
         DoubleListNode<Entry<K,V>> node = findNode(key);
         if (node != null) return node.getElement().getValue();
         else return null;
     }

     /*
     head -> valor minimo
     tail -> valor maximo
      */
     @Override
     public V insert(K key, V value) {
         DoubleListNode<Entry<K,V>> node = findNode(key);
         if (node != null && node.getElement().getKey().compareTo(key) == 0) {
             V oldValue = node.getElement().getValue();
             node.setElement(new EntryClass<>(key, value));
             return oldValue;
         } else {
             Entry<K, V> newEntry = new EntryClass<>(key, value);
             if (node == head) addFirst(newEntry);
             else if(node == null) addLast(newEntry);
             else this.addBeforeNode(newEntry, node);
             return null;
         }
     }

     @Override
     public boolean isEmpty(){
         return currentSize==0;
     }

     @Override
     public Iterator<Entry<K, V>> iterator(){
         return new DoubleListIterator<>(head,tail);
     }

     /**
      * Removes the first node in the list.
      * Pre-condition: the list is not empty.
      */
     protected void removeFirstNode( ){
         head = head.getNext();
         if ( head == null ) tail = null;
         else head.setPrevious(null);
         currentSize--;
     }

     /**
      * Removes and returns the value at the first entry in the list.
      */
     protected V removeFirst( ) throws EmptyDictionaryException{
         if (this.isEmpty()) throw new EmptyDictionaryException();
         V oldValue = head.getElement().getValue();
         removeFirstNode();
         return oldValue;
     }

     /**
      * Removes the last node in the list.
      * Pre-condition: the list is not empty.
      */
     protected void removeLastNode( ){
         tail = tail.getPrevious();
         if (tail == null) head = null;
         else tail.setNext(null);
         currentSize--;
     }

     /**
      * Removes and returns the value at the last entry in the list.
      */
     protected V removeLast( ) throws EmptyDictionaryException{
         if ( this.isEmpty() ) throw new EmptyDictionaryException();
         V oldValue = tail.getElement().getValue();
         this.removeLastNode();
         return oldValue;
     }

     /**
      * Removes the specified node from the list.
      * Pre-condition: the node is neither the head nor the tail of the list.
      * @param node - middle node to be removed.
      */
     protected void removeMiddleNode( DoubleListNode<Entry<K,V>> node ){
         DoubleListNode<Entry<K, V>> previousNode = node.getPrevious();
         DoubleListNode<Entry<K, V>> nextNode = node.getNext();
         previousNode.setNext(nextNode);
         nextNode.setPrevious(previousNode);
         currentSize--;
     }

     @Override
     public V remove(K key){
         DoubleListNode<Entry<K,V>> node = findNode(key);
         if ((node == null) || (node.getElement().getKey().compareTo(key)!=0))
             return null;
         else {
             if (node == head) return this.removeFirst();
             else if (node == tail) return this.removeLast();
             else{
                 removeMiddleNode(node);
                 return node.getElement().getValue();
             }
         }
     }

     @Override
     public int size(){
         return currentSize;
     }
}
