import java.util.*;

public class Vertex implements Comparable<Vertex>{
    private String name;
    private Integer distance = Integer.MAX_VALUE;
    private List<Vertex> shortestPath = new LinkedList<>();
    private Map<Vertex, Integer> adjacentVertices = new HashMap<>();

    public void addVertex(Vertex vertex, int cost) {
        adjacentVertices.put(vertex, cost);
    }

    public String getName() {
        return this.name;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer dist) {
        this.distance = dist;
    }

    public List<Vertex> getShortestPath() {
        return this.shortestPath;
    }

    public void setShortestPath(List<Vertex> sPath) {
        this.shortestPath = sPath;
    }

    public Map<Vertex, Integer> getAdjacentVertices() {
        return this.adjacentVertices;
    }

    @Override
    public int compareTo(Vertex vertex) {
        return Integer.compare(this.distance, vertex.distance);
    }


    public Vertex(String vertexName) {
        this.name = vertexName;
    }
}
