public class Edge {

    private int source;  // Vertex at the beginning of the edge
    private int destination;  // Vertex at the end of the edge
    private int weight;  // Weight associated with the edge

    public Edge(int source, int destination) {
        this.source = source;
        this.destination = destination;
        this.weight = 0;  // Default weight of 0
    }

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + source + ", " + destination + (weight > 0 ? ", " + weight : "") + ")";
    }
}



