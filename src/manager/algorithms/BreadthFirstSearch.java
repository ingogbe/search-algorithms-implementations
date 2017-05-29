package manager.algorithms;

import java.io.File;
import java.util.ArrayList;

import bo.graph.Edge;
import bo.graph.Vertex;
import manager.file.UCLoadFile;

public class BreadthFirstSearch {
	
	public static void breadthFirstSearch(Vertex initialVertex, Vertex finalVertex, File file){
		
		UCLoadFile ucLoadFile = new UCLoadFile(file);
		int nEdges = ucLoadFile.getNumberOfEdges();
		int nInteractions = 0;
		int nInteractionsUntilFinalVertex = 0;
		
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.add(initialVertex);
		
		Vertex currentVertex = initialVertex;

		while(!vertexList.isEmpty()){
			nInteractions++;
			System.out.println("Current vertex = " + currentVertex);
			
			int i = 0;
			boolean print = true;
			
			while((i+1) <= nEdges){
				Edge e = ucLoadFile.getEdge(i);
				
				if(currentVertex.compare(e.getV0())){
					if(print){
						System.out.print("Neighbors = ");
						print = false;
					}
					
					vertexList.add(e.getV1());
					System.out.print(e.getV1() + " ");
				}
				
				i++;
			}
			
			if(!print){
				System.out.println("");
			}
			
			vertexList.remove(currentVertex);
			if(!vertexList.isEmpty())
				currentVertex = vertexList.get(0);
			
			System.out.print("List = ");
			for(Vertex v: vertexList){
				System.out.print(v.getName() + " ");
			}
			System.out.println("----------\n");

			
			if(!currentVertex.compare(finalVertex)){
				nInteractionsUntilFinalVertex++;
			}
		}
		
		System.out.println("RESULT:");
		System.out.println("Number Of Interactions = " + nInteractions);
		System.out.println("Number Of Interactions Until End = " + nInteractionsUntilFinalVertex);
		System.out.println("================================\n");
		
	}
}
