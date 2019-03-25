package A;

public class Edge {


    private Vertex start;
    private Vertex end;
    private int weight; // 权重（边的长度）

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}


