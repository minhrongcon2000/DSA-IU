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
public interface ITreeWalker<T> {
    public List<T> dfs();

    public List<T> bfs();

    public List<T> nlr();

    public List<T> lrn();

    public List<T> lnr();
}
