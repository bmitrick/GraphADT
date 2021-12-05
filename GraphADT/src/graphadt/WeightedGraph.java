/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadt;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Bmitr
 */
public class WeightedGraph {
    
    private final int[][] adjacencyMatrix;
    
    /**
     * Constructs a new weighted graph based on an input weighted adjacency matrix
     * @param inputMatrix 
     */
    public WeightedGraph(int[][] inputMatrix){
        adjacencyMatrix = inputMatrix;
    }
    
    /**
     * Will run Floyd's algorithm on an graph
     * 
     * @return matrix representing the output of Floyd's algorithm
     */
    public int[][] floyd(){
        int[][] workingMatrix = adjacencyMatrix;
        
        for (int k = 0; k < workingMatrix.length; k++){
            for (int i = 0; i < workingMatrix.length; i++){
                for (int j = 0; j < workingMatrix.length; j++){
                    
                    workingMatrix[i][j] = Math.min(workingMatrix[i][j], workingMatrix[i][k] + workingMatrix[k][j]);
                   
                }
            }
            
//            System.out.println("Step " + k + ":");
//            for(int[] arr: workingMatrix){
//                System.out.println(Arrays.toString(arr));
//            }
//
//            System.out.println();
//            System.out.println();
        }
        
        return workingMatrix;
    }
    
    /**
     * Runs Prim's algorithm on a weighted graph
     * ###GRAPH MUST BE UNDIRECTED###
     * 
     * @return minimal spanning tree for the graph
     */
    public int[][] prim(){
        int[][] mst = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        for(int[] i: mst){
            Arrays.fill(i, 999999);
        }
        
        ArrayList<Integer> visited = new ArrayList<>();
        
        //Assume we start at 0
        visited.add(0);
        
        while(visited.size() < adjacencyMatrix.length){
            int cheapestEdgeWeight = Integer.MAX_VALUE;
            int cheapestEdgeX = 0;
            int cheapestEdgeY = 0;
            
            //Look through edges attached to already visited nodes
            for(int i: visited){
                int x = i;
                
                for(int y = 0; y < adjacencyMatrix.length; y++){
                    //Check you arent looking at the path from one node to itself & that y has not already been visited
                    if(y != x && !visited.contains(y)){
                        
                        //If the edge from node x to node y is the cheapest found so far, update cheapestEdgeWeight and its coords
                        if(adjacencyMatrix[x][y] < cheapestEdgeWeight){
                            cheapestEdgeWeight = adjacencyMatrix[x][y];
                            cheapestEdgeX = x;
                            cheapestEdgeY = y;
                        }
                    }
                }
            }
            
            visited.add(cheapestEdgeY);
            mst[cheapestEdgeX][cheapestEdgeY] = cheapestEdgeWeight;
            mst[cheapestEdgeY][cheapestEdgeX] = cheapestEdgeWeight;
        }
        
        //Manually sets the shortest path from a node to itself as 0
        for(int i = 0; i < mst.length; i++){
            for(int j = 0; j < mst.length; j++){
                if(i == j) mst[i][j] = 0;
            }
        }
        
        
        return mst;
    }
    
}
