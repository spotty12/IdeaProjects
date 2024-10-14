import java.util.ArrayList;
import java.util.List;

public class WordLadderModel {
    public List<String> words; //A list to hold the words to be used in the model
    protected AbstractGraph<Integer>.Tree tree; // This tree stores the model
    public WordLadderModel(List <String> words, String beginWord) {
        //add your code
        this.words = words;

        //Initialise graph with edges and vertices
        UnweightedGraph<Integer> graph = new UnweightedGraph<>(getEdges(), words.size());
        //Create a spanning tree
        tree = graph.bfs(words.indexOf(beginWord));
    }

    public AbstractGraph<Integer>.Tree getTree(){
        return tree;
    }

    private List<AbstractGraph.Edge> getEdges() {
        List<AbstractGraph.Edge> edges = new ArrayList<>(); // Store edges
        //add your code here

        //Check for each word if there exists an edge with another word
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (i == j) continue;

                //Checks for single character difference between words
                if (isOneLetterDifferent(i,j)){
                    edges.add(new AbstractGraph.Edge(i, j));
                }
            }
        }
        return edges;
    }

    //Returns true if two words have a single character difference
    private boolean isOneLetterDifferent(int u, int v) {
        //add your code here
        String one = words.get(u);
        String two = words.get(v);

        //Add to count if two characters match
        int count = 0;
        for (int i = 0; i < one.length(); i++) {
            if (one.charAt(i) != two.charAt(i)) {
                count++;
            }
        }

        return count == 1;// you may need to remove this line
    }
    public void printSpanningTree(){
        // add your code here
        tree.printTree();
    }
    public void printPath(String endWord){
		//add your code here.
        //Checks if a path exists
        if (tree.getPath(words.indexOf(endWord)).size() != 1){
            tree.printPath(words.indexOf(endWord));
        } else {
            System.out.println("No path exists");
        }
    }
}
