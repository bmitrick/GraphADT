/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadt;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bmitr
 */
public class Graph implements Serializable{
    
    private ArrayList<ArrayList<Integer>> adjacencyList;

    /**
     * Constructs a new graph based on the number of desired vertices
     * 
     * @param numVertices number of vertices to be included in the new graph
     * @param isDirected if the graph is directed or not
     */
    public Graph(int numVertices, boolean isDirected) {
        this.adjacencyList = new ArrayList<>(numVertices);
        
        for(int i=0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList());
        }
        
        //Determine the number of edges needed for the graph1
        int chosen = (numVertices*(numVertices-1))/2;
        int edgeNum = (int) (0.3 * chosen);
        
        //Generate the edges for each entry in the adjacency list
        generateEdges(edgeNum, isDirected);
    }
    
    /**
     * Generates the edges for the graph
     * 
     * @param edgeNum the number of edges to be put in the graph
     * @param isDirected if the graph is directed or not
     */
    private void generateEdges(int edgeNum, boolean isDirected){
        int edgesToAssign = edgeNum;
        
        if (isDirected){
            while(edgesToAssign > 0){
                int randomStartVertex = (int) (Math.random()*(adjacencyList.size()));
                int randomEndVertex = (int) (Math.random()*(adjacencyList.size()));

                //Verifies edge does not already exist and that the created edge does not lead an edge to itself
                if(!adjacencyList.get(randomStartVertex).contains(randomEndVertex) && !adjacencyList.get(randomEndVertex).contains(randomStartVertex) && randomStartVertex != randomEndVertex){
                    adjacencyList.get(randomStartVertex).add(randomEndVertex);

                    edgesToAssign--;
                }

            }
        }else{
            while(edgesToAssign > 0){
                int randomStartVertex = (int) (Math.random()*(adjacencyList.size()));
                int randomEndVertex = (int) (Math.random()*(adjacencyList.size()));

                //Verifies edge does not already exist and that the created edge does not lead an edge to itself
                if(randomStartVertex != randomEndVertex && !adjacencyList.get(randomEndVertex).contains(randomStartVertex)){
                    adjacencyList.get(randomStartVertex).add(randomEndVertex);
                    adjacencyList.get(randomEndVertex).add(randomStartVertex);


                    edgesToAssign--;
                }

            }  
        }
        
        /*
        if (isDirected){
            if(edgeNum >= adjacencyList.size()){
                //Randomly assigns every node 1 edge
                for(int i = 0; i < adjacencyList.size(); i++){
                    int randomVertex = (int) (Math.random()*(adjacencyList.size()));

                    while (randomVertex == i || adjacencyList.get(i).contains(randomVertex)){
                        randomVertex = (int) (Math.random()*(adjacencyList.size()));
                    }

                    adjacencyList.get(i).add(randomVertex);
                }

                edgesToAssign = edgesToAssign - adjacencyList.size();

                while(edgesToAssign > 0){
                    int randomStartVertex = (int) (Math.random()*(adjacencyList.size()));
                    int randomEndVertex = (int) (Math.random()*(adjacencyList.size()));

                    //Verifies edge does not already exist and that the created edge does not lead an edge to itself
                    if(!adjacencyList.get(randomStartVertex).contains(randomEndVertex) && !adjacencyList.get(randomEndVertex).contains(randomStartVertex) && randomStartVertex != randomEndVertex){
                        adjacencyList.get(randomStartVertex).add(randomEndVertex);

                        edgesToAssign--;
                    }

                }
            } else {
                for(int i = 0; i < edgeNum; i++){
                    int randomStartVertex = (int) (Math.random()*(adjacencyList.size()));
                    int randomEndVertex = (int) (Math.random()*(adjacencyList.size()));
                    
                    while (randomStartVertex  == randomEndVertex || adjacencyList.get(randomStartVertex).contains(randomEndVertex)){
                        randomEndVertex = (int) (Math.random()*(adjacencyList.size()));
                    }
                    
                    adjacencyList.get(i).add(randomEndVertex);
                }
            }
        }else{
            if (edgeNum >= adjacencyList.size()){
                //Randomly assigns every node 1 edge
                for(int i = 0; i < adjacencyList.size(); i++){
                    int randomVertex = (int) (Math.random()*(adjacencyList.size()));

                    while (randomVertex == i || adjacencyList.get(randomVertex).contains(i)){
                        randomVertex = (int) (Math.random()*(adjacencyList.size()));
                    }

                    adjacencyList.get(i).add(randomVertex);
                    adjacencyList.get(randomVertex).add(i);
                }

                edgesToAssign = edgesToAssign - adjacencyList.size();

                while(edgesToAssign > 0){
                    int randomStartVertex = (int) (Math.random()*(adjacencyList.size()));
                    int randomEndVertex = (int) (Math.random()*(adjacencyList.size()));

                    //Verifies edge does not already exist and that the created edge does not lead an edge to itself
                    if(randomStartVertex != randomEndVertex && !adjacencyList.get(randomEndVertex).contains(randomStartVertex)){
                        adjacencyList.get(randomStartVertex).add(randomEndVertex);
                        adjacencyList.get(randomEndVertex).add(randomStartVertex);


                        edgesToAssign--;
                    }

                }
            }else {
                for(int i = 0; i < edgeNum; i++){
                    int randomStartVertex = (int) (Math.random()*(adjacencyList.size()));
                    int randomEndVertex = (int) (Math.random()*(adjacencyList.size()));
                    
                    while (randomStartVertex  == randomEndVertex || adjacencyList.get(randomStartVertex).contains(randomEndVertex)){
                        randomEndVertex = (int) (Math.random()*(adjacencyList.size()));
                    }
                    
                    adjacencyList.get(randomStartVertex).add(randomEndVertex);
                    adjacencyList.get(randomEndVertex).add(randomStartVertex);
                }
            }
            
        }*/
        
    }
    
    /**
     * Conducts a DFS of a graph from a given starting vertex. Saves the DFS to the given ArrayList
     * 
     * @param traversal the ArrayList to save the DFS to
     * @param startingValue the vertex to start the DFS with
     * @param visited an array of visited verticies
     */
    public void DFS(ArrayList<Integer> traversal, int startingValue, ArrayList<Integer> visited){ 
        visited.add(startingValue);
        
        for(int i = 0; i < adjacencyList.get(startingValue).size(); i++){
            if(!visited.contains(adjacencyList.get(startingValue).get(i))){
                DFS(traversal, adjacencyList.get(startingValue).get(i), visited);
            }
        }
        
        traversal.add(startingValue);
    }
    
    /**
     * Identifies the clusters for a given graph and returns them as an ArrayList of ArrayLists
     * 
     * @return the clusters for a given graph 
     */
    public ArrayList<ArrayList<Integer>> identifyClusters(){
        ArrayList<ArrayList<Integer>> clusters = new ArrayList<>();
        
        ArrayList<Integer> visited = new ArrayList<>();
        
        int startingValue = 0;
        ArrayList<Integer> traversal = new ArrayList<>();
        this.DFS(traversal, startingValue, visited);
        
        clusters.add(traversal);
        
        int nextUnvisited = findNextUnvisited(visited);
        while(nextUnvisited != 0){
            traversal = new ArrayList<>();
            startingValue = nextUnvisited;
            this.DFS(traversal, startingValue, visited);
            
            clusters.add(traversal);
            
            nextUnvisited = findNextUnvisited(visited);
        }
        
        return clusters;
    }
    
    private int findNextUnvisited(ArrayList<Integer> visited){
        for(int i = 0; i < adjacencyList.size(); i++){
            if (!visited.contains(i)) return i;
        }
        
        return 0;
    }
    
    //Below are mostly just helper functions in order to make testing easier
     
    /**
     * Verifies that a given graph is undirected
     * 
     * @return true if the graph is undirected, false otherwise
     */
    public boolean verifyUndirected(){
        for(int i = 0; i < adjacencyList.size(); i++){
            for(int j = 0; j < adjacencyList.get(i).size(); j++){
                int connectedVertex = adjacencyList.get(i).get(j);
                
                if(!adjacencyList.get(connectedVertex).contains(i)) return false;
            }
        }
        
        return true;
    }
    
    /**
     * Returns a StringBuilder output representing the adjacency list of the graph 
     * 
     * @return a StringBuilder output representing the adjacency list of the graph 
     */
    public StringBuilder adjacencyListToStringBuilder(){
        StringBuilder returnable = new StringBuilder("");
        
        returnable.append("Adjacency List for the graph:");
        
        for(int i=0; i < adjacencyList.size(); i++) {
            returnable.append(System.getProperty("line.separator"));
            returnable.append("Vertex ").append(i).append(": ").append(adjacencyList.get(i));
        }
        
        return returnable;
    }
    
    /**
     * Returns a StringBuilder output representing the connected components of the graph
     * 
     * @return a StringBuilder output representing the connected components of the graph 
     */
    public StringBuilder connectedComponentsToStringBuilder(){
        StringBuilder returnable = new StringBuilder("");
        
        returnable.append("Clusters of the Graph:");
        
        ArrayList<ArrayList<Integer>> clusters = this.identifyClusters();
        
        for(int i = 0; i < clusters.size(); i++){
            returnable.append(System.getProperty("line.separator"));
            returnable.append("Cluster ").append(i+1).append(": ").append(clusters.get(i));
        }
        
        return returnable;
    }
    
    //I added the below functions as well as the Serializer class in order to save preconstructed graphs so I don't have to regenerate large ones
    /**
     * Saves the adjacency list as a readable output to a file
     * @throws java.io.FileNotFoundException
     */
    public void saveToFile() throws FileNotFoundException{
        try (PrintWriter out = new PrintWriter("graph.txt")) {
            out.println(adjacencyListToStringBuilder());
        }
    }
    
    @Override
    public boolean equals(Object g) {
        if (g instanceof Graph) {
            Graph replicaGraph = (Graph) g;

            if (replicaGraph.getAdjacencyList().equals(this.adjacencyList)) return true;
        }
        return false;
    }
    
    /**
     * getter for adjacencyList
     * 
     *  @return adjacencyList
     */
    public ArrayList<ArrayList<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
