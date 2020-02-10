# javaWS1920

External libraries used:

com.tinkerpop.blueprints:blueprints-core:2.3.0 (for the graph reader)
org.apache.directory.studio:org.apache.commons.io:2.42 (for os-independant file names)
org.realityforge.org.jetbrains.annotations:org.jetbrains.annotations:1.2.02 (for nullable annotation)
commons-cli:commons-cli:1.4 (for command line arguments)

(File > Project Structure > Libraries > + > Maven > Search)


Add program arguments to local intellij configuration:

src/de/frauas/java/projectWS1920/resources/small_graph.graphml -a testOutput (for mac)
(replace each "/" with two "\\" for windows)

(Edit Configurations > Application > (your configuration name) > Program arguments)


