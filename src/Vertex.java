public class Vertex {

    private int data;  // Data associated with the vertex
    public Vertex(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Vertex{" + data + "}";
    }
}
