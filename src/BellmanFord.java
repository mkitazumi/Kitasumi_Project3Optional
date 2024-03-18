import java.util.Arrays;

public class BellmanFord {

    public int[] findShortestPaths(Graph graph, int source) {
        int numVertices = graph.getNumVertices();

        // Initialize distances to infinity (except source)
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0; // Distance from source to itself is 0

        // Relax edges repeatedly (numVertices - 1 iterations) to handle negative cycles
        for (int i = 0; i < numVertices - 1; i++) {
            for (int u = 0; u < numVertices; u++) {
                for (Edge neighbor : graph.getNeighbors(u)) {
                    int v = neighbor.getDestination();
                    int weight = neighbor.getWeight();

                    // Relaxation condition
                    if (distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                    }
                }
            }
        }

        // Check for negative weight cycles (optional)
        for (int u = 0; u < numVertices; u++) {
            for (Edge neighbor : graph.getNeighbors(u)) {
                int v = neighbor.getDestination();
                int weight = neighbor.getWeight();
                if (distances[u] + weight < distances[v]) {
                    // Negative cycle detected (return null to indicate failure)
                    return null;
                }
            }
        }

        return distances;
    }
}



