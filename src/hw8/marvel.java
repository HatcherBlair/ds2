package hw8;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.graph.*;

public class marvel {

    public static void main(String[] args) {
        Long start;
        Long finish;
        System.out.println("Starting to build Bipartite Graph");
        start = System.currentTimeMillis();
        NodesAreEdgesAndComics graph1 = new NodesAreEdgesAndComics("src/hw8/nodes.csv",
                "src/hw8/edges.csv");
        finish = System.currentTimeMillis();
        System.out.printf("Finished in: %dms\n", finish - start);
        System.out.printf("Num Edges: %d, Num Nodes: %d\n\n", graph1._graph.edges().size(),
                graph1._graph.nodes().size());

        System.out.println("Starting to build graph with heros as nodes and comics as edges");
        start = System.currentTimeMillis();
        EdgesAreComics graph2 = new EdgesAreComics("src/hw8/nodes.csv", "src/hw8/edges.csv");
        finish = System.currentTimeMillis();
        System.out.printf("Finished in: %dms\n", finish - start);
        System.out.printf("Num Edges: %d, Num Nodes: %d\n\n", graph2._graph.edges().size(),
                graph2._graph.nodes().size());
    }

    static class NodesAreEdgesAndComics {
        private MutableGraph<String> _graph;

        NodesAreEdgesAndComics(String pathNodeTypes, String pathEdges) {
            _graph = GraphBuilder.undirected().build();
            // Adds edges and (silently) adds nodes.
            // Unless the input is malformed there will never be a hero->hero or
            // comic->comic edge
            try {
                File file = new File(pathEdges);
                Stream<String> lines = Files.lines(file.toPath());
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    if (!("hero".compareTo(parts[0]) == 0)) {
                        _graph.putEdge(parts[0], parts[1]);
                    }
                });
                lines.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    static class EdgesAreComics {
        private HashMap<String, Set<String>> comics;
        private MutableValueGraph<String, String> _graph;

        EdgesAreComics(String pathNodeTypes, String pathEdges) {
            comics = new HashMap<>();
            // Populate comics with the keys
            try {
                File file = new File(pathNodeTypes);
                Stream<String> lines = Files.lines(file.toPath());
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    if ("node".compareTo(parts[0]) == 0) {
                        // Skip the first line
                    } else {
                        if ("comic".compareTo(parts[1]) == 0) {
                            comics.put(parts[0], new HashSet<String>());
                        }

                    }
                });
                lines.close();
            } catch (Exception e) {
            }

            // Populate the sets in comics
            try {
                File file = new File(pathEdges);
                Stream<String> lines = Files.lines(file.toPath());
                lines.forEach(line -> {
                    String[] parts = line.split(",");
                    // Skip the first line
                    if (!("hero".compareTo(parts[0]) == 0)) {
                        Set<String> comic = comics.get(parts[1]);
                        if (comic != null) {
                            comic.add(parts[0]);
                        } else {
                            comic = new HashSet<String>();
                            comic.add(parts[0]);
                            comics.put(parts[1], comic);
                        }
                    }
                });
                lines.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            _graph = ValueGraphBuilder.undirected().build();

            // Add the edges (putEdge adds nodes silently)
            for (String comic : comics.keySet()) {
                List<String> heros = new ArrayList<>(comics.get(comic));
                for (int i = 0; i < heros.size(); i++) {
                    for (int j = i + 1; j < heros.size(); j++) {
                        _graph.putEdgeValue(heros.get(i), heros.get(j), comic);
                    }
                }
            }
        }
    }
}
