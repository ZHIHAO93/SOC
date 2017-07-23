package NetworkCreator;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import Edge.Edge;

public class RandomNetwork extends Network{

	private double p;
	private Map<Integer, Integer> nodeMap;
	private Map<Edge, Integer> edgeMap;
	
	public RandomNetwork(int N, double p) {
		super(N);
		this.p = p;
		nodeMap = new TreeMap<Integer, Integer>();
		edgeMap = new TreeMap<Edge, Integer>();
		createNodes();
	}

	@Override
	public void createRed() {
		Random probability = new Random();	// el numero aleatorio
		
		// recorremos toda la red cogiendos pares de nodos
		// obtenemos un numero aleatorio
		// si es menor que la probabilidad, creamos un enlace
		for(int i = 0; i < numNodes; i++) {
			for(int j = i + 1; j < numNodes; j++) {
				if(probability.nextDouble() < p) {
					Edge edge = new Edge(i, j);
					edgeMap.put(edge, 1);
				}
			}
		}
	}
	
	private void createNodes() {
		for(int i = 0; i < numNodes; i++){
			nodeMap.put(i, i);
		}
	}

	@Override
	public Map<Edge, Integer> getEdgeMap() {
		// TODO Auto-generated method stub
		return edgeMap;
	}

	@Override
	public Object getNodeMap() {
		// TODO Auto-generated method stub
		return nodeMap;
	}

}
