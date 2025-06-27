package com.shyarnis.topsort.utils;

import com.shyarnis.topsort.Graph;

import java.io.*;

public class EdgeParser {
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
