import java.util.*;

class Edg {
    char target;
    int weight;

    Edg(char target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class NodeDistance implements Comparable<NodeDistance> {
    int nodeIdx;
    int dist;

    NodeDistance(int nodeIdx, int dist) {
        this.nodeIdx = nodeIdx;
        this.dist = dist;
    }

    @Override
    public int compareTo(NodeDistance other) {
        return Integer.compare(this.dist, other.dist);
    }
}

public class Task3 {
    int V;
    LinkedList<Edg>[] adj;
    char[] vertexLabels;

    public Task3(char[] labels) {
        this.V = labels.length;
        this.vertexLabels = labels;
        this.adj = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    private int getIndex(char label) {
        for (int i = 0; i < vertexLabels.length; i++) {
            if (vertexLabels[i] == label) return i;
        }
        return -1;
    }

    public void addEdge(char u, char v, int weight) {
        int uIdx = getIndex(u);
        int vIdx = getIndex(v);
        adj[uIdx].add(new Edg(v, weight));
        adj[vIdx].add(new Edg(u, weight));
    }

    public void Dijkstra(char startLabel) {
        int start = getIndex(startLabel);
        int[] distances = new int[V];
        int[] parents = new int[V];
        boolean[] processed = new boolean[V];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        distances[start] = 0;

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
        pq.add(new NodeDistance(start, 0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            int u = current.nodeIdx;

            if (processed[u]) continue;
            processed[u] = true;

            for (Edg edge : adj[u]) {
                int v = getIndex(edge.target);
                int weight = edge.weight;

                if (!processed[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    parents[v] = u;
                    pq.add(new NodeDistance(v, distances[v]));
                }
            }
        }

        printResults(distances, parents);
    }

    private void printResults(int[] distances, int[] parents) {
        for (int i = 0; i < V; i++) {
            System.out.print("To " + vertexLabels[i] + ": Distance = " + distances[i] + ", Path = ");
            printPath(i, parents);
            System.out.println();
        }
    }

    private void printPath(int currentIdx, int[] parents) {
        if (currentIdx == -1) return;
        List<Character> path = new ArrayList<>();
        for (int at = currentIdx; at != -1; at = parents[at]) {
            path.add(vertexLabels[at]);
        }
        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + (i == path.size() - 1 ? "" : " -> "));
        }
    }

    public static void main(String[] args) {
        char[] labels = {'A', 'B', 'C', 'D', 'E'};
        Task3 g = new Task3(labels);

        g.addEdge('B', 'A', 7);
        g.addEdge('C', 'A', 8);
        g.addEdge('D', 'A', 9);
        g.addEdge('E', 'B', 9);

        g.Dijkstra('E');
    }
}
