package manager.file;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bo.graph.Edge;
import bo.graph.Vertex;
import bo.graph.heuristic.Heuristic;
import col.file.ColLoadFile;

public class UCLoadFile {
	
	private File file;
	
	public UCLoadFile(File file){
		this.file = file;
	}
	
	public UCLoadFile(){
		this.file = null;
	}
	
	public File getFile() {
		return file;
	}
	
	public File loadFile(){
		File file = null;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
			JFileChooser fileChooser = new JFileChooser();
			
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
			
			this.file = file;
			return file;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		return file;
	}

	public Vertex getInitialVertex() {
		ColLoadFile colLoadFile = new ColLoadFile(this.getFile());
		
		Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(colLoadFile.readLine());
		colLoadFile.closeBuffer();
		
		while(m.find()) {
			Vertex vertex = new Vertex(m.group(1));
			return vertex;  
		}
		
		return null;
	}
	
	public Vertex getFinalVertex() {
		ColLoadFile colLoadFile = new ColLoadFile(this.getFile());
		
		colLoadFile.skipLine(1);
		Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(colLoadFile.readLine());
		colLoadFile.closeBuffer();
		
		while(m.find()) {
			Vertex vertex = new Vertex(m.group(1));
			return vertex;  
		}
		
		return null;
	}
	
	public int getNumberOfEdges() {
		ColLoadFile colLoadFile = new ColLoadFile(this.getFile());
		
		String content = "";
		int edgeCount = 0;
		
		while(content != null && !content.contains("caminho")){
			content = colLoadFile.readLine();
		}
		
		while(content != null && content.contains("caminho")){
			content = colLoadFile.readLine();
			edgeCount++;
		}
		
		colLoadFile.closeBuffer();
		return edgeCount;
	}
	
	public Edge getEdge(int n) {
		ColLoadFile colLoadFile = new ColLoadFile(this.getFile());
		
		String content = "";
		int edgeCount = 0;
		
		while(content != null && !content.contains("caminho")){
			content = colLoadFile.readLine();
		}
		
		while(content != null && content.contains("caminho")){
			if(n == edgeCount){
				colLoadFile.closeBuffer();
				
				Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(content);
				
				String edgeComponents = "";
				while(m.find()) {
					edgeComponents = m.group(1);
				}
				
				String components[] = edgeComponents.trim().split(",");
				Vertex vertex0 = new Vertex(components[0].trim());
				Vertex vertex1 = new Vertex(components[1].trim());
				
				Edge edge = new Edge(vertex0, vertex1, Float.parseFloat(components[2].trim()));
				
				return edge;
			}
			else{
				content = colLoadFile.readLine();
				edgeCount++;
			}
		}
		
		return null;
	}
	
	public int getNumberOfHeuristics() {
		ColLoadFile colLoadFile = new ColLoadFile(this.getFile());
		
		String content = "";
		int heuristicCount = 0;
		
		while(content != null && !content.contains("h(")){
			content = colLoadFile.readLine();
		}
		
		while(content != null && content.contains("h(")){
			content = colLoadFile.readLine();
			heuristicCount++;
		}
		
		colLoadFile.closeBuffer();
		return heuristicCount;
	}
	
	public Heuristic getHeuristic(int n) {
		ColLoadFile colLoadFile = new ColLoadFile(this.getFile());
		
		String content = "";
		int heuristicCount = 0;
		
		while(content != null && !content.contains("h(")){
			content = colLoadFile.readLine();
		}
		
		while(content != null && content.contains("h(")){
			if(n == heuristicCount){
				colLoadFile.closeBuffer();
				
				Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(content);
				
				String heuristicComponents = "";
				while(m.find()) {
					heuristicComponents = m.group(1);
				}
				
				String components[] = heuristicComponents.trim().split(",");
				Vertex vertex0 = new Vertex(components[0].trim());
				Vertex vertex1 = new Vertex(components[1].trim());
				
				Heuristic heuristic = new Heuristic(vertex0, vertex1, Float.parseFloat(components[2].trim()));
				
				return heuristic;
			}
			else{
				content = colLoadFile.readLine();
				heuristicCount++;
			}
		}
		
		return null;
	}
	
}
