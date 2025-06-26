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
   