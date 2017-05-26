package bo.graph;

public class Edge {

	public static final int V0_TO_V1 = 0;
	public static final int V1_TO_V0 = 1;
	public static final int NO_ORIENTATION = 2;
	
	private Vertex v0;
	private Vertex v1;
	private float value;
	private int orientation;
	
	public Edge(Vertex v0, Vertex v1, float value, int orientation) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.value = value;
		this.orientation = orientation;
	}

	public Edge() {
		super();
		this.v0 = null;
		this.v1 = null;
		this.value = 0;
		this.orientation = NO_ORIENTATION;
	}

	public Edge(Vertex v0, Vertex v1) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.value = 0;
		this.orientation = NO_ORIENTATION;
	}

	public Edge(Vertex v0, Vertex v1, float value) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.value = value;
		this.orientation = NO_ORIENTATION;
	}
	
	

	public Edge(Vertex v0, Vertex v1, int orientation) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.value = 0;
		this.orientation = orientation;
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
	
	public int getOrientation() {
		return orientation;
	}
	
	public String getOrientationName() {
		if(this.getOrientation() == V0_TO_V1)
			return "V0 to V1";
		else if(this.getOrientation() == V1_TO_V0)
			return "V1 to V0";
		else
			return "No orientation";
	}
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	@Override
	public String toString() {
		return "Edge [v0 = " + v0.toString() + ", v1 = " + v1.toString() + ", value = " + value + ", orientation = " + this.getOrientationName() + "]";
	}
	
	public boolean compare(Edge e){
		
		if( this.getV0().getName().equals(e.getV0().getName()) 	&& 
			this.getV1().getName().equals(e.getV1().getName()) 	&& 
			this.getValue() == e.getValue() 					&& 
			this.getOrientation() == e.getOrientation()){
			
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
