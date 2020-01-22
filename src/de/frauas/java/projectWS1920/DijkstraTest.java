package de.frauas.java.projectWS1920;

import de.frauas.java.projectWS1920.application.MockData;
import de.frauas.java.projectWS1920.models.MyGraph;
import de.frauas.java.projectWS1920.models.MyNode;
import org.junit.jupiter.api.Test;

import static de.frauas.java.projectWS1920.models.MyGraph.DIAMETER_UNDEFINED;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

    MyGraph unconnectedTestGraph = MockData.createMockGraphUnconnected();
    MyGraph multiplePathsTestGraph = MockData.createMockGraphMultipleShortestPaths();

    @Test
    void connectedFalse_Test() {
        // arrange
        multiplePathsTestGraph.setAdjacentNodes();
        // act
        unconnectedTestGraph.calculateShortestPathsFrom(unconnectedTestGraph.getNodeById(1));
        // assert
        assertFalse(unconnectedTestGraph.getConnected());
    }

    @Test
    void connectedTrue_Test() {
        // arrange
        multiplePathsTestGraph.setAdjacentNodes();
        // act
        multiplePathsTestGraph.calculateShortestPathsFrom(multiplePathsTestGraph.getNodeById(2));
        // assert
        assertTrue(multiplePathsTestGraph.getConnected());
    }

    @Test
    void multiplePaths_Test() {
        // arrange
        multiplePathsTestGraph.setAdjacentNodes();
        MyNode originNode = multiplePathsTestGraph.getNodeById(1);
        MyNode destinationNode = multiplePathsTestGraph.getNodeById(4);
        // act
        multiplePathsTestGraph.calculateShortestPathsFrom(originNode);
        originNode.calculateDirectionsTo(destinationNode);
        // assert
        assertEquals(2, originNode.getDirectionsTo(destinationNode).size());
    }

    @Test
    void diameterUndefined_Test() {
        // arrange
        unconnectedTestGraph.setAdjacentNodes();
        // act
        for (MyNode node : unconnectedTestGraph.getNodes()) {
            unconnectedTestGraph.calculateShortestPathsFrom(node);
        }
        unconnectedTestGraph.setDiameter();
        // assert
        assertEquals(DIAMETER_UNDEFINED, unconnectedTestGraph.getDiameter());
    }

}
