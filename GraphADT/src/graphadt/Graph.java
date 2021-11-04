/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadt;

import java.util.ArrayList;

/**
 *
 * @author Bmitr
 */
public class Graph {
    
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
        int edgeNum = (int) (0.8 * chosen);
        
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
    }
    
    /**
     * Conducts a DFS of a graph from a given starting vertex. Saves the DFS to the given ArrayList
     * 
     * @param traversal the ArrayList to save the DFS to
     * @param startingValue the vertex to start the DFS with
     * @param visited an array of visited vertices 
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
    
    /**
     * Finds the next unvisited node in a graph after a list of visited nodes is passed in
     * 
     * @param visited an ArrayList of all the visited nodes
     * @return the index of the next unvisited node
     */
    private int findNextUnvisited(ArrayList<Integer> visited){
        for(int i = 0; i < adjacencyList.size(); i++){
            if (!visited.contains(i)) return i;
        }
        
        return 0;
    }
    
    /**
     * Checks if a given vertex has incoming edges
     * 
     * @param adjList adjacency list of graph in question
     * @param vertex vertex to check for incoming edges
     * @return if the vertex has any incoming edges
     */
    public boolean hasIncomingEdges(ArrayList<ArrayList<Integer>> adjList, int vertex){
        for(int i = 0; i < adjList.size(); i++){
            if(adjList.get(i).contains(vertex)) return true;
        }
        
        return false;
    }
    
    /**
     * Topologically sorts graph
     * @return the topological traversal of a graph as a string
     */
    public String topologicalSort(){
        ArrayList<ArrayList<Integer>> workingAdjList = (ArrayList) adjacencyList.clone();
        ArrayList<Integer> visited = new ArrayList<>();
        
        while (visited.size() < workingAdjList.size()){
            boolean noNodeWithNoIncomingEdgesExists = true;
        
            for(int i = 0; i < workingAdjList.size(); i++){
                if(!hasIncomingEdges(workingAdjList, i) && !visited.contains(i)){
                    visited.add(i);
                    workingAdjList.set(i, new ArrayList<Integer>());
                    noNodeWithNoIncomingEdgesExists = false;
                }
            }
            
            if (noNodeWithNoIncomingEdgesExists){
                return "Cannot be topologically sorted";
            }
        }
        
        
        return visited.toString();
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
}