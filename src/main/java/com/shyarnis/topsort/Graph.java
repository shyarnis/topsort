package com.shyarnis.topsort;

import java.util.*;

public class Graph<T> {
    // key: T as node
    // values: set<T> for neighbours
    private final Map<T, Set<T>> adjacencyList = new HashMap<>();

    // add node, if absent
    public void addNode(T node) {
        adjacencyList.putIfAbsent(node, new HashSet<>());
    }

    // add directed edge, (from -> to)
    public void addEdge(T from, T to) {
        addNode(from);
        addNode(to);
        adjacencyList.get(from).add(to);
    }

    // set of all nodes in that graph
    public Set<T> getNodes() {
        return adjacencyList.keySet();
    }

    // return neighbours of given node; if node does not exist, return empty set
    public Set<T> getNeighbors(T node) {
        return adjacencyList.getOrDefault(node, Collections.emptySet());
    }

    // return full adjacency list
    public Map<T, Set<T>> getAdjacencyList() {
        return Collections.unmodifiableMap(adjacencyList);
    }
}
