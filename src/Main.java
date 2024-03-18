import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the number of vertices and edges from user input
        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        // Create a graph
        Graph graph = new Graph(numVertices);

        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter source, destination (space-separated): ");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        // Check if the graph is a DAG
        boolean isDag = graph.isDAG();

        if (isDag) {
            System.out.println("The graph is a Directed Acyclic Graph (DAG).");
        } else {
            System.out.println("The graph is not a DAG (cycle detected).");
        }
    }
}






