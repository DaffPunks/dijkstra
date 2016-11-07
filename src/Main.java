/**
 * Created by User on 02.10.2016.
 */

public class Main {

    public static void main(String[] args) {
        Edge[] edges = {
                new Edge(0, 2, 1),
                new Edge(0, 3, 4),
                new Edge(0, 4, 2),
                new Edge(0, 1, 3),
                new Edge(1, 3, 2),
                new Edge(1, 4, 3),
                new Edge(1, 5, 1),
                new Edge(2, 4, 1),
                new Edge(3, 5, 4),
                new Edge(4, 5, 2),
        };
        Graph g = new Graph(edges);
        System.out.println(g.getNoOfNodes());
        g.calculateShortestDistances();
        g.printResult();
    }
}
