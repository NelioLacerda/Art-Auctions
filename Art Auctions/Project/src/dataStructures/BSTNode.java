package dataStructures;

import java.io.Serializable;

/**
 * BST node implementation.
 * @version 1.0.
 * @param <K> Generic type Key.
 * @param <V> Generic type Value.
 */
class BSTNode<K, V> implements Serializable {

     /**
      * Serial Version UID of the Class.
      */
     static final long serialVersionUID = 0L;

    /**
     * Entry stored in the node.
     */
    private EntryClass<K, V> entry;

    /**
     * (Pointer to) the left child.
     */
    private BSTNode<K ,V> leftChild;

    /**
     * (Pointer to) the right child.
     */
    private BSTNode<K ,V> rightChild;

     /**
      * Constructor for BST nodes.
      * @param key to be stored in this BST tree node.
      * @param value to be stored in this BST tree node.
      * @param left sub-tree of this node.
      * @param right sub-tree of this node.
      */
    public BSTNode(K key, V value, BSTNode<K ,V> left, BSTNode<K, V> right){
        entry = new EntryClass<>(key, value);
        leftChild = left;
        rightChild = right;
    }

     /**
      * Constructor for BST nodes.
      * @param key to be stored in this BST tree node.
      * @param value to be stored in this BST tree node.
      */
    public BSTNode(K key, V value){
        this(key, value, null, null);
    }

     /**
      * Returns the entry (key and value) of the current node.
      * @return the entry (key and value) of the current node.
      */
    public EntryClass<K, V> getEntry(){
        return entry;
    }

     /**
      * Returns the key of the current node.
      * @return the key of the current node.
      */
    public K getKey(){
        return entry.getKey();
    }

     /**
      * Returns the value of the current node.
      * @return the value of the current node.
      */
    public V getValue(){
        return entry.getValue();
    }

     /**
      * Returns the left child node of the current node.
      * @return the left child node of the current node.
      */
    public BSTNode<K, V> getLeft(){
        return leftChild;
    }

     /**
      * Returns the right child node of the current node.
      * @return the right child node of the current node.
      */
     public BSTNode<K,V> getRight( ){
         return rightChild;
     }

     /**
      * Assigns a new entry (key and value) to the current BST node.
      * @param newEntry new entry (key and value).
      */
     public void setEntry(EntryClass<K, V> newEntry ){
         entry = newEntry;
     }

     /**
      * Assigns new a key and value to the current BST node.
      * @param newKey new key.
      * @param newValue new value.
      */
     public void setEntry(K newKey, V newValue){
         entry.setKey(newKey);
         entry.setValue(newValue);
     }

     /**
      * Sets the new key of the current node.
      * @param newKey new key.
      */
    public void setKey(K newKey){
         entry.setKey(newKey);
    }

     /**
      * Sets the new value object of the current node.
      * @param newValue new value.
      */
     public void setValue( V newValue ) {
         entry.setValue(newValue);
     }

     /**
      * Sets the new left child node of this node.
      * @param newLeft the new left child node of the current node.
      */
     public void setLeft( BSTNode<K,V> newLeft ) {
         leftChild = newLeft;
     }

     /**
      * Sets the new right child node of this node.
      * @param newRight the new right child node of the current node.
      */
     public void setRight( BSTNode<K,V> newRight ){
         rightChild = newRight;
     }

     /**
      * Returns true if the node is a leaf.
      * @return true if the node is a leaf, false otherwise.
      */
     public boolean isLeaf( ) {
         return leftChild == null && rightChild == null;
     }
}
