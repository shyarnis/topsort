package com.shyarnis.topsort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CycleDetectorTest {
    private Graph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testNoCycle() {
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        CycleDetector<String> detector = new CycleDetector<>();
        assertFalse(detector.hasCycle(graph));
    }

    @Test
    public void testWithCycle() {
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        CycleDetector<String> detector = new CycleDetector<>();
        assertTrue(detector.hasCycle(graph));
    }
}
