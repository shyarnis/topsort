import java.util.*;

public class TopoSort {
    public static void main(String[] args) {
        int n = 6;  // nodes

        int[][] edges = {   // directed edges
                {1, 2},
                {1, 3},
                {2, 4},
                {3, 4},
                {4, 5},
                //{5, 4},      // <-- cycle
                {5, 6},
                {4, 6},
        };

        List<Integer> topoOrder = topologicalSort(edges, n);
        System.out.println("Topological Order: " + topoOrder);
    }

    public static List<Integer> topologicalSort(int[][] edges, int n) {
        List<Integer> result = new ArrayList<>();

        // build an adjacency list from the given edges
        Map<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int src = edge[0];
            int dst = edge[1];
            adjList.get(src).add(dst);
        }
        System.out.println(adjList);

        // visited set to avoid repeated traversing
        Set<Integer> visited = new HashSet<>();

        // perform DFS from every node
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                dfs(i, adjList, visited, result);
            }
        }

        // reverse the result list to get the topological order
        Collections.reverse(result);
        return result;
    }

    public static void dfs(int src, Map<Integer, ArrayList<Integer>> adjList, Set<Integer> visited, List<Integer> result) {
        // Mark current node as visited
        visited.add(src);

        // recursion call for adjacent nodes
        for (int neighbor : adjList.get(src)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, adjList, visited, result);
            }
        }

        // add the current node to result
        result.add(src);
    }
}
