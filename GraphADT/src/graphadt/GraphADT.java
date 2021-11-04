/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadt;

/**
 *
 * @author Bmitr
 */
public class GraphADT {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        long start;
        long end;
        
        //Undirected 10 node
        start = System.currentTimeMillis();
        Graph undirected10 = new Graph(10,  false);
        System.out.println(undirected10.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print an undirected graph with 10 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        //Directed 10 node
        start = System.currentTimeMillis();
        Graph directed10 = new Graph(10,  true);
        System.out.println(directed10.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print a directed graph with 10 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        
        //Undirected 100 node
        start = System.currentTimeMillis();
        Graph undirected100 = new Graph(100,  false);
        System.out.println(undirected100.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print an undirected graph with 100 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        //Directed 100 node
        start = System.currentTimeMillis();
        Graph directed100 = new Graph(100,  true);
        System.out.println(directed100.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print a directed graph with 100 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        //Undireted 1000 node
        start = System.currentTimeMillis();
        Graph undirected1000 = new Graph(1000,  false);
        System.out.println(undirected1000.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print an undirected graph with 1000 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        //Directed 1000 node
        start = System.currentTimeMillis();
        Graph directed1000 = new Graph(1000,  true);
        System.out.println(directed1000.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print a directed graph with 1000 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        //Undirected 10000 node
        start = System.currentTimeMillis();
        Graph undirected10000 = new Graph(10000,  false);
        System.out.println(undirected10000.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print an undirected graph with 10000 nodes : " + (end- start) + " ms");
        System.out.println();
        System.out.println();
        
        //Directed 10000
        start = System.currentTimeMillis();
        Graph directed10000 = new Graph(10000,  true);
        System.out.println(directed10000.adjacencyListToStringBuilder());
        end = System.currentTimeMillis();
        System.out.println("Time to generate and print a directed graph with 1000 nodes : " + (end- start) + " ms");

        
    }
}
