1. separation of concerns between files, `src` and `test`
2. why the name of package is com.<github-name>.toposort?
   - it is based upon reverse domain name convention, which prevents naming collisions. 
   - Github URL: github.com/apache/kafka	 Package Name: org.apache.kafka
   - mark `src/main/java` as sources root
   - mark `src/test/java` as test sources root

3. Naming convention for both source and test
   - src/main/java/com/<github-name>/topsort/Graph.java
   - src/test/java/com/<github-name>/topsort/GraphTest.java

4. Creating a graph
   - generics class is used to work with string or integer type of data
   - it uses adjacencyList which is KV pair of node and neighbours
   - addNode()
   - addEdge()
   - getNodes()
   - getNeighbours()
   - getAdjacencyList()

5. Testing a Graph
   - Junit is used for writing unit tests
   - most of the annotations are located in `junit-jupiter-api`

6. Cycle Detection
   - it must be Directed Acyclic Graph, so cycle detection is must
   - `enum` is used instead of hashset since it need to track of 3 states, while hashset can track of 2 states
   - Time Complexity: `O(V + E)`, each node and edge is visited at most once. 
   - Space Complexity: `O(V)`, for the state map and recursion stack.

7. Topological Sort using Kahn Algorithm
- **No need of cycle detection** because Kahn's algorithm already detects cycle implicitly
```java
   if (result.size() != graph.getNodes().size()) { 
      throw new IllegalStateException("Graph has a cycle; topological sort is not possible.");
   }
```
- Since, the topological sort result contains all nodes which should be equal to length of all nodes of graph
- Time Complexity: `O(V + E)`
- Space Complexity: `O(V + E)`

8. Graph Visualizer
- all of the methods in that class are **static** which means the method is associated with the class itself, 
rather than with instances of that class. So, it is moved inside `utils/` directory.

- Usage of graph visualizer by creating main function inside that class.
```java
    public static void main(String[] args) throws IOException, InterruptedException {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "E");

        // create an output directory if not present
        String dir = "output";
        Files.createDirectories(Paths.get(dir));

        // 1. visualize original graph
        GraphVisualizer.visualizeOriginalGraph(graph, dir +"/before.dot");
        GraphVisualizer.renderDotToPng(dir + "/before.dot", dir + "/before.png");

        // 2. sort the graph
        TopologicalSort<String>  ts = new TopologicalSort<String>(graph);
        List<String> sorted = ts.sort();

        // 3. visualize sorted graph
        GraphVisualizer.visualizeSortedGraph(sorted, dir + "/after.dot");
        GraphVisualizer.renderDotToPng(dir + "/after.dot", dir + "/after.png");
    }
```