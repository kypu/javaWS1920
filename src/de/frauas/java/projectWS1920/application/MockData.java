package de.frauas.java.projectWS1920.application;

import de.frauas.java.projectWS1920.models.MyEdge;
import de.frauas.java.projectWS1920.models.MyGraph;
import de.frauas.java.projectWS1920.models.MyNode;

import java.util.*;


public class MockData {

    public static MyGraph createMockGraphUnconnected() {
        MyNode node1 = new MyNode(1);
        MyNode node2 = new MyNode(2);
        MyNode node3 = new MyNode(3);
        MyNode node4 = new MyNode(4);
        MyNode node5 = new MyNode(5);
        MyEdge edge1 = new MyEdge(1, node2, node4, 53);

        MyGraph mockGraph = new MyGraph();
        mockGraph.addNode(node1);
        mockGraph.addNode(node2);
        mockGraph.addNode(node3);
        mockGraph.addNode(node4);
        mockGraph.addNode(node5);
        mockGraph.addEdge(edge1);

        return mockGraph;
    }

    public static MyGraph createMockGraphMultipleShortestPaths() {
        MyNode node1 = new MyNode(1);
        MyNode node2 = new MyNode(2);
        MyNode node3 = new MyNode(3);
        MyNode node4 = new MyNode(4);
        MyEdge edge1 = new MyEdge(1, node2, node4, 53);
        MyEdge edge2 = new MyEdge(2, node1, node3, 45);
        MyEdge edge3 = new MyEdge(3, node3, node4, 34);
        MyEdge edge4 = new MyEdge(4, node1, node4, 79);

        MyGraph mockGraph = new MyGraph();
        mockGraph.addNode(node1);
        mockGraph.addNode(node2);
        mockGraph.addNode(node3);
        mockGraph.addNode(node4);
        mockGraph.addEdge(edge1);
        mockGraph.addEdge(edge2);
        mockGraph.addEdge(edge3);
        mockGraph.addEdge(edge4);

        return mockGraph;
    }

    public static MyGraph createRandomMockGraph(int numOfNodes) {

        int maxEdges=numOfNodes*(numOfNodes-1)/2;
        HashSet<Tuple> existedEdgeSet = new HashSet<Tuple>(); // storing Tuples of Nodes, where Edges are already created
        MyGraph mockGraph = new MyGraph();

        //create number of nodes as required and add them into the graph
        for(int i=1;i<=numOfNodes;i++) {
            MyNode aNode = new MyNode(i);
            mockGraph.addNode(aNode);
        }

        // nodes as a list instead of a set here because need to access specific elements
        List<MyNode> nodesList = new ArrayList<>();
        nodesList.addAll(mockGraph.getNodes());
        //create max number of edges based on the number of nodes
        //getRandomNumberInRange auto generate whole number between the first parameter to the second parameter
        int numOfRandomEdges = getRandomNumberInRange(0,maxEdges);
        //template for random generation
        MyNode node1 =null;
        MyNode node2 = null;
        MyEdge randomEdge = null;
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

                randomEdge = new MyEdge(i,node1,node2,getRandomNumberInRange(1,100)); //add Edge to the graph
            }while(existedEdgeSet.add(new Tuple(node1, node2))==false);

           mockGraph.addEdge(randomEdge);
        }

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
        private MyNode node1;
        private MyNode node2;

        Tuple(MyNode node1, MyNode node2){
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