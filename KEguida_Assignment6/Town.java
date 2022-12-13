import java.util.ArrayList;

public class Town implements Comparable<Town>{

	String name;
	ArrayList<Town> edgeList;
	int distanceTo=Integer.MAX_VALUE;
	Town by;
	boolean visited=false;
	
	public Town(String name) 
	{
		this.name = name;
		edgeList = new ArrayList<Town>();
	}
	
	public Town(Town templateTown)
	{
		this.name = templateTown.getName();
		edgeList = new ArrayList<Town>(templateTown.edgeList);
	}
	
	@Override
	public int compareTo(Town o) {
		return this.name.compareTo(o.getName());
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
	
	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.name.equals(((Town)o).getName());
	}

	public String getName() {
		return name;
	}
	
}
