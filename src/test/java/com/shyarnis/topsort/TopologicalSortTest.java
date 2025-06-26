package com.shyarnis.topsort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class TopologicalSortTest {
    private Graph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    void testTopologicalSort_ValidDAG() {
        graph.addEdge("G", "B");
        graph.addEdge("G", "C");
        graph.addEdge("G", "D");
        graph.addEdge("C", "F");
        // graph: G -> B -> C -> D -> F

        TopologicalSort<String> topologicalSort = new TopologicalSort<>(graph);
        List<String> result = topologicalSort.sort();

        // G must come before B and so on
        assertTrue(result.indexOf("G") < result.indexOf("B"));
        assertTrue(result.indexOf("B") < result.indexOf("C"));
        assertTrue(result.indexOf("C") < result.indexOf("D"));
        assertTrue(result.indexOf("G") < result.indexOf("F"));
        assertEquals(5, result.size());
        assertTrue(result.containsAll(Arrays.asList("G","B", "C", "D", "F")));
    }

    @Test
    void testTopologicalSort_withCycle() {
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "B");
        // graph: A -> B -> C -> B -> C -> B -> ...  (cycle)

        TopologicalSort<String> topologicalSort = new TopologicalSort<>(graph);

        assertThrows(IllegalStateException.class, topologicalSort::sort);
    }
}
