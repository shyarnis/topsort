package com.shyarnis.topsort.utils;

import com.shyarnis.topsort.Graph;

import java.io.*;

/**
 * Utility class for parsing edges from a text file and building a {@link Graph} from them.
 * <p>
 * This parser supports two directed edge formats per line:
 * <ul>
 *     <li>{@code A -> B}</li>
 *     <li>{@code A, B}</li>
 * </ul>
 * Lines that are empty or start with {@code #} are ignored as comments.
 */
public class EdgeParser {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EdgeParser() {
        // Prevent instantiation
    }

    /**
     * Parses a text file containing edge definitions and constructs a directed {@link Graph}.
     * <p>
     * Each line in the file must represent a directed edge between two nodes, in one of the following formats:
     * <ul>
     *     <li>{@code A -> B}</li>
     *     <li>{@code A, B}</li>
     * </ul>
     * Lines that are empty or start with {@code #} are treated as comments and skipped.
     * If a line does not match either format, it is also ignored.
     *
     * @param filePath the path to the text file containing edge definitions
     * @return a {@link Graph} instance populated with nodes and edges from the file
     * @throws IOException if there is an error reading the file
     */
    public static Graph<String> parseEdgesFromFile(String filePath) throws IOException {
        Graph<String> graph = new Graph<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String from, to;

                if (line.contains("->")) {
                    // Format: A -> B
                    String[] parts = line.split("->");
                    if (parts.length != 2) continue;
                    from = parts[0].trim();
                    to = parts[1].trim();

                } else if (line.contains(",")) {
                    // Format: A, B
                    String[] parts = line.split(",");
                    if (parts.length != 2) continue;
                    from = parts[0].trim();
                    to = parts[1].trim();
                } else {
                    continue; // skip invalid line
                }

                graph.addEdge(from, to);
            }
        }
        return graph;
    }
}
