package col.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ColLoadFile {
	
	private File file;
	private BufferedReader contentBuffer;
	
	public ColLoadFile(File file){
		super();
		
		this.file = file;
		
		try {
			contentBuffer = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}

	public BufferedReader getContentBuffer() {
		return contentBuffer;
	}

	public String readLine(){
		try {
			String content = getContentBuffer().readLine();
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			return "I/O Exception";
		}
	}
	
	public void skipLine(int numberOfLines){
		try {
			for(int i = 0; i < numberOfLines; i++)
				getContentBuffer().readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeBuffer(){
		try {
			getContentBuffer().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
