package Edge;

public class Edge implements Comparable<Object>{
	
	// Campo de la clase
	private int from;
	private int to;
	private String type;
	
	/**
	 * Constructor de grafo no dirigido
	 * @param v El numero de vertices.
	 * @param w El numero de aristas
	 */
	public Edge(int v, int w){
		this.from = v;
		this.to = w;
		this.type = "undirected";
	}
	
	public String toString(){
		return this.from + ";" + this.to + ";" + this.type;
	}

	@Override
	public int compareTo(Object o) {
		if(Integer.compare(this.from, ((Edge) o).from) == 0 && Integer.compare(this.to, ((Edge) o).to) == 0){
			return 0;
		} else if(Integer.compare(this.from, ((Edge) o).from) == 0) {
			return Integer.compare(this.to, ((Edge) o).to);
		} else
			return Integer.compare(this.from, ((Edge) o).from);
	}
}
