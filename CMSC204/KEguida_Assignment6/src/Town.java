import java.util.ArrayList;

/**
 * 
 * @author Kevin Eguida
 *
 */
public class Town implements Comparable<Town>{

	String name;
	//List of adjacent towns.
	ArrayList<Town> edgeList;
	
	//Fields used for the shortestPath algorithm.
	int distanceTo=Integer.MAX_VALUE;
	Town by;
	boolean visited=false;
	
	/**
	 * Construtor that creates a Town object with the name specified
	 * @param name
	 */
	public Town(String name) 
	{
		this.name = name;
		edgeList = new ArrayList<Town>();
	}
	
	/**
	 * Copy constructor
	 * @param templateTown
	 */
	public Town(Town templateTown)
	{
		this.name = templateTown.getName();
		edgeList = new ArrayList<Town>(templateTown.edgeList);
	}
	
	/**
	 * compare to method
	 */
	@Override
	public int compareTo(Town o) {
		return this.name.compareTo(o.getName());
	}
	
	/**
	 * To string method
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * hash code method
	 */
	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}
	
	/**
	 * equals method
	 */
	@Override
	public boolean equals(Object o)
	{
		return this.name.equals(((Town)o).getName());
	}

	public String getName() {
		return name;
	}
	
}
