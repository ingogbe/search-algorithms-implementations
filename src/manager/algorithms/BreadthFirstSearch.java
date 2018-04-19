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
		int nMaxVertexInMemory = 0;
		
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.add(initialVertex);
		nMaxVertexInMemory = checkVertexInMemory(vertexList, nMaxVertexInMemory);
		
		Vertex currentVertex = initialVertex;

		//Enquanto a lista de vertices a se expandir não for vazia
		while(!vertexList.isEmpty()){
			nInteractions++;
			System.out.println("Current vertex = " + currentVertex);
			
			int i = 0;
			boolean print = true;
			
			while((i+1) <= nEdges){
				Edge e = ucLoadFile.getEdge(i);
				
				//Pega as arestas as quais o vertice inicial é o vertice atual
				if(currentVertex.compare(e.getV0())){
					if(print){
						System.out.print("Neighbors = ");
						print = false;
					}
					
					//Adiciona os vertices destino destas arestas a fronteira
					vertexList.add(e.getV1());
					nMaxVertexInMemory = checkVertexInMemory(vertexList, nMaxVertexInMemory);
					//Imprime vizinhos do vertice atual
					System.out.print(e.getV1() + " ");
				}
				
				i++;
			}
			
			if(!print){
				System.out.println("");
			}
			
			//Remove o vertice atual da fronteira
			vertexList.remove(currentVertex);
			
			//Se a lista não estiver vazia pega o primeiro nó para expandi-lo
			if(!vertexList.isEmpty())
				currentVertex = vertexList.get(0);
			
			//Imprime lista atual
			System.out.print("List = ");
			for(Vertex v: vertexList){
				System.out.print(v.getName() + " ");
				
			}
			System.out.println("----------\n");
			
			//Para ao encontrar a resposta
			if(currentVertex.compare(finalVertex)){
				//break;
			}
			else {
				//Contador de iterações até encontrar a resposta
				nInteractionsUntilFinalVertex++;
			}
		}
		
		System.out.println("RESULT:");
		System.out.println("Number Of Interactions = " + nInteractions);
		System.out.println("Number Of Interactions Until End = " + nInteractionsUntilFinalVertex);
		System.out.println("Number of Max Vertex In Memory = " + nMaxVertexInMemory);
		System.out.println("================================\n");
		
	}
	
	public static int checkVertexInMemory(ArrayList<Vertex> array, int nMaxVertexInMemory) {
		if(nMaxVertexInMemory < array.size()) {
			nMaxVertexInMemory = array.size();
		}
		
		return nMaxVertexInMemory;
	}
}
