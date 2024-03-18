import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DAGShortestPath {

    public static int[] findShortestPaths(Graph graph, int source) {
        int numVertices = graph.getNumVertices();
        int[] distances = new int[numVertices];  // Stores distances from source
        LinkedList<Edge>[] adjListCopy = graph.getAdjListCopy();

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0; // Distance to source is 0

        // Perform Topological Sort (using Kahn's Algorithm)
        List<Integer> topologicalOrder = topologicalSort(graph);

        // BFS traversal based on topological order
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Edge neighbor : adjListCopy[current]) {
                int neighborVertex = neighbor.getDestination();
                distances[neighborVertex] = Math.min(distances[neighborVertex], distances[current] + neighbor.getWeight());
                queue.add(neighborVertex);
            }
        }

        return distances;
    }

    // Implementation of Topological Sort using Kahn's Algorithm
    private static List<Integer> topologicalSort(Graph graph) {
        int numVertices = graph.getNumVertices();
        int[] inDegrees = new int[numVertices];  // Count in-degree for each vertex

        // Calculate in-degree for each vertex
        for (int i = 0; i < numVertices; i++) {
            for (Edge neighbor : graph.getNeighbors(i)) {
                inDegrees[neighbor.getDestination()]++;
            }
        }

        // Create a queue to store vertices with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topologicalOrder = new LinkedList<>();
        int processedVertices = 0;

        // Process vertices using BFS based on in-degree
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedVertices++;
            topologicalOrder.add(current);

            for (Edge neighbor : graph.getNeighbors(current)) {
                int neighborVertex = neighbor.getDestination();
                inDegrees[neighborVertex]--;
                if (inDegrees[neighborVertex] == 0) {
                    queue.add(neighborVertex);
                }
            }
        }

        // Check for cycles (not a DAG)
        if (processedVertices != numVertices) {
            throw new RuntimeException("Graph is not a DAG (cycle detected)");
        }

        return topologicalOrder;
    }
}
