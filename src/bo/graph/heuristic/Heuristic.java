package bo.graph.heuristic;

import bo.graph.Vertex;

public class Heuristic {
	private Vertex v0;
	private Vertex v1;
	private float value;
	
	public Heuristic(Vertex v0, Vertex v1, float value) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.value = value;
	}

	public Vertex getV0() {
		return v0;
	}

	public void setV0(Vertex v0) {
		this.v0 = v0;
	}

	public Vertex getV1() {
		return v1;
	}

	public void setV1(Vertex v1) {
		this.v1 = v1;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Heuristic [v0 = " + v0.toString() + ", v1 = " + v1.toString() + ", value = " + value + "]";
	}	
	
	
	
}
