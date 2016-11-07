/**
 * Created by User on 02.10.2016.
 */

import java.util.ArrayList;

public class Graph {
    private Node[] nodes;
    private int noOfNodes;
    private Edge[] edges;
    private int noOfEdges;

    public Graph(Edge[] edges) {
        this.edges = edges;

        this.noOfNodes = calculateNoOfNodes(edges);
        this.nodes = new Node[this.noOfNodes];
        for (int n = 0; n < this.noOfNodes; n++) {
            this.nodes[n] = new Node();
        }

        this.noOfEdges = edges.length;
        for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
            this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
            this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
        }
    }

    private int calculateNoOfNodes(Edge[] edges) {
        int noOfNodes = 0;
        for (Edge e : edges) {
            if (e.getToNodeIndex() > noOfNodes)
                noOfNodes = e.getToNodeIndex();
            if (e.getFromNodeIndex() > noOfNodes)
                noOfNodes = e.getFromNodeIndex();
        }
        noOfNodes++;
        return noOfNodes;
    }

    public void calculateShortestDistances() {
        this.nodes[0].setDistanceFromSource(0);
        int nextNode = 0;

        for (Node node : this.nodes) {
            // loop around the edges of current node
            ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
            for (Edge currentNodeEdge : currentNodeEdges) {
                int neighbourIndex = currentNodeEdge.getNeighbourIndex(nextNode);
                // only if not visited
                if (!this.nodes[neighbourIndex].isVisited()) {
                    int temp = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdge.getLength();
                    if (temp < nodes[neighbourIndex].getDistanceFromSource()) {
                        nodes[neighbourIndex].setDistanceFromSource(temp);
                    }
                }
            }
            nodes[nextNode].setVisited(true);

            nextNode = getNodeShortestDistanced();
        }
    }

    private int getNodeShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;
        for (int i = 0; i < this.nodes.length; i++) {
            int currentDist = this.nodes[i].getDistanceFromSource();
            if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    public void printResult() {
        String output = "Number of nodes = " + this.noOfNodes;
        output += "\nNumber of edges = " + this.noOfEdges;
        for (int i = 0; i < this.nodes.length; i++) {
            output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
        }
        System.out.println(output);
    }

    public Node[] getNodes() {
        return nodes;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public int getNoOfEdges() {
        return noOfEdges;
    }
}
