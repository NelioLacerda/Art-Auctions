package dataStructures;                                         

/**
 * AVL tree implementation.
 *
 * @version 1.0
 *
 * @param <K> Generic type Key, must extend comparable.
 * @param <V> Generic type Value.
 */
public class AVLTree<K extends Comparable<K>, V> extends AdvancedBSTree<K,V> {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;


    @Override
    public V insert( K key, V value ){
        Stack<PathStep<K,V>> path = new StackInList<>();
        BSTNode<K,V> node = this.findNode(key, path);
        if ( node == null ) {
            AVLNode<K,V> newLeaf = new AVLNode<>(key, value);
            this.linkSubtree(newLeaf, path.top());
            currentSize++;
            this.reorganizeIns(path);
            return null;   
        } else {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
    }


    /**
     * Every ancestor of the new leaf is stored in the stack, 
     * which is not empty.
     * @param path - Stack of PathStep objects containing all ancestors of the inserted node
     */
    protected void reorganizeIns( Stack<PathStep<K,V>> path ) {
        boolean grew = true;
        PathStep<K, V> lastStep = path.pop();
        AVLNode<K, V> parent = (AVLNode<K, V>) lastStep.parent;
        while (grew && parent != null) {
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
            else // parent's right subtree has grown.
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
            parent = (AVLNode<K, V>) lastStep.parent;
        }
    }


    /**
     * Every ancestor of node is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param node - root of subtree to balance
     * @param path - Stack of PathStep objects containing all ancestors of node
     */
    protected void rebalanceInsLeft( AVLNode<K,V> node, 
        Stack<PathStep<K,V>> path ) {
        AVLNode<K,V> leftChild = (AVLNode<K,V>) node.getLeft();
        switch ( leftChild.getBalance() )
        {
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
     * @param node - root of subtree to balance
     * @param path - Stack of PathStep objects containing all ancestors of node
     */
    protected void rebalanceInsRight( AVLNode<K,V> node, Stack<PathStep<K,V>> path )
    {
        AVLNode<K,V> rightChild = (AVLNode<K,V>) node.getRight();
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
    public V remove( K key ) {
        Stack<PathStep<K,V>> path = new StackInList<>();
        BSTNode<K,V> node = this.findNode(key, path);
        if ( node == null )
            return null;
        else {
            V oldValue = node.getValue();
            if ( node.getLeft() == null )
                // The left subtree is empty.
                this.linkSubtree(node.getRight(), path.top());
            else if ( node.getRight() == null )
                // The right subtree is empty.
                this.linkSubtree(node.getLeft(), path.top());
            else {
                // Node has 2 children. Replace the node's entry with the 'minEntry' of the right subtree.
                path.push( new PathStep<>(node, false) );
                BSTNode<K,V> minNode = this.minNode(node.getRight(), path);
                node.setEntry( minNode.getEntry() );
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), path.top());
            }
            currentSize--;
            this.reorganizeRem(path);
            return oldValue;
        }
    }


    /**
     * Every ancestor of the removed node is stored in the stack, 
     * which is not empty.
     * @param path - Stack of PathStep objects containing all ancestors of the inserted node.
     */
    protected void reorganizeRem( Stack<PathStep<K,V>> path ) {
        PathStep<K, V> lastStep = path.pop(); //nó removido
        AVLNode<K, V> parent = (AVLNode<K,V>) lastStep.parent; //nó do pai do removido
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
            else // parent's right subtree has decrease.
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
            parent = (AVLNode<K, V>) lastStep.parent;

        }
    }

    /**
     * Checks the type of rotation to the right to be made according to the balance of parent right child.
     * @param parent parent of the remove node.
     * @param path - Stack of PathStep objects containing all ancestors of theRoot.
     * @return true if the tree has decrease, false otherwise.
     */
    protected boolean rebalanceRemRight( AVLNode<K,V> parent, Stack<PathStep<K,V>> path ) {
        AVLNode<K,V> rightChild = (AVLNode<K,V>) parent.getRight();
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
    protected boolean rebalanceRemLeft( AVLNode<K,V> parent, Stack<PathStep<K,V>> path ) {
        AVLNode<K,V> leftChild = (AVLNode<K,V>) parent.getLeft();
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
     * @param theRoot - root of the rotation
     * @param leftChild - Left child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateLeft1L( AVLNode<K,V> theRoot, AVLNode<K,V> leftChild, 
        Stack<PathStep<K,V>> path ) {
        theRoot.setBalance('E');
        leftChild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, path);
    }


    /**
     * Performs a single left rotation rooted at theRoot,
     * when the balance factor of its leftChild is 'E'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param theRoot - root of the rotation
     * @param leftChild - Left child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateLeft1E( AVLNode<K,V> theRoot, AVLNode<K,V> leftChild, 
        Stack<PathStep<K,V>> path )
    {
     // theRoot.setBalance('L');
        leftChild.setBalance('R');
        this.rotateLeft(theRoot, leftChild, path);
    }


    /**
     * Performs a single right rotation rooted at theRoot,
     * when the balance factor of its rightChild is 'R'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     * @param theRoot - root of the rotation
     * @param rightChild - Right child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot 
     */
    protected void rotateRight1R( AVLNode<K,V> theRoot, AVLNode<K,V> rightChild, Stack<PathStep<K,V>> path ) {
        theRoot.setBalance('E');
        rightChild.setBalance('E');
        this.rotateRight(theRoot, rightChild, path);
    }


    /**
     * Performs a single right rotation rooted at theRoot,
     * when the balance factor of its rightChild is 'E'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     * @param theRoot - root of the rotation
     * @param rightChild - Right child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot 
     */
    protected void rotateRight1E( AVLNode<K,V> theRoot, AVLNode<K,V> rightChild, Stack<PathStep<K,V>> path ) {
     // theRoot.setBalance('R');
        rightChild.setBalance('L');
        this.rotateRight(theRoot, rightChild, path);
    }


    /**
     * Performs a double left rotation rooted at theRoot.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     * @param theRoot - root of the rotation
     * @param leftChild - Left child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateLeft2( AVLNode<K,V> theRoot, AVLNode<K,V> leftChild, Stack<PathStep<K,V>> path ) {
        AVLNode<K,V> rightGrandchild = (AVLNode<K,V>) leftChild.getRight();
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
     * @param theRoot - root of the rotation
     * @param rightChild - Right child of theRoot
     * @param path - Stack of PathStep objects containing all ancestors of theRoot 
     */
    protected void rotateRight2( AVLNode<K,V> theRoot, AVLNode<K,V> rightChild, Stack<PathStep<K,V>> path ) {
        AVLNode<K,V> leftGrandchild = (AVLNode<K,V>) rightChild.getLeft();
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
