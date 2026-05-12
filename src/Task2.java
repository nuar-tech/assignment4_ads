import java.util.LinkedList;
import java.util.Queue;

class Edge{
    char target;
    int weight;

    Edge(char target, int weight){
        this.target = target;
        this.weight = weight;
    }
}
public class Task2 {

    int V;
    LinkedList<Edge>[] adj;
    char[] vertexLabels;

    public Task2(char[] labels){
        this.V = labels.length;
        this.vertexLabels = labels;
        this.adj = new LinkedList[V];

        for(int i = 0; i < V; i++){
            adj[i] = new LinkedList<>();
        }
    }

    private int getIndex(char label){
        for(int i = 0; i < vertexLabels.length; i++){
            if(vertexLabels[i] == label)
                return i;
        }
        return -1;
    }

    public void addEdge(char u, char v, int weight){
        int uIdx = getIndex(u);
        int vIdx = getIndex(v);

        adj[uIdx].add(new Edge(v, weight));
        adj[vIdx].add(new Edge(u, weight));

    }

    public void printGraph(){
        for(int i = 0; i < V; i++){
            System.out.println(vertexLabels[i] + ": ");
            for(Edge edge : adj[i]){
                System.out.println(edge.target + "(" + edge.weight + ")");
            }
            System.out.println();
        }
    }

    public void dfs(char startLabel){
        boolean[] visited = new boolean[V];
        int startIdx = getIndex(startLabel);
        System.out.println("DFS starting from " + startLabel + ": ");
        visitNode(startIdx, visited);
        System.out.println();
    }

    public void visitNode(int vIdx, boolean[] visited) {
        visited[vIdx] = true;
        System.out.println(vertexLabels[vIdx] + " ");


        for (Edge edge : adj[vIdx]) {
            int neighborIdx = getIndex(edge.target);
            if (!visited[neighborIdx]) {
                visitNode(neighborIdx, visited);
            }
        }
    }

    public void bfs(char startLabel) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        int startIdx = getIndex(startLabel);

        visited[startIdx] = true;
        queue.add(startIdx);

        System.out.print("BFS Order starting from " + startLabel + ": ");

        while (!queue.isEmpty()) {
            int vIdx = queue.poll();
            System.out.print(vertexLabels[vIdx] + " ");

            for (Edge edge : adj[vIdx]) {
                int neighborIdx = getIndex(edge.target);
                if (!visited[neighborIdx]) {
                    visited[neighborIdx] = true;
                    queue.add(neighborIdx);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        char[] labels = {'A', 'B', 'C', 'D', 'E'};
        Task2 g = new Task2(labels);

        g.addEdge('B', 'A', 7);
        g.addEdge('C', 'A', 8);
        g.addEdge('D', 'A', 9);
        g.addEdge('E', 'B', 9);

        g.printGraph();
        System.out.println();
        g.dfs('A');
        g.bfs('D');
    }
}
