package Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import CSVUtils.Writer;
import Edge.Edge;
import NetworkCreator.BarabasiAlbertModel;
import NetworkCreator.Network;
import NetworkCreator.RandomNetwork;

public class Creator {
	
	private Network nc;
	private Writer edgeWriter;
	
	private int N[] = {500, 1000, 5000};
	private int m[] = {3, 4};
	private String fase[] = {"subcritica", "critica", "supercritica", "conectada"};
	
	public Creator() {
		Barabasi();
		RandomNetwork();
		MovieNetwork();
	}
	
	@SuppressWarnings("unchecked")
	private void Barabasi() {
		
		for(int i = 0; i < N.length; i++){
			for(int j = 0; j < m.length; j++){
				nc = new BarabasiAlbertModel(N[i], m[j]);
				System.out.println("Crear red con modelo de Barabasi Albert con " + N[i] + " nodos y "
						+ m[j] + " enlaces en cada paso");
				
				nc.createRed();
				
				String fileName = "Barabasi_Albert_" + N[i] + "_" + m[j] + ".csv";
				String header = "Source;Target;Type;Weight";
				saveToCsv(fileName,header);
				
				TreeMap<Integer,List<Edge>> nodeMap = (TreeMap<Integer, List<Edge>>)nc.getNodeMap();
				System.out.println("numero de nodo creado " + nodeMap.size());
				System.out.println("numero de enlcaces creado " + nc.getEdgeMap().size());
				System.out.print(System.getProperty("line.separator"));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void RandomNetwork() {
		
		for(int i = 0; i < N.length; i++){
			for(int j = 0; j < fase.length; j++){
				double p = 0;
				switch(fase[j]){
				case "subcritica":
					p = 1.0 / (N[i] * 1.5);
					break;
				case "critica":
					p = 1.0 / N[i];
					break;
				case "supercritica":
					p = 1.0 / (N[i] * 0.5);
					break;
				case "conectada":
					p = Math.log(N[i]) / (N[i] * 0.9);
					break;
					default:
						break;
				}
				nc = new RandomNetwork(N[i], p);
				System.out.println("Crear red aleatoria " + fase[j] + " con " + N[i] + " nodos y p=" + p);
				
				nc.createRed();
				
				String fileName = "Red_Aleatoria_" + N[i] + "_" + fase[j] + ".csv";
				String header = "Source;Target;Type;Weight";
				saveToCsv(fileName,header);

				TreeMap<Integer,List<Edge>> nodeMap = (TreeMap<Integer, List<Edge>>)nc.getNodeMap();
				System.out.println("numero de nodo creado " + nodeMap.size());
				System.out.println("numero de enlcaces creado " + nc.getEdgeMap().size());
				System.out.print(System.getProperty("line.separator"));
			}
			
			Writer nodeWriter = new Writer("Red_Aleatoria_" + N[i] + "_Nodos.csv");
			TreeMap<Integer, Integer> nodeMap = (TreeMap<Integer, Integer>) nc.getNodeMap();
			List<String> saveLine = new ArrayList<String>();
			saveLine.add("Id;Label");
			nodeWriter.saveLine(saveLine);
			for(Map.Entry<Integer, Integer> entry: nodeMap.entrySet()) {
				saveLine.clear();
				saveLine.add(String.valueOf(entry.getKey()));
				saveLine.add(String.valueOf(entry.getValue()));
				nodeWriter.saveLine(saveLine);
			}
			nodeWriter.close();
		}
	}
	@SuppressWarnings("unchecked")
	private void MovieNetwork() {
		nc = new BarabasiAlbertModel(1893, 10);
		System.out.println("Crear red de peliculas con modelo de Barabasi Albert con 1893 nodos"
				+ " y 10 enlaces en cada paso");
		
		nc.createRed();
		
		String fileName = "Peliculas_1893_10.csv";
		String header = "Source;Target;Type;Weight";
		saveToCsv(fileName,header);
		
		TreeMap<Integer,List<Edge>> nodeMap = (TreeMap<Integer, List<Edge>>)nc.getNodeMap();
		System.out.println("numero de nodo creado " + nodeMap.size());
		System.out.println("numero de enlcaces creado " + nc.getEdgeMap().size());
		System.out.print(System.getProperty("line.separator"));
	}
	
	private void saveToCsv(String fileName, String header) {
		edgeWriter = new Writer(fileName);
		List<String> saveLine = new ArrayList<String>();
		saveLine.add(header);
		edgeWriter.saveLine(saveLine);
		for(Map.Entry<Edge, Integer> entry : nc.getEdgeMap().entrySet()){
			saveLine = new ArrayList<String>();
			saveLine.add(entry.getKey().toString());
			int weight = entry.getValue();
			saveLine.add(String.valueOf(weight));
			edgeWriter.saveLine(saveLine);
		}
		edgeWriter.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Creator();
	}

}
