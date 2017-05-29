package manager.algorithms;

import java.io.File;
import java.util.ArrayList;

import bo.graph.Edge;
import bo.graph.Vertex;
import bo.graph.heuristic.Heuristic;
import bo.graph.heuristic.VertexPath;
import manager.file.UCLoadFile;

public class AStar {
	public static void heuristicAStar(Vertex initialVertex, Vertex finalVertex, File file) throws InterruptedException{
		
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
