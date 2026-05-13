import java.util.LinkedList;

class E{
    char target;
    int weight;

    E(char target, int weight){
        this.target = target;
        this.weight = weight;
    }
}
public class Task1 {

    int V;
    LinkedList<E>[] adj;
    char[] vertexLabels;

    public Task1(char[] labels){
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

        adj[uIdx].add(new E(v, weight));
        adj[vIdx].add(new E(u, weight));
    }

    public void printGraph(){
        for(int i = 0; i < V; i++){
            System.out.println(vertexLabels[i] + ": ");
            for(E edge : adj[i]){
                System.out.println(edge.target + "(" + edge.weight + ")");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        char[] labels = {'A', 'B', 'C', 'D', 'E'};
        Task1 g = new Task1(labels);

        g.addEdge('B', 'A', 7);
        g.addEdge('C', 'A', 8);
        g.addEdge('D', 'A', 9);
        g.addEdge('E', 'B', 9);

        g.printGraph();
    }
}
