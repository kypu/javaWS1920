package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MockData {


    public static Graph createMockGraph() {

        // The mock graph looks like this: NODE1--edge13--NODE2--edge15--NODE3
        // We can add more nodes and edges to make it more complicated

        Graph mockGraph = new Graph();


        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        Edge edge1 = new Edge(1, node1, node2, 13);
        Edge edge2 = new Edge(2, node2, node3, 15);

        mockGraph.addNode(node1);
        mockGraph.addNode(node2);
        mockGraph.addNode(node3);

        mockGraph.addEdge(edge1);
        mockGraph.addEdge(edge2);

        mockGraph.setAdjacentNodes();

        return mockGraph;
    }


    public static Graph createMockGraph(int numOfNodes) {

        int maxEdges=numOfNodes*(numOfNodes-1)/2;
        HashSet<Tuple> existedEdgeSet = new HashSet<Tuple>(); // storing Tuples of Nodes, where Edges are already created
        Graph mockGraph = new Graph();

        //create number of nodes as required and add them into the graph
        for(int i=1;i<=numOfNodes;i++) {
            Node aNode = new Node(i);
            mockGraph.addNode(aNode);
        }

        List<Node> nodesList = mockGraph.getNodes();
        //create max number of edges based on the number of nodes
        //getRandomNumberInRange auto generate whole number between the first parameter to the second parameter
        int numOfRandomEdges = getRandomNumberInRange(0,maxEdges);

        Node node1 =null;
        Node node2 = null;
        Edge aEdge = null;
        for(int i=0;i<numOfRandomEdges;i++) {
            do{
                int idNode1 = getRandomNumberInRange(0, numOfNodes - 1);
                int idNode2 = getRandomNumberInRange(0, numOfNodes - 1);
                //if the both of the Nodes are the same, then generate new one
                while (idNode1 == idNode2) {
                    idNode2 = getRandomNumberInRange(0, numOfNodes - 1);
                }
                node1 = nodesList.get(idNode1);
                node2 = nodesList.get(idNode2);

                aEdge = new Edge(i,node1,node2,getRandomNumberInRange(1,100)); //add Edge to the graph
            }while(existedEdgeSet.add(new Tuple(node1, node2))==false);

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
     static class Tuple{
        private Node node1;
        private Node node2;

        Tuple(Node node1, Node node2){
            this.node1=node1;
            this.node2=node2;
        }

         @Override
         public boolean equals(Object o) {

             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
             Tuple tuple = (Tuple) o;
             return ( this.node1 == node1 && this.node2== node2) ||
                     ( this.node1 == node2 && this.node2== node1); // if order has changed the object is still the same
         }

         //commutative addition => hash calculation of each node and then sum it up, so the order of nodes is not relevant
         @Override
         public int hashCode() {
             return Objects.hash(node1)+Objects.hash(node2);
         }
     }
}