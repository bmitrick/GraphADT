 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadt;

import java.util.Arrays;

/**
 *
 * @author Bmitr
 */
public class GraphADT {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //testFloyd();
        
        testPrim();
    }
    
    private static int[][] makeRandomDirectedAdjMatrix(int nodes){
        int[][] matrix = new int[nodes][nodes];
        
        for(int i = 0; i < nodes; i++){
            for(int j = 0; j < nodes; j++){
                if (i == j) matrix[i][j] =0;
                else{
                    //Randomly determines if a node will be connected to another or not. Has a 50/50 chance to be connected
               
                    //If it is connected, it will be assigned a random weight in the range 1 to 11
                    if(Math.random() < 0.5) matrix[i][j] = (int) (Math.random() * 10)+1;

                    //If it is not connected, it will have value 999999 for convenience sake
                    else matrix[i][j] = 999999;
                }
                
            }
        }
        
        return matrix;
    }
    
    private static int[][] makeRandomUndirectedAdjMatrix(int nodes){
        int[][] matrix = new int[nodes][nodes];
        
        for(int i = 0; i < nodes/2 +1 ; i++){
            for(int j = i; j < nodes; j++){
                if (i == j) matrix[i][j] = 0;
                else{
                    //Randomly determines if a node will be connected to another or not. Has a 50/50 chance to be connected
               
                    //If it is connected, it will be assigned a random weight in the range 1 to 11
                    if(Math.random() < 0.5){
                        matrix[i][j] = (int) (Math.random() * 10)+1;
                        matrix[j][i] = matrix[i][j];
                    }

                    //If it is not connected, it will have value 999999 for convenience sake
                    else{
                        matrix[i][j] = 999999;
                        matrix[j][i] = matrix[i][j];
                    }
                }
            }
        }
        
        return matrix;
    }
    
    private static void testFloyd(){
        long start;
        long end;
        
        
        //Floyd's testing
        
        //Attempting Floyd's on a 4 node matrix
        start = System.currentTimeMillis();
//        int[][] matrix1  = { {0, 999999, 3, 999999},
//                            {2, 0, 999999, 999999},
//                            {999999,7, 0, 1},
//                            {6, 999999, 999999, 0}};

        int[][] matrix1 = makeRandomDirectedAdjMatrix(4);
        
        System.out.println("Starting Matrix:");
        for(int[] arr: matrix1){
            System.out.println(Arrays.toString(arr));
        }
        
        System.out.println();
        System.out.println();
                            
        WeightedGraph graph1 = new WeightedGraph(matrix1);
        
        int[][] finished1 = graph1.floyd();
        
        System.out.println("Finished Matrix:");
        for(int[] arr: finished1){
            System.out.println(Arrays.toString(arr));
        }
        
        end = System.currentTimeMillis();
        
        System.out.println("Constructing and running Floyd's on a graph with 4 nodes took " + (end-start) + " ms");
        System.out.println();
        System.out.println();
        
        
        
        
        
        //Attempting Floyd's on a 10 node matrix
        start = System.currentTimeMillis();
        int[][] matrix2 = makeRandomDirectedAdjMatrix(10);
        
        System.out.println("Starting Matrix:");
        for(int[] arr: matrix2){
            System.out.println(Arrays.toString(arr));
        }
        
        System.out.println();
        System.out.println();
                            
        WeightedGraph graph2 = new WeightedGraph(matrix2);
        
        int[][] finished2 = graph2.floyd();
        
        System.out.println("Finished Matrix:");
        for(int[] arr: finished2){
            System.out.println(Arrays.toString(arr));
        }
        
        end = System.currentTimeMillis();
        
        System.out.println("Constructing and running Floyd's on a graph with 10 nodes took " + (end-start) + " ms");
        System.out.println();
        System.out.println();
        
        
        
        
        
        //Attempting Floyd's on a 100 node matrix
        start = System.currentTimeMillis();
        int[][] matrix3 = makeRandomDirectedAdjMatrix(100);
        
        System.out.println("Starting Matrix:");
        for(int[] arr: matrix3){
            System.out.println(Arrays.toString(arr));
        }
                            
        WeightedGraph graph3 = new WeightedGraph(matrix3);
        
        int[][] finished3 = graph3.floyd();
        
        System.out.println("Finished Matrix:");
        for(int[] arr: finished3){
            System.out.println(Arrays.toString(arr));
        }
        
        end = System.currentTimeMillis();
        
        System.out.println("Constructing and running Floyd's on a graph with 100 nodes took " + (end-start) + " ms");
        System.out.println();
        System.out.println();
        
        
        
        
        
        //Attempting Floyd's on a 1000 node matrix
        start = System.currentTimeMillis();
        int[][] matrix4 = makeRandomDirectedAdjMatrix(1000);
        
//        System.out.println("Starting Matrix:");
//        for(int[] arr: matrix4){
//            System.out.println(Arrays.toString(arr));
//        }
                            
        WeightedGraph graph4 = new WeightedGraph(matrix4);
        
        int[][] finished4 = graph4.floyd();
        
//        System.out.println("Finished Matrix:");
//        for(int[] arr: finished4){
//            System.out.println(Arrays.toString(arr));
//        }
        
        end = System.currentTimeMillis();
        
        System.out.println("Constructing and running Floyd's on a graph with 1000 nodes took " + (end-start) + " ms (not including printing graph and output)");
        System.out.println();
        System.out.println();
          
        
        
        
        
        //Attempting Floyd's on a 10000 node matrix
        start = System.currentTimeMillis();
        int[][] matrix5 = makeRandomDirectedAdjMatrix(10000);
        
//        System.out.println("Starting Matrix:");
//        for(int[] arr: matrix5){
//            System.out.println(Arrays.toString(arr));
//        }
                            
        WeightedGraph graph5 = new WeightedGraph(matrix5);
        
        int[][] finished5 = graph5.floyd();
        
//        System.out.println("Finished Matrix:");
//        for(int[] arr: finished5){
//            System.out.println(Arrays.toString(arr));
//        }
        
        end = System.currentTimeMillis();
        
        System.out.println("Constructing and running Floyd's on a graph with 10000 nodes took " + (end-start) + " ms (not including printing graph and output)");
        System.out.println();
        System.out.println();
    }
    
    private static void testPrim(){
        long start;
        long end;
        
        
        //Prim's testing
        
        //Attempting Prim's on a 4 node matrix
        start = System.currentTimeMillis();
        
//        int[][] matrix1  = {{999999,2,1,3},  
//                            {2,999999,7,999999},
//                            {1,7,999999,2},
//                            {3,9999999,2,999999}};


        int[][] matrix1 = makeRandomUndirectedAdjMatrix(4);
        
        System.out.println("Starting Matrix:");
        for(int[] arr: matrix1){
            System.out.println(Arrays.toString(arr));
        }
        
        System.out.println();
        System.out.println();
                            
        WeightedGraph graph1 = new WeightedGraph(matrix1);
        
        int[][] finished1 = graph1.prim();
        
        System.out.println("Finished Matrix:");
        for(int[] arr: finished1){
            System.out.println(Arrays.toString(arr));
        }
        
        end = System.currentTimeMillis();
        
        System.out.println("Constructing and running Prim's on a graph with 4 nodes took " + (end-start) + " ms");
        System.out.println();
        System.out.println();
    }
}