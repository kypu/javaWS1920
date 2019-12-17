package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

public class Application {

    public static void main(String[] args) {

        // load mock data to test before we finish parsing the graph
        Graph mockGraph = MockData.createRandomMockGraph(10);
        // test the dijkstra implementation
        Node testOriginNode = mockGraph.getNodeById(1);
        mockGraph.calculateShortestPathsFrom(testOriginNode);
        for (Node node : mockGraph.getNodes()) {
            int pathLength = testOriginNode.getShortestPathLengthTo(node);
            if (pathLength == Integer.MAX_VALUE) {
                System.out.println(testOriginNode.toString() + " and " + node.toString() + " are not connected.");
            } else {
                System.out.println("Shortest path from " + testOriginNode.getNodeId() + " to " + node.getNodeId() + " is " +
                        testOriginNode.getShortestPathLengthTo(node));
                System.out.println("Directions: " + testOriginNode.getShortestPathDirectionsTo(node));
            }
        }

        // 1. output number of nodes
        System.out.println("----Number of nodes: " + mockGraph.getNodes().size());

        // 2. output number of edges
        System.out.println("Number of edges: " + mockGraph.getEdges().size());
        // 3. output node IDs
        //#print out each nodes and its neighbors
        System.out.println("---------------NODE INFO---------------------");

        for(Node node : mockGraph.getNodes()){
            node.print();
            System.out.println("---");
        }

        // 4. output edge IDs
        System.out.println("---------------EDGE INFO---------------------");
        for(Edge edge : mockGraph.getEdges()){
            edge.print();
            System.out.println("---");
        }
        // 5. is the graph connected?

        // 6. diameter of graph

        // shortest paths ?

    }
}









