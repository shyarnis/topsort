package com.shyarnis.topsort.utils;

import com.shyarnis.topsort.Graph;

import java.io.*;
import java.util.*;

/**
 * Utility class for visualizing directed graphs in GraphViz DOT format and rendering them to PNG.
 * <p>
 * This class provides static methods to:
 * <ul>
 *     <li>Convert a graph to DOT format representing the original edges</li>
 *     <li>Convert a topologically sorted list of nodes to a chained DOT graph</li>
 *     <li>Render DOT files to PNG using the external GraphViz tool</li>
 * </ul>
 */
public class GraphVisualizer {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private GraphVisualizer() {
        // Prevent instantiation
    }

    /**
     * Generates a GraphViz DOT representation of the original directed graph.
     *
     * @param graph   the directed graph to visualize
     * @param dotPath the output path for the generated DOT file
     * @param <T>     the type of node in the graph
     * @throws IOException if the DOT file cannot be written
     */
    public static <T> void visualizeOriginalGraph(Graph<T> graph, String dotPath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dotPath))) {
            writer.println("digraph G {");      // first line of .dot

            // iterate over all nodes and their neighbours
            for (T from : graph.getNodes()) {
                for (T to : graph.getNeighbors(from)) {
                    writer.printf("    \"%s\" -> \"%s\";%n", from, to);
                }
            }
            writer.println("}");                // last line of .dot
        }
    }

    /**
     * Generates a GraphViz DOT representation of a topologically sorted list of nodes,
     * displaying them as a simple directed chain.
     *
     * @param sortedOrder the list of nodes in topological order
     * @param dotPath     the output path for the generated DOT file
     * @param <T>         the type of node
     * @throws IOException if the DOT file cannot be written
     */
    public static <T> void visualizeSortedGraph(List<T> sortedOrder, String dotPath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dotPath))) {
            writer.println("digraph Sorted {");
            // first, list all nodes from topological sorted list
            for (T t : sortedOrder) {
                writer.printf("    \"%s\";%n", t);
            }

            // second, connect them using chain "->"
            for (int i = 0; i < sortedOrder.size() - 1; i++) {
                writer.printf("    \"%s\" -> \"%s\";%n", sortedOrder.get(i), sortedOrder.get(i + 1));
            }
            writer.println("}");
        }
    }

    /**
     * Renders a GraphViz DOT file to a PNG image using the GraphViz CLI tool.
     * <p>
     * Requires the {@code dot} command to be installed and available in the system path.
     *
     * @param dotPath the path to the input DOT file
     * @param pngPath the path to the output PNG file
     * @throws IOException          if the external process cannot be started
     * @throws InterruptedException if the rendering process is interrupted
     * @throws RuntimeException     if GraphViz fails to render the image
     */
    public static void renderDotToPng(String dotPath, String pngPath) throws IOException, InterruptedException {
        // create a process to run dot command
        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", dotPath, "-o", pngPath);     // -Tpdf, -Tsvg

        Process process = pb.start();
        if (process.waitFor() != 0) {
            throw new RuntimeException("Failed to render GraphViz image.");
        }
    }
}
