package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import CSVUtils.Reader;
import CSVUtils.Writer;
import Edge.Edge;

public class ProcessCSV {

	// campo de la clase
	private Map<String, Integer> movieMap;
	private Map<Edge, Integer> edgeMap;
	private Reader reader;
	private Writer nodeWriter;
	private Writer edgeWriter;

	/**
	 * Metodo constructor
	 * 
	 * @param filePath
	 *            El parametro filePath es la ruta del archivo de entrada
	 */
	public ProcessCSV(String filePath) {
		movieMap = new TreeMap<String, Integer>();
		edgeMap = new TreeMap<Edge, Integer>();
		reader = new Reader(filePath);
		nodeWriter = new Writer("node.csv");
		edgeWriter = new Writer("edge.csv");
	}

	public static void main(String[] args) {
		ProcessCSV pcsv = new ProcessCSV("actorMovies.csv");
		pcsv.process();
		pcsv.writeEdge();
	}

	/**
	 * El metodo que lee los datos del archivo de entrada y guarda las
	 * informaciones procesadas al archivo de salida (node.csv y edge.csv)
	 */
	private void process() {

		// contador de la pelicula
		int count = 0;
		String movies[];

		// leer el encabezado del archivo de entrada
		String line = this.reader.readLine();

		// guardar el encabezado del archivo de nodos
		List<String> saveLine = new ArrayList<String>();
		saveLine.add("ID");
		saveLine.add("Label");
		this.nodeWriter.saveLine(saveLine);

		// guardar el encabezado del archivo de aristas
		saveLine.clear();
		saveLine.add("Source");
		saveLine.add("Target");
		saveLine.add("Type");
		saveLine.add("weight");
		this.edgeWriter.saveLine(saveLine);

		// leer primera linea
		line = this.reader.readLine();
		while (line != null) {
			movies = line.split("\\|");

			// guardamos todas las peliculas en movies

			// recorremos la lista de las peliculas
			for (int i = 0; i < movies.length; i++) {

				// si no estaba antes, metemos al arbol con contador,
				// y guardamos en la salida (nodo)
				if (!movieMap.containsKey(movies[i])) {
					movieMap.put(movies[i], count);
					saveLine.clear();
					saveLine.add(String.valueOf(count));
					saveLine.add(movies[i]);
					this.nodeWriter.saveLine(saveLine);
					count++;
				}

				// recorremos siguiente pelicula
				for (int j = i + 1; j < movies.length; j++) {

					// si no estaba antes, metemos al arbol con contador,
					// y guardamos en la salida (nodo)
					if (!movieMap.containsKey(movies[j])) {
						movieMap.put(movies[j], count);
						saveLine.clear();
						saveLine.add(String.valueOf(count));
						saveLine.add(movies[j]);
						this.nodeWriter.saveLine(saveLine);
						count++;
					}

					// guardamos la arista de los dos peliculas en la salida de
					// aristas
					Edge edge = new Edge(movieMap.get(movies[i]),
							movieMap.get(movies[j]));
					if (!edgeMap.containsKey(edge)) {
						edgeMap.put(edge, 0);
					} else {
						edgeMap.replace(edge, edgeMap.get(edge) + 1);
					}

				}
			}

			// leer siguiente linea
			line = this.reader.readLine();
		}

		// cierramos los archivos
		this.reader.close();
		this.nodeWriter.close();
	}

	private void writeEdge() {
		List<String> saveLine = new ArrayList<String>();
		int weight = 0;
		Iterator<Entry<Edge,Integer>> entries = edgeMap.entrySet().iterator();
		Entry<Edge, Integer> entry;
		while(entries.hasNext()){
			entry = entries.next();
			saveLine.clear();
			saveLine.add(entry.getKey().toString());
			weight = entry.getValue() + 1;
			saveLine.add(String.valueOf(weight));
			this.edgeWriter.saveLine(saveLine);
		}
		this.edgeWriter.close();
	}
}
