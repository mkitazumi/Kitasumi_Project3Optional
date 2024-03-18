import java.util.LinkedList;

public class Main {

    public static void main( String[] args ) {
        // Create a sample graph (modify for DAG testing)
        int numVertices = 5;
        Graph graph = new Graph( numVertices );
        graph.addEdge( 0, 1, 1 );
        graph.addEdge( 0, 2, 4 );
        graph.addEdge( 1, 2, 3 ); // Modify edges to create a DAG for testing
        graph.addEdge( 1, 3, 2 );
        graph.addEdge( 2, 4, 2 );
        // Comment out the following line to create a DAG for testing
        graph.addEdge( 3, 1, 1 );

        // Choose the source vertex
        int source = 0;

        // Check if the graph is a DAG
        boolean isDAG = isDAG( graph );
        System.out.println( "The graph " + ( isDAG ? "is" : "is not" ) + " a Directed Acyclic Graph (DAG)." );

        // Run Bellman-Ford algorithm
        if (isDAG) { // Only run Bellman-Ford if it's a DAG (no cycles)
            BellmanFord bellmanFord = new BellmanFord();
            boolean hasShortestPaths = bellmanFord.findShortestPaths( graph, source );
            printResults( "Bellman-Ford", hasShortestPaths );
        } else {
            System.out.println( "Bellman-Ford algorithm not applicable (graph has cycles)." );
        }

        // Run Dijkstra's algorithm (assuming graph doesn't have negative weights)
        if (isDAG && !hasShortestPaths) {  // Only run Dijkstra's if it's a DAG and Bellman-Ford failed
            Dijkstra dijkstra = new Dijkstra(); // Assuming Dijkstra class is implemented
            int[] dijkstraDistances = dijkstra.findShortestPaths( graph, source );
            printResults( "Dijkstra", true, dijkstraDistances ); // Assuming successful execution
        } else {
            System.out.println( "Dijkstra's algorithm not applicable (negative weights or cycles)." );
        }
    }

    private static void printResults( String algorithm, boolean hasPaths, int[] distances ) {
        if (hasPaths) {
            System.out.println( "\n" + algorithm + " Results:" );
            for (int i = 0; i < distances.length; i++) {
                System.out.println( "Vertex " + i + " distance: " + ( distances[ i ] == Integer.MAX_VALUE ? "Infinity" : distances[ i ] ) );
            }
        } else {
            System.out.println( algorithm + " couldn't find shortest paths (negative cycle detected)." );
        }
    }

    // Helper class to store vertex and its in-degree (number of incoming edges)
    private static class Vertex {
        int vertex;
        int inDegree;

        public Vertex( int vertex, int inDegree ) {
            this.vertex = vertex;
            this.inDegree = inDegree;
        }
    }

    // Check if the graph is a DAG using Topological Sort (BFS)
    private static boolean isDAG( Graph graph ) {
        int numVertices = graph.getNumVertices();

        // Create a queue to store vertices with in-degree 0
        LinkedList<Vertex> queue = new LinkedList<>();

        // Count in-degree for each vertex
        int[] inDegrees = new int[ numVertices ];
        for (int i = 0; i < numVertices; i++) {
            for (Edge neighbor : graph.getNeighbors( i )) {
                inDegrees[ neighbor.getDestination() ]++; // Increment in-degree of destination
            }
        }

        // Add vertices with in-degree 0 to the queue
        for (int i = 0; i < numVertices; i++) {
            if (inDegrees[ i ] == 0) {
                queue.add( new Vertex( i, 0 ) );
            }
        }

        // Perform topological sort using BFS
        int processedVertices = 0;
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            processedVertices++;

            // Process neighbors (reduce their in-degree)
            for (Edge neighbor : graph.getNeighbors( current.vertex )) {
                int neighborVertex = neighbor.getDestination();
                inDegrees[ neighborVertex ]--;

                // If in-degree becomes 0, add to the queue
                if (inDegrees[ neighborVertex ] == 0) {
                    queue.add( new Vertex( neighborVertex, 0 ) );
                }
            }

            // If all vertices are not processed, there's a cycle (not a DAG)
            return processedVertices == numVertices;
        }
    }
}


