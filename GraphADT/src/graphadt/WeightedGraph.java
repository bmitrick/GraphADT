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
    
    public int[][] prim(){
        int[][] mst = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        
        ArrayList<Integer> visited = new ArrayList<>();
        
        return mst;
    }
    
}
