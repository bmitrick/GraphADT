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
    
    public ArrayList<ArrayList<Integer>> adjacencyList;

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
            //Randomly assigns every node 1 edge
            for(int i = 0; i < adjacencyList.size(); i++){
                int randomVerticy = (int) (Math.random()*(adjacencyList.size()));

                while (randomVerticy == i || adjacencyList.get(randomVerticy).contains(i) && !adjacencyList.get(i).contains(randomVerticy)){
                    randomVerticy = (int) (Math.random()*(adjacencyList.size()));
                }

                adjacencyList.get(i).add(randomVerticy);
            }

            edgesToAssign = edgesToAssign - adjacencyList.size();

            while(edgesToAssign > 0){
                int randomStartVerticy = (int) (Math.random()*(adjacencyList.size()));
                int randomEndVerticy = (int) (Math.random()*(adjacencyList.size()));

                //Verifies edge does not already exist and that the created edge does not lead an edge to itself
                if(!adjacencyList.get(randomStartVerticy).contains(randomEndVerticy) && !adjacencyList.get(randomEndVerticy).contains(randomStartVerticy) && randomStartVerticy != randomEndVerticy){
                    adjacencyList.get(randomStartVerticy).add(randomEndVerticy);

                    edgesToAssign--;
                }

            }
        }else{
            //Randomly assigns every node 1 edge
            for(int i = 0; i < adjacencyList.size(); i++){
                int randomVerticy = (int) (Math.random()*(adjacencyList.size()));

                while (randomVerticy == i || adjacencyList.get(randomVerticy).contains(i) && !adjacencyList.get(i).contains(randomVerticy)){
                    randomVerticy = (int) (Math.random()*(adjacencyList.size()));
                }

                adjacencyList.get(i).add(randomVerticy);
                adjacencyList.get(randomVerticy).add(i);
            }

            edgesToAssign = edgesToAssign - adjacencyList.size();

            while(edgesToAssign > 0){
                int randomStartVerticy = (int) (Math.random()*(adjacencyList.size()));
                int randomEndVerticy = (int) (Math.random()*(adjacencyList.size()));

                //Verifies edge does not already exist and that the created edge does not lead an edge to itself
                if(!adjacencyList.get(randomStartVerticy).contains(randomEndVerticy) && randomStartVerticy != randomEndVerticy){
                    adjacencyList.get(randomStartVerticy).add(randomEndVerticy);
                    adjacencyList.get(randomEndVerticy).add(randomStartVerticy);

                    
                    edgesToAssign--;
                }

            }
        }
        
    }
    
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
     * @return 
     */
    public StringBuilder adjacencyListToStringBuilder(){
        StringBuilder returnable = new StringBuilder("");
        
        returnable.append("Adjacency List for the graph:");
        
        for(int i=0; i < adjacencyList.size(); i++) {
            returnable.append(System.getProperty("line.separator"));
            returnable.append("Verticy ").append(i).append(": ").append(adjacencyList.get(i));
        }
        
        return returnable;
    }
    
    //I added the below functions as well as the Serializer class in order to save preconstructed graphs so I don't have to regenerate large ones
    /**
     * Saves the adjacency list as a readable output to a file
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
    
    public ArrayList<ArrayList<Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}
