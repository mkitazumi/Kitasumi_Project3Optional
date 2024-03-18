import java.util.Arrays;
import java.util.LinkedList;

public class Dijkstra {

    public static int[] findShortestPaths(Graph graph, int source) {
        int numVertices = graph.getNumVertices();
        int[] distances = new int[numVertices]; // Stores distances from source
        boolean[] visited = new boolean[numVertices]; // Track visited nodes
        LinkedList<Edge>[] adjListCopy = graph.getAdjListCopy();

        // Initialize distances and visited arrays
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0; // Distance to source is 0

        for (int count = 0; count < numVertices - 1; count++) { // Potentially early termination
            int minDistanceNode = findMinDistanceNode(distances, visited);
            visited[minDistanceNode] = true;

            for (Edge neighbor : adjListCopy[minDistanceNode]) {
                int newDistance = distances[minDistanceNode] + neighbor.getWeight();
                if (!visited[neighbor.getDestination()] && newDistance < distances[neighbor.getDestination()]) {
                    distances[neighbor.getDestination()] = newDistance;
                }
            }

            // Early termination if all vertices are visited
            if (isAllVisited(visited)) {
                break;
            }
        }

        return distances;
    }

    private static int findMinDistanceNode(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceNode = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= minDistance) {
                minDistance = distances[i];
                minDistanceNode = i;
            }
        }
        return minDistanceNode;
    }

    private static boolean isAllVisited(boolean[] visited) {
        for (boolean visitedState : visited) {
            if (!visitedState) {
                return false; // Not all vertices are visited
            }
        }
        return true; // All vertices are visited
    }
}
