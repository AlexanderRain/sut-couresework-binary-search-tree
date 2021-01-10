package coursework.model;

import java.util.List;

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {

    @Override /** Inorder traversal from the root*/
    public abstract List<E> inorder();

    @Override /** Postorder traversal from the root */
    public abstract List<E> postorder();

    @Override /** Preorder traversal from the root */
    public abstract List<E> preorder();

    @Override /** Return true if the tree is empty */
    public boolean isEmpty() {
        return getSize() == 0;
    }
//
//  @Override /** Return an iterator for the tree */
//  public java.util.Iterator<E> iterator() {
//    return null;
//  }
}
