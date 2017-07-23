package Edge;

public class Edge implements Comparable<Object>{
	
	// Campos de la clase
	private int from;
	private int to;
	private String type;
	
	/**
	 * Constructora
	 * @param v El nodo from
	 * @param w El nodo to
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
		if(Integer.compare(this.from, ((Edge) o).from) == 0) {
			return Integer.compare(this.to, ((Edge) o).to);
		} else {
			return Integer.compare(this.from, ((Edge) o).from);
		}
	}
}
