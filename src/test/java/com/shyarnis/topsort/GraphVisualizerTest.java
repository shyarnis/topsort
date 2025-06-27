package com.shyarnis.topsort;

import com.shyarnis.topsort.utils.GraphVisualizer;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphVisualizerTest {

    private static final String OUTPUT_DIR = "test-output";

    @BeforeAll
    static void setup() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT_DIR));
    }

    @Test
    void testVisualizeOriginalGraph() throws IOException {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        String dotPath = OUTPUT_DIR + "/original.dot";
        GraphVisualizer.visualizeOriginalGraph(graph, dotPath);

        List<String> lines = Files.readAllLines(Paths.get(dotPath));
        assertTrue(lines.contains("    \"A\" -> \"B\";"));
        assertTrue(lines.contains("    \"A\" -> \"C\";"));
        assertEquals("digraph G {", lines.get(0));
        assertEquals("}", lines.get(lines.size() - 1));
    }

    @Test
    void testVisualizeSortedGraph() throws IOException {
        List<String> sorted = List.of("A", "B", "C");

        String dotPath = OUTPUT_DIR + "/sorted.dot";
        GraphVisualizer.visualizeSortedGraph(sorted, dotPath);

        List<String> lines = Files.readAllLines(Paths.get(dotPath));
        assertTrue(lines.contains("    \"A\" -> \"B\";"));
        assertTrue(lines.contains("    \"B\" -> \"C\";"));
        assertTrue(lines.contains("    \"A\";"));
        assertTrue(lines.contains("    \"B\";"));
        assertTrue(lines.contains("    \"C\";"));
        assertEquals("digraph Sorted {", lines.get(0));
        assertEquals("}", lines.get(lines.size() - 1));
    }

    @Test
    void testRenderDotToPng() throws IOException, InterruptedException {
        String dotPath = OUTPUT_DIR + "/sample.dot";
        String pngPath = OUTPUT_DIR + "/sample.png";

        // Create a minimal .dot file
        Files.write(Paths.get(dotPath), List.of(
                "digraph G {",
                "    \"A\" -> \"B\";",
                "}"
        ));

        GraphVisualizer.renderDotToPng(dotPath, pngPath);
        assertTrue(Files.exists(Paths.get(pngPath)), "PNG file should be created");
        assertTrue(Files.size(Paths.get(pngPath)) > 0, "PNG file should not be empty");
    }

    @AfterAll
    static void cleanup() throws IOException {
        Files.walk(Paths.get(OUTPUT_DIR))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
