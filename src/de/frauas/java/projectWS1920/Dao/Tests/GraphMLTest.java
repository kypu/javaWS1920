package de.frauas.java.projectWS1920.Dao.Tests;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import de.frauas.java.projectWS1920.models.MyGraph;
import de.frauas.java.projectWS1920.models.MyEdge;
import de.frauas.java.projectWS1920.models.MyNode;
import de.frauas.java.projectWS1920.Bc.Validate;
import de.frauas.java.projectWS1920.Dao.GraphML;
import de.frauas.java.projectWS1920.resources.Resource;


import static org.junit.jupiter.api.Assertions.*;

class GraphMLTest
{
    // filepath to test file within resource folder
    //is cwd needed? if so, add "\\" to beginning of Resource.filepath
    // String currentWorkingDirectory = System.getProperty("user.dir");
    private String resourceFilepath = Resource.getFilepath();
    private String graphMLFile = "small_graph.graphml";
    private final String testPath = resourceFilepath + graphMLFile;

    /*
    Checks if all node ids of given GraphML file have been read in correctly.
    Small_graph.graphml contains nodes with id ranging from 0 - 14
    */
    @Test
    void importData_NodesAvailability_Test() throws Exception
    {
        assertTrue(Validate.filepath(testPath));

        // arrange

        // act
        MyGraph testeeGraph = GraphML.importData(testPath);

        // assert
        // Check if all node ids are correct and range from 0 - 14
        for (Integer i = 0; i <= 14; i++)
        {
            assertNotNull(testeeGraph.getNodeById(i));
        }

        // Check if the total of read-in nodes are exactly 15
        assertEquals(15, testeeGraph.getNodes().size());
    }

    /*
    Checks if all edge ids of given GraphML file have been read in correctly.
    Small_graph.graphml contains edges with id ranging from 0 - 27
     */
    @Test
    void importData_EdgesAvailability_Test() throws Exception
    {
        assertTrue(Validate.filepath(testPath));

        // arrange

        // act
        MyGraph testeeGraph = GraphML.importData(testPath);

        // assert
        // Check if all node ids are correct and range from 0 - 14
        for (MyEdge edge : testeeGraph.getEdges())
        {
            assertTrue(edge.getEdgeId() <= 27);
        }

        // Check if the total of read-in nodes are exactly 15
        assertEquals(28, testeeGraph.getEdges().size());
    }

    /*
    Checks if all an example edge has been read in and stored correctly.
     */
    @Test
    void importData_EdgeExample_Test() throws Exception
    {
        // arrange
        MyEdge exampleEdge = new MyEdge(
                0,
                new MyNode(0),
                new MyNode(2),
                8
        );

        // act
        MyGraph testeeGraph = GraphML.importData(testPath);
        MyEdge testeeEdge = getEdgeByIdHelper(testeeGraph, 0);

        // assert
        assertEquals(exampleEdge.getEdgeId(), testeeEdge.getEdgeId());
        assertEquals(exampleEdge.getWeight(), testeeEdge.getWeight());
        assertEquals(exampleEdge.getOriginNode().getNodeId(), testeeEdge.getOriginNode().getNodeId());
        assertEquals(exampleEdge.getDestinationNode().getNodeId(), testeeEdge.getDestinationNode().getNodeId());
    }

    /**
     *
     * @param graph Graph that's being searched.
     * @param edgeId Edge id you're looking for.
     * @return Edge with the id you're looking for in given graph. If not found, returns null.
     */
    @Nullable
    private MyEdge getEdgeByIdHelper(MyGraph graph, Integer edgeId)
    {
        for (MyEdge edge : graph.getEdges())
        {
            if (edge.getEdgeId() == edgeId) return edge;
        }
        return null;
    }
}