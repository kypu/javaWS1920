//Java group 26
package de.frauas.java.projectWS1920.Application;

import de.frauas.java.projectWS1920.DataAccessObject.GraphML;
import de.frauas.java.projectWS1920.Models.MyGraph;
import de.frauas.java.projectWS1920.Models.MyNode;
import de.frauas.java.projectWS1920.Resources.Resource;
import java.util.HashMap;

public class Application {

    public static void main(String[] args) throws Exception {
        String fn = "small_graph.graphml";
        String[] testArgs = new String[]{ Resource.getFilepath() + fn, "-a", "Test"};
        var app = new CommandLineApplication();
        app.run(testArgs);

        MyGraph readInGraph = GraphML.importData(Resource.getFilepath() + "small_graph.graphml");
        /*
        Boolean didItWork = GraphML.exportData(Resource.getFilepath() + "attempt.graphml", readInGraph);
        if (didItWork) System.out.println("Success!");*/

        readInGraph.initialiseGraphAttributes();

        //Output shortest paths
        System.out.println("### Shortest paths ###");
        for(MyNode node1 : readInGraph.getNodes()){
            System.out.println("Source node '"+node1.getNodeId()+"':");
            for(MyNode node2 : readInGraph.getNodes()){
                System.out.println("To node '"+node2.getNodeId()+"': path -> " + node1.getDirectionsTo(node2) + "; length -> " + node1.getShortestPathLengthTo(node2));
            }
        }

        // output betweenness centrality
        System.out.println("### Betweenness Centrality ###");
        for (MyNode node : readInGraph.getNodes()) {
            System.out.println("Node '" + node.getNodeId() + "': " + node.getBetweennessCentrality());
        }

        System.out.println("Is graph connected? " + readInGraph.getConnected());

        System.out.println("Diameter: " + readInGraph.getDiameter());

        System.out.println(GraphML.exportData("src/de/frauas/java/projectWS1920/resources/testExport.graphml", readInGraph));
        /*
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
        */
    }
}









