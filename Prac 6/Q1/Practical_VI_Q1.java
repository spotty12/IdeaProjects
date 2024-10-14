import java.io.*;
import java.util.*;

public class Practical_VI_Q1 {
	  public static void main(String[] args) {
		  //add your code here

		  //Input
		  Scanner input = new Scanner(System.in);
		  try {
			  System.out.print("Please enter a file name (eg. graph1.txt): ");
			  String fileName = input.nextLine();
			  File file = new File("Q1/" + fileName);
			  input = new Scanner(file);
		  } catch (FileNotFoundException e) {
			  System.out.println("ERROR: File not found");
		  }

		  boolean first = true;
		  int numOfVertices = 0;
		  //List to store edges
		  List<AbstractGraph.Edge> edges = new LinkedList<>();

		  while (input.hasNextLine()) {
			String[] line = input.nextLine().split("[\\s+]");

			//Get number of vertices
			if (first) {
				numOfVertices = Integer.parseInt(line[0]);
				first = false;
				continue;
			}

			//Get all the edges
			int from = Integer.parseInt(line[0]);
			for (int i = 1; i < line.length; i++) {
				int to = Integer.parseInt(line[i]);
				edges.add(new AbstractGraph.Edge(from, to));
			}
		  }

		  //Create graph using edges and number of vertices
		  UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, numOfVertices);
		  System.out.println("Number of vertices is " + numOfVertices);
		  graph.printEdges();

		  //Get dfs spanning tree of graph
		  AbstractGraph<Integer>.Tree t = graph.dfs(0);

		  //Check if graph is connected
		  if (numOfVertices == t.getNumberOfVerticesFound()) {
			  System.out.println("The graph is connected");
		  } else {
			  System.out.println("The graph is not connected");
		  }
	  }
	}

