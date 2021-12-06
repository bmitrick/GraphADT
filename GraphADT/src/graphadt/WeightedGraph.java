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
                    
                    if(!visited.contains(y)){
                        //Check you arent looking at the path from one node to itself & that y has not already been visited
                        if(y != x){

                            //If the edge from node x to node y is the cheapest found so far, update cheapestEdgeWeight and its coords
                            if(adjacencyMatrix[x][y] < cheapestEdgeWeight){
                                cheapestEdgeWeight = adjacencyMatrix[x][y];
                                cheapestEdgeX = x;
                                cheapestEdgeY = y;
                            }
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
    
    public int[][] kruskal(){
        int[][] mst = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        
        for(int[] i: mst){
            Arrays.fill(i, 999999);
        }
        
        ArrayList<int[]> sortedEdges = new ArrayList<>();
        
        //Make an ArrayList of edges formatted as [starting node, ending node, weight] sorted by weight
        for(int k = 1; k < 12; k++){
            for(int i = 0; i < adjacencyMatrix.length; i++){
                for(int j = 0; j < adjacencyMatrix.length; j++){
                    if(adjacencyMatrix[i][j] == k){
                        int[] temp = new int[3];
                        temp[0] = i;
                        temp[1] = j;
                        temp[2] = adjacencyMatrix[i][j];
                        
                        //Double checks the edges is not already in there but labled as its inverse
                        sortedEdges.add(temp);
                    }
                }
            }
        }
        
        for(int[] arr: sortedEdges){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
        System.out.println();
        
        //Builds MST by adding in the edges until every node has been reached
        ArrayList<Integer> visitedNodes = new ArrayList<>();
        
        int edgeCount = 0;
        
        while(true){
            //Pulls the shortest unused edge
            int[] working = sortedEdges.get(0);
            
            System.out.println();
            System.out.println(Arrays.toString(working));
            
            //If the ending node has not already been visited
            if(!(visitedNodes.contains(working[1]) && visitedNodes.contains(working[0]))){
                System.out.println("not contained");
                int i = working[0];
                int j = working[1];
                
                mst[i][j] = working[2];
                mst[j][i] = working[2];
                
                if(!visitedNodes.contains(j)){
                    visitedNodes.add(j);
                }
                if(!visitedNodes.contains(i)){
                    visitedNodes.add(i);
                }
                
                edgeCount++;
            }
            
            sortedEdges.remove(0);
            
            if(edgeCount >= mst.length){
                WeightedGraph g = new WeightedGraph(mst);
                
                int[][] shortestPath = g.prim();
                
                boolean noPath = false;
                
                for(int[] arr: shortestPath){
                    for(int i: arr){
                        if(i == 999999) noPath = true;
                    }
                }
                
                if(!noPath) break;
            }
        } 
        
        return mst;
    }
}
