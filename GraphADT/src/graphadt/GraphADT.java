/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphadt;

import java.io.IOException;

/**
 *
 * @author Bmitr
 */
public class GraphADT {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Graph graph = new Graph(10000, false);
        
        //System.out.print(graph.verifyUndirected());
        
        
        /*
        //Saves graph as "readable" file
        try {
            graph.saveToFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphADT.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        System.out.println("BINARY TEST WITH SERIALIZER CLASS:");
        //Attempts to serialize a graph to a given txt file
        try{
            //Serializes a graph to a given txt file
            Serializer.serializeToBinary("data/undirectedSize10000.bin", graph);
            
            //Attempts to deserialize a graph from a given txt file
            try{
                // Deserializes a graph from the data.txt file
                Graph replicaGraph = (Graph) Serializer.deserializeFromBinary("data/undirectedSize10000.bin");

                System.out.println("The serialization worked... " + replicaGraph.equals(graph));
            }catch(IOException | ClassNotFoundException ex){
                System.out.println("Error in deserialization");
            }
        }catch (IOException ex){
            System.out.println("Error in serialization");
        }
        
    }
}
