import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {

    private int numVertices;
    private List<List<Integer>> adjList;  // Adjacency list representation for directed graphs

    public int getNumVertices() {
        return numVertices;  // Assuming numVertices stores the number of vertices
    }

    public List<Integer> getNeighbors(int vertex) {
        if (vertex < 0 || vertex >= numVertices) {
            throw new IllegalArgumentException("Invalid vertex index: " + vertex);
        }
        return new ArrayList<>(adjList.get(vertex));
    }

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);  // Initialize adjacency list

        // Create empty lists for each vertex
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        // Add directed edge from source to destination
        adjList.get(source).add(destination);
    }

    public boolean isDAG() {
        boolean[] visited = new boolean[numVertices];
        boolean[] inStack = new boolean[numVertices];  // Track vertices in the current DFS path

        // Perform DFS for each vertex
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) { // Start DFS from unvisited vertices
                if (isCyclicUtil(i, visited, inStack)) {
                    return false;  // Cycle detected, not a DAG
                }
            }
        }

        return true;  // No cycles found, it's a DAG
    }

    // Helper method for recursive DFS with cycle detection
    private boolean isCyclicUtil(int v, boolean[] visited, boolean[] inStack) {
        visited[v] = true;  // Mark current vertex as visited
        inStack[v] = true;   // Push current vertex to the DFS path stack

        // Explore neighbors
        for (int neighbor : adjList.get(v)) {
            // Self-loop check
            if (neighbor == v) {
                return true;  // Self-loop, cycle detected
            }

            // Check if a cycle exists recursively
            if (!visited[neighbor]) {
                if (isCyclicUtil(neighbor, visited, inStack)) {
                    return true;  // Cycle found in a descendant
                }
            } else if (inStack[neighbor]) {  // Back edge detected (cycle)
                return true;
            }
        }

        // Pop current vertex
        inStack[v] = false;
        return false;  // No cycle found in this path
    }

    //idk just adding

    public List<Integer> getTopologicalSort() {
        // Implement topological sort using DFS
        List<Integer> topologicalOrder = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numVertices];

        // Perform DFS for each vertex
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Pop vertices from stack
        while (!stack.isEmpty()) {
            topologicalOrder.add(stack.pop());
        }

        return topologicalOrder;
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        // Explore neighbors only if unvisited (applicable for DAGs)
        for (int neighbor : adjList.get(v)) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }

        stack.push(v);
    }

}



