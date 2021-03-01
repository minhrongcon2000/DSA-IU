/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author LTSACH
 */

class ItemNotFoundException extends RuntimeException {
    private Object itemInfo;

    public ItemNotFoundException(Object itemInfo) {
        this.itemInfo = itemInfo;
    }

    @Override
    public String toString() {
        String message = String.format("The following vertex is not found: %s", itemInfo);
        return message;
    }
}

class VertexNotFoundException extends ItemNotFoundException {
    public VertexNotFoundException(Object vertexInfo) {
        super(vertexInfo);
    }
}

class EdgeNotFoundException extends ItemNotFoundException {
    public EdgeNotFoundException(Object vertexInfo) {
        super(vertexInfo);
    }
}

public interface IGraph<T> {
    public void add(T vertex);

    public void remove(T vertex) throws VertexNotFoundException;

    public boolean contains(T vertex);

    public void connect(T from, T to) throws VertexNotFoundException;

    public void connect(T from, T to, float weight) throws VertexNotFoundException;

    public void disconnect(T from, T to) throws VertexNotFoundException, EdgeNotFoundException;

    public float weight(T from, T to) throws VertexNotFoundException, EdgeNotFoundException;

    public java.util.List<T> getOutwardEdges(T from) throws VertexNotFoundException;

    public java.util.List<T> getInwardEdges(T to) throws VertexNotFoundException;

    public java.util.Iterator<T> iterator();

    public int size();

    public int inDegree(T vertex) throws VertexNotFoundException;

    public int outDegree(T vertex) throws VertexNotFoundException;

    public void println();
}


//
class Path<T> {
    List<T> path;
    float cost;

    public Path() {
        this.path = new LinkedList<>();
        this.cost = 0;
    }

    void add(T item) {
        this.path.add(item);
    }

    public String toString() {
        String desc = "[";
        ListIterator<T> it = this.path.listIterator();
        int lastIdx = this.path.size() - 1;
        while (it.hasNext()) {
            int nextIdx = it.nextIndex();
            T item = it.next();
            if (nextIdx < lastIdx) desc += String.format("%s,", item);
            else desc += String.format("%s", item);
        }
        desc += "]";
        desc += String.format(": %6.2f", this.cost);
        return desc;
    }
}

interface IFinder<T> {
    public List<Path> dijkstra(IGraph<T> graph, T start);
}