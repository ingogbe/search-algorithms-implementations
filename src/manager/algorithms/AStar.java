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
		int nMaxVertexInMemory = 0;
		
		//Lista de caminhos
		ArrayList<VertexPath> vertexPaths = new ArrayList<VertexPath>();
		
		//Caminho
		VertexPath initialVP = new VertexPath();
		
		/*
		 * Inicializando caminho inicial
		 * Buscando h(n) = heuristica, distância estimada de N ao no final
		 * Atribuindo g(n) = distancia de N ao nó inicial
		 */
		for(int h = 0; h < nHeuristics; h++){
			Heuristic heuristic = ucLoadFile.getHeuristic(h);
			if(heuristic.getV0().compare(initialVertex)){
				initialVP.addVertex(initialVertex, 0, heuristic.getValue());
				break;
			}
		}
		
		vertexPaths.add(initialVP);
		//Toda vez que faz um add checa quantidade de nós na memória
		nMaxVertexInMemory = checkVertexInMemory(vertexPaths, nMaxVertexInMemory);
		
		int completedVPs = 0;
		VertexPath lesserWeigthVP = null;
		
		while(completedVPs != vertexPaths.size()){
			nInteractions++;
			//Zera contador de caminhos completos ao inicio de cada loop
			completedVPs = 0;
			
			//Busca o caminho com menor peso
			lesserWeigthVP = null;
			for(VertexPath vp :vertexPaths){
				
				//Imprime caminhos em aberto
				System.out.println(vp);
				
				//Verifica qual o caminho de menor peso (se não for um caminho completo, do inicio ao fim)
				if(!vp.isEnd()){
					//Se não houver nenhum menor caminho ainda, ele seleciona o primeiro, senão procura qual o menor
					if(lesserWeigthVP == null || vp.getWeigth() < lesserWeigthVP.getWeigth()){
						lesserWeigthVP = vp;
					}
				}
				//Contador para verificar a quantidade de caminhos completos
				else{
					completedVPs++;
				}
			}
			
			//Se o ultimo vertice no menor caminhos atual for igual ao vertice final, encerra a busca
			if(lesserWeigthVP.getLastVertex().compare(finalVertex)){
				System.out.println("----------");
				lesserWeigthVP.setEnd(true);
				break;
			}
			
			if(lesserWeigthVP != null){
				//Remove o caminho de menor peso dos caminhos possíveis pois vamos trabalhar com ele agora
				vertexPaths.remove(lesserWeigthVP);
				
				int neighbors = 0;
				for(int i = 0; i < nEdges; i++){
					Edge e = ucLoadFile.getEdge(i);
					
					/*
					 * Pega os caminhos do ultimo vertice do caminho de menor peso
					 * 
					 * e.getV0() é o verticel inicial da aresta comparada, portanto sei atraves do compare()
					 * que essa aresta é iniciada no ultimo vertice do meu melhor caminho atual
					 */
					if(lesserWeigthVP.getLastVertex().compare(e.getV0())){
						
						//Cria uma copia do caminho de menor peso atual, que sera usada para gerar um novo caminho
						//Obs.: Mantem a original por causa do IF acima
						VertexPath newVP = lesserWeigthVP.copy();
						
						for(int h = 0; h < nHeuristics; h++){
							Heuristic heuristic = ucLoadFile.getHeuristic(h);
							
							/*
							 * Compara as heuristicas do arquivo para achar a que possue o vertice inicial igual ao destino
							 * do vertice da fronteira do caminho de menor peso atual que esta sendo checado agora
							 * 
							 * Obs.: O heuristic.getV1() sempre será o destino final
							 */
							if(heuristic.getV0().compare(e.getV1())){
								
								//Adiciona esse vertice com seu valor de aresta e o respectivo valor da heuristica
								newVP.addVertex(e.getV1(), e.getValue(), heuristic.getValue());
								break;
							}
						}
						
						//Adiciona esse novo caminho aos caminhos possiveis
						vertexPaths.add(newVP);
						nMaxVertexInMemory = checkVertexInMemory(vertexPaths, nMaxVertexInMemory);
						
						neighbors++;
					}
				}
				
				//Se não houver vizinhos, este caminho não tem mais para onde percorrer e chega ao fim
				if(neighbors == 0){
					lesserWeigthVP.setEnd(true);
					//Adiciona novamente a lista de caminhos
					vertexPaths.add(lesserWeigthVP);
					nMaxVertexInMemory = checkVertexInMemory(vertexPaths, nMaxVertexInMemory);
				}
			}
			
		}
		
		System.out.println("RESULT:");
		System.out.println(lesserWeigthVP);
		System.out.println(lesserWeigthVP.getEvaluationString());
		System.out.println("Number Of Interactions = " + nInteractions);
		System.out.println("Number of Max Vertex In Memory = " + nMaxVertexInMemory);
		System.out.println("================================\n");
	}
	
	
	public static int checkVertexInMemory(ArrayList<VertexPath> array, int nMaxVertexInMemory) {
		
		int n = 0;
		for(VertexPath a: array) {
			n = n + a.getVertexList().size();
		}
		
		if(nMaxVertexInMemory < n) {
			nMaxVertexInMemory = n;
		}
		
		return nMaxVertexInMemory;
	}
}
