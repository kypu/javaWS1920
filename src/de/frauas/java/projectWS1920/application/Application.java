package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

public class Application {

    public static void main(String[] args) {

        // load mock data to test before we finish parsing the graph
        Graph mockGraph = MockData.createMockGraph(5);
        System.out.println("Node 1's neighbours: " + mockGraph.getNodes().get(0).getAdjacentNodes());
        // test the dijkstra implementation
        /*Node testOriginNode = mockGraph.getNodeById(1);
        mockGraph.calculateShortestPathsFrom(testOriginNode);


        for (Node node : mockGraph.getNodes()) {
            System.out.println("Shortest path from " + testOriginNode.getNodeId() + " to " + node.getNodeId() + " is " +
                    testOriginNode.getShortestPathTo(node));
        }
*/
        // 1. output number of nodes
        System.out.println("----Number of nodes: " + mockGraph.getNodes().size());

        // 2. output number of edges
        System.out.println("Number of edges: " + mockGraph.getEdges().size());
        // 3. output node IDs
        //#print out each nodes and its neighbors
        System.out.println("---------------NODE INFO---------------------");
        for(int i=0;i<mockGraph.getNodes().size();i++){
            mockGraph.getNodes().get(i).print();
            System.out.println("---");
        }

        // 4. output edge IDs
        System.out.println("---------------EDGE INFO---------------------");
        for(int i=0;i<mockGraph.getEdges().size();i++){
            mockGraph.getEdges().get(i).print();
            System.out.println("---");
        }
        // 5. is the graph connected?

        // 6. diameter of graph

        // shortest paths ?

    }
}









