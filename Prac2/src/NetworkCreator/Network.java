package NetworkCreator;

import java.util.Map;

import Edge.Edge;

public abstract class Network {
	
	protected int numNodes;		// numero de nodos de la red
	
	/**
	 * Constructora
	 * @param N	El numero de nodos que quiere crear
	 */
	public Network(int N){
		this.numNodes = N;
	}
	
	/**
	 * Crear la red
	 */
	abstract public void createRed();
	
	abstract public Map<Edge, Integer> getEdgeMap();
	
	abstract public Object getNodeMap();
	
	public int getNodeNumber(){
		return numNodes;
	}
}
