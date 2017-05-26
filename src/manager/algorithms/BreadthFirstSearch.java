package manager.algorithms;

import java.io.File;
import java.util.ArrayList;

import bo.graph.Edge;
import bo.graph.Vertex;
import bo.graph.heuristic.Heuristic;
import bo.graph.heuristic.VertexPath;
import manager.file.UCLoadFile;

public class BreadthFirstSearch {
	
	public static void normalBreadthFirstSearch(Vertex initialVertex, Vertex finalVertex, File file){
		
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
	
	public static void heuristicBreadthFirstSearch(Vertex initialVertex, Vertex finalVertex, File file) throws InterruptedException{
		
		UCLoadFile ucLoadFile = new UCLoadFile(file);
		int nEdges = ucLoadFile.getNumberOfEdges();
		int nHeuristics = ucLoadFile.getNumberOfHeuristics();
		int nInteractions = 0;
		
		ArrayList<VertexPath> vertexPaths = new ArrayList<VertexPath>();
		VertexPath initialVP = new VertexPath();
		
		for(int h = 0; h < nHeuristics; h++){
			Heuristic heuristic = ucLoadFile.getHeuristic(h);
			if(heuristic.getV0().compare(initialVertex)){
				initialVP.addVertex(initialVertex, 0, heuristic.getValue());
				break;
			}
		}
		
		vertexPaths.add(initialVP);
		int itsOver = 0;
		VertexPath lesserWeigthVP = null;
		
		//while(itsOver != vertexPaths.size()){
		while(itsOver != vertexPaths.size()){
			nInteractions++;
			itsOver = 0;
			ArrayList<VertexPath> auxVertexPaths = new ArrayList<VertexPath>();
			
			lesserWeigthVP = null;
			for(VertexPath vp :vertexPaths){
				
				System.out.println(vp);
				
				if(!vp.isEnd()){
					if(lesserWeigthVP == null || vp.getWeigth() < lesserWeigthVP.getWeigth()){
						lesserWeigthVP = vp;
					}
				}
				else{
					itsOver++;
				}
				
				auxVertexPaths.add(vp);
			}
			
			if(lesserWeigthVP != null){
				auxVertexPaths.remove(lesserWeigthVP);
				
				int neighbors = 0;
				for(int i = 0; i < nEdges; i++){
					Edge e = ucLoadFile.getEdge(i);
					
					if(lesserWeigthVP.getLastVertex().compare(e.getV0())){
						VertexPath newVP = lesserWeigthVP.copy();
						
						for(int h = 0; h < nHeuristics; h++){
							Heuristic heuristic = ucLoadFile.getHeuristic(h);
							if(heuristic.getV0().compare(e.getV1())){
								newVP.addVertex(e.getV1(), e.getValue(), heuristic.getValue());
								break;
							}
						}
						
						auxVertexPaths.add(newVP);
						neighbors++;
					}
				}
				
				if(neighbors == 0){
					lesserWeigthVP.setEnd(true);
					auxVertexPaths.add(lesserWeigthVP);
				}
			}
			
			System.out.println("----------");
			vertexPaths = auxVertexPaths;
			
			if(lesserWeigthVP.getLastVertex().compare(finalVertex)){
				break;
			}
			
		}
		
		System.out.println("RESULT:");
		System.out.println(lesserWeigthVP);
		System.out.println("Number Of Interactions = " + nInteractions);
		System.out.println("================================\n");
	}
}
