import java.util.Arrays;
import java.util.LinkedList;

public class BellmanFord {

    public static boolean findShortestPaths(Graph graph, int source) {
        int numVertices = graph.getNumVertices();
        int[] distances = new int[numVertices];

        // Get a deep copy of adjList ONLY ONCE
        LinkedList<Edge>[] adjListCopy = graph.getAdjListCopy();

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Relaxation process (V-1 iterations)
        for (int i = 0; i < numVertices - 1; i++) {
            boolean didUpdate = false;
            for (int u = 0; u < numVertices; u++) {
                for (Edge neighbor : adjListCopy[u]) {
                    int v = neighbor.getDestination(); // Get destination vertex from Edge object
                    int weight = neighbor.getWeight(); // Get weight from Edge object
                    int newDistance = distances[u] + weight;
                    if (distances[v] > newDistance) {
                        distances[v] = newDistance;
                        didUpdate = true;
                    }
                }
            }
            if (!didUpdate) {
                break;  // Optimization: No updates, no negative cycle
            }
        }

        // Check for negative cycle (relax one more time)
        for (int u = 0; u < numVertices; u++) {
            for (Edge neighbor : adjListCopy[u]) {
                int v = neighbor.getDestination(); // Get destination vertex from Edge object
                int weight = neighbor.getWeight(); // Get weight from Edge object
                if (distances[v] > distances[u] + weight) {
                    return true;  // Negative cycle detected
                }
            }
        }

        return false;  // No negative cycle found
    }
}
