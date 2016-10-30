package CSVUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class Writer {

	private FileOutputStream fos;
	private OutputStreamWriter osw;
	private BufferedWriter bw;

	/**
	 * Metodo constructora
	 * 
	 * @param fileName
	 *            El parametro fileName es path del archivo de salida
	 */
	public Writer(String fileName) {
		try {
			fos = new FileOutputStream(fileName);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error de la entrada/salida");
		}
	}

	/**
	 * El metodo que guarda una linea en archivo de salida
	 * 
	 * @param strSaveLine
	 *            El parametro strSaveLine es la lista de los elementos que
	 *            guarda
	 */
	public void saveLine(List<String> strSaveLine) {
		try {
			String line = strSaveLine.get(0);
			for (int i = 1; i < strSaveLine.size(); i++) {
				line += ";" + strSaveLine.get(i);
			}
			bw.write(line + System.getProperty("line.separator"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en escribir en archivo");
		}
	}

	/**
	 * El metodo que cierra el archivo de salida
	 */
	public void close() {
		try {
			bw.close();
			osw.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en cerrar el archivo de salida");
		}
	}
}
