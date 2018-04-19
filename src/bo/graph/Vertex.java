package bo.graph;

public class Vertex {
	private String name;
	
	public Vertex(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public boolean compare(Vertex v){
		if(this.getName().equals(v.getName())){
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
