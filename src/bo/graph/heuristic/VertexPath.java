package bo.graph.heuristic;

import java.util.ArrayList;

import bo.graph.Vertex;

public class VertexPath {

	private ArrayList<Vertex> vertexList;
	private boolean end;
	private float edgeValues;
	private float heuristicValue;

	public float getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(float heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	public float getEdgeValues() {
		return edgeValues;
	}

	public void setEdgeValues(float edgeValues) {
		this.edgeValues = edgeValues;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public VertexPath() {
		super();
		this.end = true;
		this.vertexList = new ArrayList<Vertex>();
		this.edgeValues = 0;
		this.heuristicValue = 0;
	}

	public ArrayList<Vertex> getVertexList() {
		return this.vertexList;
	}
	
	public Vertex getVertex(int i){
		return vertexList.get(i);
	}
	
	public void addVertex(Vertex v, float edgeValue, float heuristicValue){
		this.vertexList.add(v);
		this.end = false;
		this.edgeValues += edgeValue;
		this.heuristicValue = heuristicValue;
	}
	
	private void addVertex(Vertex v){
		this.vertexList.add(v);
		this.end = false;
	}
	
	public void removeVertex(Vertex v){
		this.vertexList.remove(v);
		
		if(this.vertexList.size() > 0)
			this.end = false;
		else
			this.end = true;
	}
	
	public void removeVertex(int i){
		this.vertexList.remove(i);
		
		if(this.vertexList.size() > 0)
			this.end = false;
		else
			this.end = true;
	}
	
	public void removeAllVertex(){
		this.vertexList.removeAll(this.vertexList);
		this.end = true;
	}
	
	public void setVertex(int i, Vertex v){
		this.vertexList.set(i, v);
	}
	
	public Vertex getLastVertex(){
		return this.vertexList.get(this.vertexList.size()-1);
	}
	
	//f(n) = g(n) + h(n)
	public float getWeigth(){
		return (this.getEdgeValues() + this.getHeuristicValue());
	}
	
	//Faz um cópia desse VertexPath
	public VertexPath copy(){
		VertexPath copy = new VertexPath();
		
		for(Vertex v :this.vertexList){
			Vertex vCopy = new Vertex(v.getName());
			copy.addVertex(vCopy);
		}
		
		copy.setEdgeValues(this.getEdgeValues());
		copy.setHeuristicValue(this.getHeuristicValue());
		copy.setEnd(this.isEnd());
		
		return copy;
		
	}
	
	public String getPathString(){
		String s = "";
		for(Vertex v : this.getVertexList()){
			s = s + v.getName() + " ";
		}
		
		return s;
	}
	
	public String getEvaluationString() {
		return "Evaluation Function = f(" + this.getLastVertex().getName() + ") = g(" + this.getEdgeValues() + ") + h(" + this.getHeuristicValue() + ") = " + (this.getEdgeValues() + this.getHeuristicValue());
	}

	@Override
	public String toString() {
		String s = "Path = ";
		for(int i = 0; i < this.getVertexList().size(); i++){
			if(i == this.getVertexList().size()-1){
				s = s + this.getVertexList().get(i).getName() + " ";
			}
			else{
				s = s + this.getVertexList().get(i).getName() + "->";
			}
		}
		
		if(!this.isEnd())
			s = s + " | f(" + this.getLastVertex().getName() + ") = g(" + this.getEdgeValues() + ") + h(" + this.getHeuristicValue() + ") = " + (this.getEdgeValues() + this.getHeuristicValue());
		
		s = s + " | End = " + this.isEnd();
		
		return s;
	}
	
	
	
}
