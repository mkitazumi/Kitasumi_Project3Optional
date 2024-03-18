import java.util.LinkedList;

public class Graph {

    private int numVertices;
    private LinkedList<Edge>[] adjList;

    public int getNumVertices() {
        return numVertices;
    }

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int sourceVertex, int weight, int destinationVertex) {
        adjList[sourceVertex].add(new Edge(weight, destinationVertex));
    }

    // Method to get a deep copy of the adjacency list (used by BellmanFord)
    public LinkedList<Edge>[] getAdjListCopy() {
        LinkedList<Edge>[] copy = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            copy[i] = new LinkedList<>();
            for (Edge edge : adjList[i]) {
                copy[i].add(new Edge(edge.getWeight(), edge.getDestination())); // Deep copy each Edge object
            }
        }
        return copy;
    }

    // Method to get neighbors for a specific node (used by Dijkstra)
    public Iterable<Edge> getNeighbors(int node) {
        return adjList[node]; // Return the neighbors list for a specific node (read-only)
    }
}
