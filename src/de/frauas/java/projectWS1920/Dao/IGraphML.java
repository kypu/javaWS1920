package de.frauas.java.projectWS1920.Dao;

import de.frauas.java.projectWS1920.models.MyEdge;
import de.frauas.java.projectWS1920.models.MyGraph;
import de.frauas.java.projectWS1920.models.MyNode;

import java.io.InputStream;

public interface IGraphML<N, E>
{
    InputStream createInputStream(String p);
    Iterable<MyNode> parseNodes(N n);
    Iterable<MyEdge> parseEdges(E e);
    MyGraph importData();
    void exportData();
}
