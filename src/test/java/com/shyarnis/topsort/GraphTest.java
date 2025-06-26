package com.shyarnis.topsort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    @Test
    void testGraph() {
        graph.addNode("A");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");

        assertEquals(Set.of("B", "C"), graph.getNeighbors("A"));
        assertEquals(Set.of("D"), graph.getNeighbors("C"));
        assertEquals(Set.of(), graph.getNeighbors("B"));

        Set<String> expectedNodes = Set.of("A", "B", "D", "C");
        assertEquals(expectedNodes, graph.getNodes());
    }


    // Immutability checks
    @Test
    void testAdjacencyListUnmodifiable_String() {
        graph.addEdge("X", "Y");
        Map<String, Set<String>> adjList = graph.getAdjacencyList();
        assertThrows(UnsupportedOperationException.class, () -> adjList.put("New", Set.of("Z")));
    }
}


