import java.util.Arrays;
import java.util.LinkedList;

public class DAGShortestPath {

    public int[] findShortestPaths(Graph graph, int source) {
        int numVertices = graph.getNumVertices();

        LinkedList<Integer> topologicalOrder = topologicalSort(graph);

        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0; // Distance from source to itself is 0

        // Process vertices in topological order
        for (int u : topologicalOrder) {
            for (Edge neighbor : graph.getNeighbors(u)) {
                int v = neighbor.getDestination();
                int weight = neighbor.getWeight();

                // Relaxation condition
                distances[v] = Math.min(distances[v], distances[u] + weight);
            }
        }

        return distances;
    }

    // Helper method for Topological Sort using BFS
    private static LinkedList<Integer> topologicalSort(Graph graph) {
        int numVertices = graph.getNumVertices();


        LinkedList<Integer> queue = new LinkedList<>();


        int[] inDegrees = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (Edge neighbor : graph.getNeighbors(i)) {
                inDegrees[neighbor.getDestination()]++;
            }
        }

        for (int i = 0; i < numVertices; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        LinkedList<Integer> topologicalOrder = new LinkedList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topologicalOrder.add(u);

            for (Edge neighbor : graph.getNeighbors(u)) {
                int v = neighbor.getDestination();
                inDegrees[v]--;

                if (inDegrees[v] == 0) {
                    queue.add(v);
                }
            }
        }

        if (topologicalOrder.size() != numVertices) {
            System.out.println("Error: Graph is not a valid DAG (cycle detected during Topological Sort).");
            return null;  // Indicate failure
        }

        return topologicalOrder;
    }
}









