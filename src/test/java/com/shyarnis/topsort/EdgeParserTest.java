package com.shyarnis.topsort;

import com.shyarnis.topsort.utils.EdgeParser;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class EdgeParserTest {
    private static Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("graph-input", ".txt");

        List<String> lines = List.of(
                "# Comment line",
                "",
                "A -> B",
                "C,D",
                "invalid-line",
                "E -> F",
                "  G , H ",
                "# Another comment"
        );

        Files.write(tempFile, lines);
    }

    @Test
    void testParseEdgesFromFile() throws IOException {
        Graph<String> graph = EdgeParser.parseEdgesFromFile(tempFile.toString());

        assertTrue(graph.getNeighbors("A").contains("B"));
        assertTrue(graph.getNeighbors("C").contains("D"));
        assertTrue(graph.getNeighbors("E").contains("F"));
        assertTrue(graph.getNeighbors("G").contains("H"));

        Set<String> expectedNodes = Set.of("A", "B", "C", "D", "E", "F", "G", "H");
        assertEquals(expectedNodes, graph.getNodes());

        assertFalse(graph.getNodes().contains("invalid-line"));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }
}
