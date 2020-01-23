package de.frauas.java.projectWS1920;

import de.frauas.java.projectWS1920.application.MockData;
import de.frauas.java.projectWS1920.models.MyGraph;
import org.junit.jupiter.api.Test;

import static de.frauas.java.projectWS1920.models.MyGraph.DIAMETER_UNDEFINED;
import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

    @Test
    void unconnectedGraph_Test() {
        // arrange
        MyGraph unconnectedTestGraph = MockData.createMockGraphUnconnected();
        // act
        unconnectedTestGraph.initialiseGraphAttributes();
        // assert
        assertFalse(unconnectedTestGraph.getConnected());
        assertEquals(DIAMETER_UNDEFINED, unconnectedTestGraph.getDiameter());
    }

    @Test
    void multiplePaths_Test() {
        // arrange
        MyGraph multiplePathsTestGraph = MockData.createMockGraphMultipleShortestPaths();
        // act
        multiplePathsTestGraph.initialiseGraphAttributes();
        // assert
        assertEquals(2, multiplePathsTestGraph);
    }
}


