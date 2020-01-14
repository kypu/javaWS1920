package de.frauas.java.projectWS1920.Dao;

import de.frauas.java.projectWS1920.models.Edge;
import de.frauas.java.projectWS1920.models.Graph;
import de.frauas.java.projectWS1920.models.Node;

import java.io.InputStream;

public interface IGraphML<N, E>
{
    InputStream createInputStream(String p);
    Iterable<Node> parseNodes(N n);
    Iterable<Edge> parseEdges(E e);
    Graph importData();
    void exportData();
}
