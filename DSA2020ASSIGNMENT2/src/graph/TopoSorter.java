package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TopoSorter<T> {
    public static final int DFS = 0;
    public static final int BFS = 1;
    protected DGraphModel<T> graph;

    public TopoSorter(DGraphModel<T> graph) {
        this.graph = graph;
    }

    public List<T> sort() {
        return sort(DFS);
    }

    public List<T> sort(int mode) {
        if (mode == DFS) return dfsSort();
        else return bfsSort();
    }

    public List<T> bfsSort() {
        List<T> topoOrder = new LinkedList<>();
        HashMap<T, Integer> indegreeMap = vertex2inDegree();
        List<T> list = listOfZeroInDegrees();

        Queue<T> queue = new Queue<>();
        for (T item : list) queue.push(item);

        /*YOUR CODE HERE*/
        while (!queue.isEmpty()) {
            T v = queue.peek();
            queue.pop();
            topoOrder.add(v);
            List<T> neighbors = this.graph.getOutwardEdges(v);
            for (T neighborNode : neighbors) {
                int inDegree = indegreeMap.get(neighborNode);
                if (inDegree == 1) {
                    queue.push(neighborNode);
                }
                indegreeMap.put(neighborNode, inDegree - 1);
            }
        }

        return topoOrder;
    }

    public List<T> dfsSort() {
        List<T> topoOrder = new LinkedList<>();
        HashMap<T, Integer> outdegreeMap = vertex2outDegree();
        List<T> list = listOfZeroInDegrees();

        Stack<T> stack = new Stack<>();
        for (T item : list) stack.push(item);

        while (!stack.isEmpty()) {
            T vertex = stack.peek();
            int outDegree = outdegreeMap.get(vertex);
            if (outDegree == 0) {
                stack.pop();
                topoOrder.add(0, vertex);
            } else {
                List<T> neighbors = this.graph.getOutwardEdges(vertex);
                for (T v : neighbors) {
                    if (stack.contains(v)) {
                        stack.remove(v);
                        stack.push(v);
                    }
                    if (!stack.contains(v) && !topoOrder.contains(v))
                        stack.push(v);
                    outdegreeMap.put(vertex, outDegree - 1);
                }
            }
        }//while: stack not empty
        return topoOrder;
    }

    private HashMap<T, Integer> vertex2inDegree() {
        HashMap<T, Integer> map = new HashMap<>();
        Iterator<T> vertexIt = this.graph.iterator();
        while (vertexIt.hasNext()) {
            T vertex = vertexIt.next();
            int inDegree = this.graph.inDegree(vertex);
            map.put(vertex, inDegree);
        }
        return map;
    }

    private HashMap<T, Integer> vertex2outDegree() {
        HashMap<T, Integer> map = new HashMap<>();
        Iterator<T> vertexIt = this.graph.iterator();
        while (vertexIt.hasNext()) {
            T vertex = vertexIt.next();
            int outDegree = this.graph.outDegree(vertex);
            map.put(vertex, outDegree);
        }
        return map;
    }

    private List<T> listOfZeroInDegrees() {
        List<T> list = new LinkedList<>();
        Iterator<T> vertexIt = this.graph.iterator();
        while (vertexIt.hasNext()) {
            T vertex = vertexIt.next();
            int inDegree = this.graph.inDegree(vertex);
            if (inDegree == 0) list.add(vertex);
        }
        return list;
    }
}
