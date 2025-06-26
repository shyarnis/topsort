package com.shyarnis.topsort;

import java.util.*;


public class TopologicalSort<T> {
    private final Graph<T> graph;

    public TopologicalSort(Graph<T> graph) {
        this.graph = graph;
    }


    public List<T> sort() {
        Map<T, Integer> inDegree = computeInDegrees();
        Queue<T> queue = new LinkedList<>();
        List<T> result = new ArrayList<>();

        // Initialize queue with nodes having 0 in-degree
        for (T node : graph.getNodes()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.add(current);

            for (T neighbor : graph.getNeighbors(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // cycle detection; no need for CycleDetector.java
        if (result.size() != graph.getNodes().size()) {
            throw new IllegalStateException("Graph has a cycle; topological sort is not possible.");
        }

        return result;
    }

    private Map<T, Integer> computeInDegrees() {
        Map<T, Integer> inDegree = new HashMap<>();

        // Initialize all nodes with 0 in-degree
        for (T node : graph.getNodes()) {
            inDegree.put(node, 0);
        }

        // Calculate in-degrees for all nodes
        for (T node : graph.getNodes()) {
            for (T neighbor : graph.getNeighbors(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        return inDegree;
    }
}