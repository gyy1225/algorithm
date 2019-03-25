package A;


import java.util.LinkedList;

public class Vertex {

    private char id; // 顶点的标识
    private LinkedList<Edge> edges; // 当前顶点可直接达到的边
    private Vertex parent; // 上一个顶点是谁(前驱)，用来记录路径的
    private int distance = Integer.MAX_VALUE; // 距离起始点的距离

    public Vertex(char id) {
        this.id = id;
        this.edges = new LinkedList<>();
    }

    public char getId() {
        return id;
    }

    public LinkedList<Edge> getNeighbors() {
        return edges;
    }

    public void addNeighbor(Vertex vertex, int weight) {
        edges.add(new Edge(this, vertex, weight)); // 起点均是当前顶点
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return String.format("Vertex[%c]: distance is %d , predecessor is '%s'", id, distance, parent == null ? "null"
                : parent.id);
    }

}

