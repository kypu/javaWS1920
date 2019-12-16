package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

import java.util.Random;
import java.util.List;


public class MockData {

    public static Graph createMockGraph() {

        // We can add more nodes and edges to make it more complicated

        Graph mockGraph = new Graph();


        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        Edge edge1 = new Edge(1, node1, node2, 3);
        Edge edge2 = new Edge(2, node1, node3, 15);
        Edge edge3 = new Edge(3, node1, node4, 1);
        Edge edge4 = new Edge(4, node3, node4, 4);

        mockGraph.addNode(node1);
        mockGraph.addNode(node2);
        mockGraph.addNode(node3);
        mockGraph.addNode(node4);

        mockGraph.addEdge(edge1);
        mockGraph.addEdge(edge2);
        mockGraph.addEdge(edge3);
        mockGraph.addEdge(edge4);

        mockGraph.setAdjacentNodes();

        return mockGraph;
    }



    public static Graph createMockGraph(int numOfNodes) {

        int maxEdges=numOfNodes*(numOfNodes-1)/2;

        // The mock graph looks like this: NODE1--edge13--NODE2--edge15--NODE3
        // We can add more nodes and edges to make it more complicated

        Graph mockGraph = new Graph();

        //create number of nodes as required and add them into the graph
        for(int i=1;i<=numOfNodes;i++) {
            Node aNode = new Node(i);
            mockGraph.addNode(aNode);
        }

        List<Node> nodesList = mockGraph.getNodes();
        //create max number of edges based on the number of nodes
        //getRandomNumberInRange auto generate whole number between the first parameter to the second parameter
        for(int i=0;i<getRandomNumberInRange(0,maxEdges);i++) {
            int idNode1 = getRandomNumberInRange(1,maxEdges);
            int idNode2 = getRandomNumberInRange(1,maxEdges);
            //if the both of the Nodes are the same, then generate new one
            while(idNode1==idNode2) {
                idNode2 = getRandomNumberInRange(1,maxEdges);
            }
            Edge aEdge = new Edge(i,nodesList.get(idNode1),nodesList.get(idNode2),getRandomNumberInRange(1,100));
            //add Edge to the graph
            mockGraph.addEdge(aEdge);
        }

        mockGraph.setAdjacentNodes();

        return mockGraph;
    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}


