package com.shyarnis.topsort.utils;

import com.shyarnis.topsort.Graph;

import java.io.*;
import java.util.*;

public class GraphVisualizer {

    // converts a directed graph into GraphViz .dot format
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

    // converts a directed graph into list topologically sorted list of nodes
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

    // convert .dot file into .png file using GraphViz tool
    public static void renderDotToPng(String dotPath, String pngPath) throws IOException, InterruptedException {
        // create a process to run dot command
        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", dotPath, "-o", pngPath);     // -Tpdf, -Tsvg

        Process process = pb.start();
        if (process.waitFor() != 0) {
            throw new RuntimeException("Failed to render GraphViz image.");
        }
    }
}
