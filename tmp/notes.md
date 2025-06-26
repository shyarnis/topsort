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
