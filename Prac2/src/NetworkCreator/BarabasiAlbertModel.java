package NetworkCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import Edge.Edge;

public class BarabasiAlbertModel extends Network {

	// Campos de la clase
	private int m;
	private Map<Integer, List<Edge>> nodeMap;
	private Map<Edge, Integer> edgeMap;
	private Map<Integer, Double> probabilityMap;
	
	/**
	 * Constructora
	 * @param N El numero de nodos de la red
	 * @param LinkNumber El numero de enlaces que crear por cada paso
	 */
	public BarabasiAlbertModel(int N, int LinkNumber) {
		super(N);
		this.m = LinkNumber;
		this.nodeMap = new TreeMap<Integer, List<Edge>>();
		this.edgeMap = new TreeMap<Edge, Integer>();
		this.probabilityMap = new TreeMap<Integer, Double>();
	}

	@Override
	public void createRed() {
		// TODO Auto-generated method stub
		
		int mo = m + 1;						// configuracion inicial de los mo nodos, supondremos mo = m + 1 en practica
		int t = super.numNodes - mo;		// t = N(t) - mo
		double nodeDegreeAccu = mo * m;		// acumulador de grados de nodos
		
		// distribucion inicial, todos los nodos tienen al menos un enlace
		for(int i = 0; i < mo; i++) {
			for(int j = i + 1; j < mo; j++) {
				Edge edge1 = new Edge(i, j);
				Edge edge2 = new Edge(j, i);
				edgeMap.put(edge1, 1);
				getNodeEdges(i).add(edge1);
				getNodeEdges(j).add(edge2);
			}
			probabilityMap.put(i, m / nodeDegreeAccu);
		}
				
		Random probability = new Random();	// el numero aleatorio
		
		// para construir una red de tamano N, necesitamos correr t pasos
		// y anadimos un nodo en cada paso con m enlaces
		for(int steps = 0; steps < t; steps++) {
			int numNodeToInsert = steps + mo;	// el numero de nuevo nodo que va a insertar
			
			nodeDegreeAccu += m * 2;
			
			int p = 0;
			
			while(p < m) {
				for(int i = 0; i < numNodeToInsert && p < m; i++) {
					if(probability.nextDouble() < probabilityMap.get(i)){
						Edge edge1 = new Edge(i, numNodeToInsert);
						Edge edge2 = new Edge(numNodeToInsert, i);
						getNodeEdges(i).add(edge1);
						getNodeEdges(numNodeToInsert).add(edge2);
						p++;
						edgeMap.put(edge1, 1);
						probabilityMap.put(i, 0.00);
					}
				}
			}
			
			// actualizamos los probabilidades de todos los nodos
			for(int i = 0; i <= numNodeToInsert; i++) {
				probabilityMap.put(i, nodeMap.get(i).size() / nodeDegreeAccu);
			}
		}
		
	}
	
	/**
	 * Devolver la lista de enlaces del nodo i,
	 * Si no existe el nodo, lo crea
	 * @param i El indice del nodo
	 * @return La lista de enlaces
	 */
	private List<Edge> getNodeEdges(int i){
		List<Edge> listEdges;
		if(!nodeMap.containsKey(i)){
			listEdges = new ArrayList<Edge>();
			nodeMap.put(i, listEdges);
		} else {
			listEdges = nodeMap.get(i);
		}
		return listEdges;
	}
	
	/**
	 * Devolver la mapa de todos los enlaces
	 */
	public Map<Edge, Integer> getEdgeMap(){
		return this.edgeMap;
	}
	
	/**
	 * Devolver el tamono de la red(numero de nodos)
	 */
	public Object getNodeMap() {
		return this.nodeMap;
	}
}
