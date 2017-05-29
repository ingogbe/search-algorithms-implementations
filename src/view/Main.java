package view;

import java.io.File;

import manager.algorithms.AStar;
import manager.file.UCLoadFile;

public class Main {

	public static void main(String[] x) throws InterruptedException{
		
		UCLoadFile ucLoadFile = new UCLoadFile();
		File file = ucLoadFile.loadFile();
		
		System.out.println("INITIAL VERTEX = " + ucLoadFile.getInitialVertex().toString());
		System.out.println("FINAL VERTEX   = " + ucLoadFile.getFinalVertex().toString());
		System.out.println("================================");
		
		//BreadthFirstSearch.normalBreadthFirstSearch(ucLoadFile.getInitialVertex(), ucLoadFile.getFinalVertex(), file);
		AStar.heuristicAStar(ucLoadFile.getInitialVertex(), ucLoadFile.getFinalVertex(), file);
		
	}
	
}
