import java.util.*;

public class KahnTopoSort {

    public static void main(String[] args) {
        int n = 6; // nodes

        int[][] edges = { // directed edges
            { 1, 2 },
            { 1, 3 },
            { 2, 4 },
            { 3, 4 },
            { 4, 5 },
            //{5, 4},      // <-- cycle
            { 5, 6 },
            { 4, 6 },
        };

        List<Integer> topoOrder = kahnTopologicalSort(edges, n);
        System.out.println("Kahn Topological Order: " + topoOrder);
    }

    public static List<Integer> kahnTopologicalSort(int[][] edges, int n) {
        // 1. Create adjacency list and in-degree array
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inDegree = new int[n + 1]; // index 0 is unused (nodes are from 1 to n)

        // Initialize adjacency list
        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        // Fill adjacency list and in-degree
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList.get(from).add(to);
            inDegree[to]++;
        }
        System.out.println(Arrays.toString(inDegree));
        System.out.println(adjList);

        // 2. Queue for nodes with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 3. Perform BFS
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            // Reduce in-degree of neighbors
            for (int neighbor : adjList.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 4. Check for cycles (not all nodes included)
        if (result.size() != n) {
            throw new RuntimeException(
                "Cycle detected; topological sort not possible"
            );
        }

        return result;
    }
}
