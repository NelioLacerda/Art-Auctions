package dataStructures;

public class AVLTreeSet<E extends Comparable<E>> extends AdvancedTreeSet<E> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * AVLTreeSet constructor. This constructor receives a comparator of an element type E, the Tree set
     * will be arranged according to this comparator.
     * @param comparator comparator of an element type E.
     * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
     * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
     * @version 1.0.
     */
    public AVLTreeSet(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public void add(E element) {
        Stack<PathStep<E>> path = new StackInList<>();
        BTNode<E> node = this.findNode(element, path);
        if ( node == null ) {
            AVLSetNode<E> newLeaf = new AVLSetNode<>(element);
            this.linkSubtree(newLeaf, path.top());
            currentSize++;
            this.reorganizeIns(path);
        }
    }

    /**
     * Every ancestor of the new leaf is stored in the stack,
     * which is not empty.
     * @param path - Stack of PathStep objects containing all ancestors of the inserted node.
     */
    protected void reorganizeIns( Stack<PathStep<E>> path ) {
        boolean grew = true;
        PathStep<E> lastStep = path.pop();
        AVLSetNode<E> parent = (AVLSetNode<E>) lastStep.parent;
        while ( grew && parent != null ) {
            if (lastStep.isLeftChild)
                // parent's left subtree has grown.
                switch (parent.getBalance()) {
                    case 'L':
                        this.rebalanceInsLeft(parent, path);
                        grew = false;
                        break;
                    case 'E':
                        parent.setBalance('L');
                        break;
                    case 'R':
                        parent.setBalance('E');
                        grew = false;
                        break;
                }
            else
                // parent's right subtree has grown.
                switch (parent.getBalance()) {
                    case 'L':
                        parent.setBalance('E');
                        grew = false;
                        break;
                    case 'E':
                        parent.setBalance('R');
                        break;
                    case 'R':
                        this.rebalanceInsRight(parent, path);
                        grew = false;
                        break;
                }
            lastStep = path.pop();
            parent = (AVLSetNode<E>) lastStep.parent;

        }
    }

    /**
     * Every ancestor of node is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param node - root of subtree to balance.
     * @param path - Stack of PathStep objects containing all ancestors of node.
     */
    protected void rebalanceInsLeft( AVLSetNode<E> node, Stack<PathStep<E>> path ) {
        AVLSetNode<E> leftChild = (AVLSetNode<E>) node.getLeft();
        switch ( leftChild.getBalance() ) {
            case 'L':
                this.rotateLeft1L(node, leftChild, path);//rotação simples
                break;
            // case 'E':
            //     Impossible.
            case 'R':
                this.rotateLeft2(node, leftChild, path);//rotação dupla
                break;
        }
    }

    /**
     * Every ancestor of node is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     * @param node - root of subtree to balance.
     * @param path - Stack of PathStep objects containing all ancestors of node.
     */
    protected void rebalanceInsRight( AVLSetNode<E> node, Stack<PathStep<E>> path ) {
        AVLSetNode<E> rightChild = (AVLSetNode<E>) node.getRight();
        switch ( rightChild.getBalance() )
        {
            case 'L':
                this.rotateRight2(node, rightChild, path);//rotação dupla
                break;
            // case 'E':
            //     Impossible.
            case 'R':
                this.rotateRight1R(node, rightChild, path);//rotação simples
                break;
        }
    }

    @Override
    public boolean remove(E element) {
        Stack<PathStep<E>> path = new StackInList<>();
        BTNode<E> node = this.findNode(element, path);
        if ( node == null )
            return false;
        else {
            E oldElement = node.getElement();
            if ( node.getLeft() == null )
                // The left subtree is empty.
                this.linkSubtree(node.getRight(), path.top());
            else if ( node.getRight() == null )
                // The right subtree is empty.
                this.linkSubtree(node.getLeft(), path.top());
            else {
                // Node has 2 children. Replace the node's entry with the 'minEntry' of the right subtree.
                path.push( new PathStep<>(node, false) );
                BTNode<E> minNode = this.minNode(node.getRight(), path);
                node.setElement( minNode.getElement() );
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), path.top());
            }
            currentSize--;
            this.reorganizeRem(path);
            return true;
        }
    }

    /**
     * Every ancestor of the removed node is stored in the stack,
     * which is not empty.
     * @param path - Stack of PathStep objects containing all ancestors of the inserted node.
     */
    protected void reorganizeRem( Stack<PathStep<E>> path ) {
        PathStep<E> lastStep = path.pop(); //nó removido
        AVLSetNode<E> parent = (AVLSetNode<E>) lastStep.parent; //nó do pai do removido
        boolean decrease = true;
        while ( decrease && parent != null ) {
            if (lastStep.isLeftChild)
                // parent's left subtree has decrease.
                switch (parent.getBalance()) {
                    case 'L':
                        parent.setBalance('E');
                        break;
                    case 'E':
                        parent.setBalance('R');
                        decrease = false;
                        break;
                    case 'R':
                        decrease = this.rebalanceRemRight(parent, path);
                        break;
                }
            else
                // parent's right subtree has decrease.
                switch (parent.getBalance()) {
                    case 'L':
                        decrease = this.rebalanceRemLeft(parent, path);
                        break;
                    case 'E':
                        parent.setBalance('L');
                        decrease = false;
                        break;
                    case 'R':
                        parent.setBalance('E');
                        break;
                }
            lastStep = path.pop();
            parent = (AVLSetNode<E>) lastStep.parent;

        }
    }

    /**
     * Checks the type of rotation to the right to be made according to the balance of parent right child.
     * @param parent parent of the remove node.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     * @return true if the tree has decrease, false otherwise.
     */
    protected boolean rebalanceRemRight( AVLSetNode<E> parent, Stack<PathStep<E>> path ) {
        AVLSetNode<E> rightChild = (AVLSetNode<E>) parent.getRight();
        boolean decrease = true;
        switch ( rightChild.getBalance() ) {
            case 'L':
                this.rotateRight2(parent, rightChild, path);//rotação dupla
                break;
            case 'E':
                this.rotateRight1E(parent, rightChild, path);
                decrease = false;
                break;
            case 'R':
                this.rotateRight1R(parent, rightChild, path);//rotação simples
                break;
        }
        return decrease;
    }

    /**
     * Checks the type of rotation to the left to be made according to the balance of parent left child.
     * @param parent parent of the remove node.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     * @return true if the tree has decrease, false otherwise.
     */
    protected boolean rebalanceRemLeft( AVLSetNode<E> parent, Stack<PathStep<E>> path ) {
        AVLSetNode<E> leftChild = (AVLSetNode<E>) parent.getLeft();
        boolean decrease = true;
        switch ( leftChild.getBalance() ) {
            case 'L':
                this.rotateLeft1L(parent, leftChild, path);//rotação simples
                break;
            case 'E':
                this.rotateLeft1E(parent, leftChild, path);
                decrease = false;
                break;
            case 'R':
                this.rotateLeft2(parent, leftChild, path);//rotação dupla
                break;
        }
        return decrease;
    }

    /**
     * Performs a single left rotation rooted at theRoot,
     * when the balance factor of its leftChild is 'L'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param theRoot - root of the rotation.
     * @param leftChild - Left child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateLeft1L( AVLSetNode<E> theRoot, AVLSetNode<E> leftChild, Stack<PathStep<E>> path ) {
        theRoot.setBalance('E');
        leftChild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, path);
    }

    /**
     * Performs a single left rotation rooted at theRoot,
     * when the balance factor of its leftChild is 'E'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param theRoot - root of the rotation.
     * @param leftChild - Left child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateLeft1E( AVLSetNode<E> theRoot, AVLSetNode<E> leftChild, Stack<PathStep<E>> path ) {
        // theRoot.setBalance('L');
        leftChild.setBalance('R');
        this.rotateLeft(theRoot, leftChild, path);
    }

    /**
     * Performs a single right rotation rooted at theRoot,
     * when the balance factor of its rightChild is 'R'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     * @param theRoot - root of the rotation.
     * @param rightChild - Right child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateRight1R(  AVLSetNode<E> theRoot, AVLSetNode<E> rightChild, Stack<PathStep<E>> path ) {
        theRoot.setBalance('E');
        rightChild.setBalance('E');
        this.rotateRight(theRoot, rightChild, path);
    }

    /**
     * Performs a single right rotation rooted at theRoot,
     * when the balance factor of its rightChild is 'E'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     * @param theRoot - root of the rotation.
     * @param rightChild - Right child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateRight1E( AVLSetNode<E> theRoot, AVLSetNode<E> rightChild, Stack<PathStep<E>> path ) {
        // theRoot.setBalance('R');
        rightChild.setBalance('L');
        this.rotateRight(theRoot, rightChild, path);
    }

    /**
     * Performs a double left rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param theRoot - root of the rotation.
     * @param leftChild - Left child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateLeft2( AVLSetNode<E> theRoot, AVLSetNode<E> leftChild, Stack<PathStep<E>> path ) {
        AVLSetNode<E> rightGrandchild = (AVLSetNode<E>) leftChild.getRight();
        switch ( rightGrandchild.getBalance() ) {
            case 'L':
                leftChild.setBalance('E');
                theRoot.setBalance('R');
                break;
            case 'E':
                leftChild.setBalance('E');
                theRoot.setBalance('E');
                break;
            case 'R':
                leftChild.setBalance('L');
                theRoot.setBalance('E');
                break;
        }
        rightGrandchild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, rightGrandchild, path);
    }

    /**
     * Performs a double right rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     * @param theRoot - root of the rotation.
     * @param rightChild - Right child of theRoot.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     */
    protected void rotateRight2(  AVLSetNode<E> theRoot, AVLSetNode<E> rightChild, Stack<PathStep<E>> path ) {
        AVLSetNode<E> leftGrandchild = (AVLSetNode<E>) rightChild.getLeft();
        switch ( leftGrandchild.getBalance() ) {
            case 'L':
                theRoot.setBalance('E');
                rightChild.setBalance('R');
                break;
            case 'E':
                theRoot.setBalance('E');
                rightChild.setBalance('E');
                break;
            case 'R':
                theRoot.setBalance('L');
                rightChild.setBalance('E');
                break;
        }
        leftGrandchild.setBalance('E');
        this.rotateRight(theRoot, rightChild, leftGrandchild, path);
    }
}
