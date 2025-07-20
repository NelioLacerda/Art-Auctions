package dataStructures;

public class AVLSetNode<E extends Comparable<E>> extends BTNode<E>{

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The balance factor of the tree rooted at the node,
     * which is:
     * 'E'  if  height( node.getLeft() ) = height( node.getRight() );
     * 'L'  if  height( node.getLeft() ) = height( node.getRight() ) + 1;
     * 'R'  if  height( node.getLeft() ) = height( node.getRight() ) - 1.
     */
    private char balanceFactor;

    /**
     * Constructor of the AVLSetNode.
     * @param theElement to be stored in this AVL Tree Set node.
     * @param balance is Left, Right or Equally balanced.
     * @param theLeft sub-tree of this node.
     * @param theRight sub-tree of this node.
     */
    public AVLSetNode(E theElement, char balance, BTNode<E> theLeft, BTNode<E> theRight) {
        super(theElement, theLeft, theRight);
        balanceFactor = balance;
    }

    /**
     * Constructor of the AVLSetNode.
     * @param theElement to be stored in this AVL Tree Set node..
     */
    public AVLSetNode(E theElement) {
        this(theElement, 'E', null, null);
    }

    /**
     * Returns the balance of the sub-tree below this node.
     * @return balanceFactor - the balance factor of the sub-tree.
     */
    public char getBalance( ){
        return balanceFactor;
    }

    /**
     * Sets the new balance of the sub-tree below this node.
     * @param newBalance - the new balance factor of the sub-tree.
     */
    public void setBalance( char newBalance ){
        balanceFactor = newBalance;
    }
}
