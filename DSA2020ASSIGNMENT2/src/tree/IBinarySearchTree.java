/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.List;

/**
 * @author LTSACH
 */
public interface IBinarySearchTree<T> {
    public void add(T item);

    public void remove(Object key);

    public T search(Object key);

    public int size();

    public List<T> ascendingList();

    public List<T> descendingList();
}
