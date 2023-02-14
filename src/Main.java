import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Vertex> citiesList = new ArrayList<>();

        Vertex NewYork = new Vertex("New York");
        Vertex Miami = new Vertex("Miami");
        Vertex Dallas = new Vertex("Dallas");
        Vertex Denver = new Vertex("Denver");
        Vertex Chicago = new Vertex("Chicago");
        Vertex SanDiego = new Vertex("San Diego");
        Vertex LA = new Vertex("LA");
        Vertex SanFrancisco = new Vertex("San Francisco");

        NewYork.addVertex(Miami, 90);
        NewYork.addVertex(Dallas, 125);
        NewYork.addVertex(Denver, 100);
        NewYork.addVertex(Chicago, 75);
        citiesList.add(NewYork);

        Miami.addVertex(Dallas, 50);
        citiesList.add(Miami);

        Dallas.addVertex(SanDiego, 90);
        Dallas.addVertex(LA, 80);
        citiesList.add(Dallas);

        Denver.addVertex(LA, 100);
        Denver.addVertex(SanFrancisco, 75);
        citiesList.add(Denver);

        Chicago.addVertex(SanFrancisco, 25);
        citiesList.add(Chicago);

        SanDiego.addVertex(LA, 45);
        citiesList.add(SanDiego);

        citiesList.add(LA);

        SanFrancisco.addVertex(LA, 45);
        citiesList.add(SanFrancisco);

        HashMap<String, Vertex> citiesHashMap = new HashMap<>();
        List<String> cities = Arrays.asList("NewYork", "Miami", "Dallas", "Denver", "Chicago", "SanDiego", "LA", "SanFrancisco");

        for (int i=0; i<citiesList.size(); i++) {
            //System.out.println(cities.get(i) + ": " + citiesList.get(i).getName());
            citiesHashMap.put(cities.get(i), citiesList.get(i));
        }

        //System.out.println(citiesHashMap.keySet());

        // Taking User Input
        System.out.println("Cities names: " + cities);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        System.out.print("Enter the name of the source city: ");
        String source = scanner.nextLine();  // Read user input

        System.out.print("Enter the name of the destination city: ");
        String destination = scanner.nextLine();

        if (!cities.contains(source) || !cities.contains(destination)) {
            throw new IllegalArgumentException("Please provide a valid city name from list of cities above");
        }

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.computeShortestPath(citiesHashMap.get(source));
        dijkstra.display(List.of(citiesHashMap.get(destination)));

        System.out.println("Enter the name of the city to get the names of cities that can be reached directly from it: ");
        //Scanner destinationObj = new Scanner(System.in);
        String city = scanner.nextLine();  // Read user input

        if (!cities.contains(city)) {
            throw new IllegalArgumentException("Please provide a valid city name from list of cities above");
        }

        dijkstra.connectedCities(citiesHashMap.get(city));
    }
}
