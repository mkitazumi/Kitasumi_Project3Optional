public class Edge {

    private int weight;
    private int destination;

    public Edge(int weight, int destination) {
        this.weight = weight;
        this.destination = destination;
    }

    // Getter method for weight
    public int getWeight() {
        return weight;
    }

    //Getter method for destination
    public int getDestination(){
        return destination;
    }

}
