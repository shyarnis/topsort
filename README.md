# TopSort

[![Build](https://github.com/shyarnis/topsort/actions/workflows/deploy-docs.yml/badge.svg)](https://github.com/shyarnis/topsort/actions/workflows/deploy-docs.yml)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.shyarnis/topsort)](https://central.sonatype.com/artifact/io.github.shyarnis/topsort)
[![Javadoc](https://img.shields.io/badge/docs-javadoc-blue?logo=java)](https://shyarnis.github.io/toposort/javadoc/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

TopSort is a lightweight Java library for topological sorting, parsing graph data from files and visualizing graphs.

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Documentation](#documentation)
- [Quick Start](#quick-start)
    - [String Graph](#string-graph)
    - [Load Graph From File](#load-graph-from-file)
    - [Visualize Graph](#visualize-graph)
- [Example Demo](#example-demo)

## Features
- Kahn’s Algorithm for topological sorting
- Edge parsing from file formats
- Graph visualization via GraphViz

## Installation

Add this to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.shyarnis</groupId>
    <artifactId>topsort</artifactId>
    <version>0.0.1</version>
</dependency>
```
or for the `gradle`
```groovy
implementation group: 'io.github.shyarnis', name: 'topsort', version: '0.0.1'
```

## Documentation
[API Reference (Javadoc)](https://shyarnis.github.io/topsort/javadoc/)

## Quick Start
### String Graph
```java
Graph<String> graph = new Graph<>();
graph.addEdge("A", "B");
graph.addEdge("B", "C");

TopologicalSort<String> sorter = new TopologicalSort<>(graph);
List<String> sorted = sorter.sort();

System.out.println(sorted); // [A, B, C]
```

### Load Graph From File
- save a directed edge graph in csv format or txt format in root directory as `graph.csv`
```csv
A,B
B,C
```
```java
Graph<String> graph = EdgeParser.parseEdgesFromFile("graph.csv");
List<String> sorted = new TopologicalSort<>(graph).sort();      // [A, B, C]
```

### Visualize Graph
- The DAGs are stored inside `output/` directory
```java
String dir = "output";
Files.createDirectories(Paths.get(dir));

GraphVisualizer.visualizeOriginalGraph(graph, dir + "/before_sort.dot");
GraphVisualizer.renderDotToPng(dir + "/before_sort.dot", dir + "/before_sort.png");

TopologicalSort<String> topologicalSort = new TopologicalSort<>(graph);
List<String> result = topologicalSort.sort();

GraphVisualizer.visualizeSortedGraph(result, dir + "/after_sort.dot");
GraphVisualizer.renderDotToPng(dir + "/after_sort.dot", dir + "/after_sort.png");
```

## Example Demo
- This repo contains [demo of topsort](https://gitlab.com/shyarnis/topsort-in-action/)
