import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {

    public void connectedCities(Vertex source) {
        source.setDistance(0);
        Queue<Vertex> unevaluatedVertices = new PriorityQueue<>();
        unevaluatedVertices.add(source);

        while (!unevaluatedVertices.isEmpty()){
            Vertex current = unevaluatedVertices.poll();
            if (Objects.equals(current.getName(), source.getName())) {
                System.out.print("Cities that can be reached directly from " + source.getName() + ": ");
                StringBuilder builder = new StringBuilder();
                String delimiter = "";
                builder.append("[");
                for (Vertex V: current.getAdjacentVertices().keySet()) {
                    builder.append(delimiter);
                    delimiter = ", ";
                    builder.append(V.getName());
                }
                builder.append("]");
                System.out.println(builder);
            }
            current.getAdjacentVertices()
                    .forEach((key, value) -> {
                        evaluateDistanceAndPath(key, value, current);
                        unevaluatedVertices.add(key);
                    });
        }
    }
    public void computeShortestPath (Vertex source) {
        source.setDistance(0);
        Set<Vertex> evaluatedVertices = new HashSet<>();
        Queue<Vertex> unevaluatedVertices = new PriorityQueue<>();
        unevaluatedVertices.add(source);

        while (!unevaluatedVertices.isEmpty()){
            Vertex current = unevaluatedVertices.poll();
            current.getAdjacentVertices()
                    .entrySet().stream()
                    .filter(entry -> !evaluatedVertices.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), current);
                        unevaluatedVertices.add(entry.getKey());
                    });
            evaluatedVertices.add(current);
        }
    }
    private void evaluateDistanceAndPath(Vertex adjacentVertex, Integer cost, Vertex source) {
        Integer newDistance = source.getDistance() + cost;
        if (newDistance < adjacentVertex.getDistance()) {
            adjacentVertex.setDistance(newDistance);
            adjacentVertex.setShortestPath(Stream.concat(source.getShortestPath().stream(), Stream.of(source)).toList());
        }
    }

    public void display(List<Vertex> vertices) {
        vertices.forEach(vertex -> {
            String path = vertex.getShortestPath().stream()
                    .map(Vertex::getName).map(Objects::toString)
                    .collect(Collectors.joining(" -> "));
            if (!path.isBlank()) {
                System.out.println(MessageFormat.format("{0} -> {1} : {2}",path, vertex.getName(), vertex.getDistance()));
            }
        });
    }
}
