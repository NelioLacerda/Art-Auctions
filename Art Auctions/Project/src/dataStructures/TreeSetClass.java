package dataStructures;

public class TreeSetClass<E extends Comparable<E>> implements TreeSet<E>{

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    protected BTNode<E> root;

    /**
     * Number of entries in the tree.
     */
    protected int currentSize;

    /**
     *
     */
    protected Comparator<E> comparator;

    protected static class PathStep<E> {
        /**
         * The parent of the node.
         */
        public BTNode<E> parent;

        /**
         * The node is the left or the right child of parent.
         */
        public boolean isLeftChild;

        /**
         * PathStep constructor.
         * @param theParent - ancestor of the current node.
         * @param toTheLeft - will be true of the current node is the left child of theParent.
         */
        public PathStep( BTNode<E> theParent, boolean toTheLeft ){
            parent = theParent;
            isLeftChild = toTheLeft;
        }

        /**
         * Method to set node parent before moving in the tree.
         * @param newParent - ancestor of the current node.
         * @param toTheLeft - will be true of the current node is the left child of theParent.
         */
        public void set( BTNode<E> newParent, boolean toTheLeft ){
            parent = newParent;
            isLeftChild = toTheLeft;
        }

    }

    /**
     * TreeSetClass implementation.
     * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
     * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
     * @version 1.0.
     * @param comparator
     */
    public TreeSetClass(Comparator<E> comparator){
        this.comparator = comparator;
        root = null;
        currentSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    protected BTNode<E> findNode(E element, PathStep<E> lastStep){
        BTNode<E> node = root;
        while (node != null) {
            E e = node.getElement();
            int compResult = comparator.compare(element, e);
            if (compResult == 0) return node;
            else if (compResult < 0) {
                lastStep.set(node, true);
                node = node.getLeft();
            } else {
                lastStep.set(node, false);
                node = node.getRight();
            }
        }
        return null;
    }


    protected BTNode<E> findNode( BTNode<E> node, E element ){
        if ( node == null ) return null;
        else {
            int compResult = element.compareTo( node.getElement() );
            if ( compResult == 0 ) return node;
            else if ( compResult < 0 ) return this.findNode(node.getLeft(), element);
            else return this.findNode(node.getRight(), element);
        }
    }

    @Override
    public void add(E element) {
        PathStep<E> lastStep = new PathStep<>(null, false);
        BTNode<E> node = this.findNode(element, lastStep);
        if (node == null) {
            BTNode<E> newLeaf = new BTNode<>(element);
            this.linkSubtree(newLeaf, lastStep);
            currentSize++;
        }
    }

    /**
     * Links a new subtree, rooted at the specified node, to the tree.
     * The parent of the old subtree is stored in lastStep.
     * @param node - root of the subtree.
     * @param lastStep - PathStep object referring to the parent of the old subtree.
     */
    protected void linkSubtree( BTNode<E> node, PathStep<E> lastStep ){
        if ( lastStep.parent == null )
            // Change the root of the tree.
            root = node;
        else
            // Change a child of parent.
            if ( lastStep.isLeftChild ) lastStep.parent.setLeft(node);
            else lastStep.parent.setRight(node);
    }

    /**
     * Returns the node with the smallest key
     * in the tree rooted at the specified node.
     * Moreover, stores the last step of the path in lastStep.
     * Requires: theRoot != null.
     * @param theRoot - node that roots the tree.
     * @param lastStep - Pathstep object to refer to the parent of theRoot.
     * @return node containing the entry with the minimum key.
     */
    protected BTNode<E> minNode( BTNode<E> theRoot, PathStep<E> lastStep ){
        BTNode<E> node = theRoot;
        while ( node.getLeft() != null ) {
            lastStep.set(node, true);
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public boolean remove(E element) {
        PathStep<E> lastStep = new PathStep<>(null, false);
        BTNode<E> node = this.findNode(element, lastStep);
        if (node == null) return false;
        else {
            if (node.getLeft() == null)
                //The left subtree is empty.
                this.linkSubtree(node.getRight(), lastStep);
            else if (node.getRight() == null)
                //The right subtree is empty.
                this.linkSubtree(node.getLeft(), lastStep);
            else{
                // Node has 2 children. Replace the node's entry with
                // the 'minEntry' of the right subtree.
                lastStep.set(node, false);
                BTNode<E> minNode = this.minNode(node.getRight(), lastStep);
                node.setElement(minNode.getElement());
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), lastStep);
            }
            currentSize--;
            return true;
        }
    }

    /**
     * Returns an iterator of the Tree Set.
     * @return an iterator of the Tree Set.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeSetIterator<E>(root);
    }
}
