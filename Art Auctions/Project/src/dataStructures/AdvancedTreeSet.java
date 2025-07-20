package dataStructures;

abstract class AdvancedTreeSet<E extends Comparable<E>> extends TreeSetClass<E> {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * AdvancedTreeSet constructor. This constructor receives a comparator of an element type E, the Tree set
     * will be arranged according to this comparator.
     * @param comparator  comparator of an element type E.
     * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
     * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
     * @version 1.0.
     */
    protected AdvancedTreeSet(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Returns the node whose element is the specified element;
     * or null if no such node exists.
     * Moreover, stores the path into the stack.
     * @param element to be searched.
     * @param path - Stack of PathStep objects containing all ancestors of node.
     * @return the found node, when the search is successful.
     */
    protected BTNode<E> findNode( E element, Stack<PathStep<E>> path ){
        path.push( new PathStep<>(null, false) );
        BTNode<E> node = root;
        while ( node != null ) {
            E e = node.getElement();
            int compResult = comparator.compare(element, e );
            if ( compResult == 0 )
                return node;
            else if ( compResult < 0 ) {
                path.push( new PathStep<>(node, true) );
                node = node.getLeft();
            } else {
                path.push( new PathStep<>(node, false) );
                node = node.getRight();
            }
        }
        return null;
    }

    /**
     * Returns the node with the smallest element.
     * in the tree rooted at the specified node.
     * Moreover, stores the path into the stack.
     * Requires: theRoot != null.
     *
     * @param theRoot - node that roots the tree.
     * @param path - Stack of PathStep objects containing all ancestors of the minNode.
     * @return node containing the entry with the minimum key.
     */
    protected BTNode<E> minNode( BTNode<E> theRoot, Stack<PathStep<E>> path ){
        BTNode<E> node = theRoot;
        while ( node.getLeft() != null ){
            path.push( new PathStep<>(node, true) );
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Performs a single left rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation.
     * @param leftChild - Left child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateLeft( BTNode<E> theRoot, BTNode<E> leftChild, Stack<PathStep<E>> path ) {
        theRoot.setLeft( leftChild.getRight() );
        leftChild.setRight(theRoot);
        this.linkSubtree(leftChild, path.top());
    }

    /**
     * Performs a single right rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation.
     * @param rightChild - Right child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateRight( BTNode<E> theRoot, BTNode<E> rightChild, Stack<PathStep<E>> path ) {
        theRoot.setRight( rightChild.getLeft() );
        rightChild.setLeft(theRoot);
        this.linkSubtree(rightChild, path.top());
    }

    /**
     * Performs a double left rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation.
     * @param leftChild - Left child of theRoot.
     * @param rightGrandchild - Right child of leftChild.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateLeft( BTNode<E> theRoot, BTNode<E> leftChild, BTNode<E> rightGrandchild, Stack<PathStep<E>> path ) {
        leftChild.setRight( rightGrandchild.getLeft() );
        theRoot.setLeft( rightGrandchild.getRight() );
        rightGrandchild.setLeft(leftChild);
        rightGrandchild.setRight(theRoot);
        this.linkSubtree(rightGrandchild, path.top());
    }

    /**
     * Performs a double right rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * @param theRoot - root of the rotation.
     * @param rightChild - Right child of theRoot.
     * @param leftGrandchild - Left child of rightChild.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateRight( BTNode<E> theRoot, BTNode<E> rightChild,
                                BTNode<E> leftGrandchild, Stack<PathStep<E>> path ) {
        theRoot.setRight( leftGrandchild.getLeft() );
        rightChild.setLeft( leftGrandchild.getRight() );
        leftGrandchild.setLeft(theRoot);
        leftGrandchild.setRight(rightChild);
        this.linkSubtree(leftGrandchild, path.top());
    }
}
