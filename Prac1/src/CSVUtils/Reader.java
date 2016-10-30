package CSVUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Reader {
	
	// Campos de la clase
	private FileInputStream fis;  
    private InputStreamReader isw;  
	private BufferedReader br;
	
	/**
	 * Metodo constructor
	 * @param filePath El parametro filePath define la ruta del archivo de entrada
	 */
	public Reader(String filePath) {
		try {
			fis = new FileInputStream(filePath);
			isw = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isw);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error! Unsupport Encode");
		}
	}
	
	/**
	 * El metodo que lea una linea del archivo de entrada
	 * @return str es la linea leida
	 */
	public String readLine(){
		String str = "";
		String movie[] = null;
		try {
			str = br.readLine();
			if(str == null)
				return null;
			movie = str.split(";");
		} catch (IOException e) {
			System.out.println("Read error");
		}
		return movie[1];
	}
	
	/**
	 * El metodo que cierra el archivo de entrada
	 */
	public void close(){
		try {
			br.close();
			isw.close();
			fis.close();
		} catch (IOException e) {
			System.out.println("Close error");
		}
	}
}
