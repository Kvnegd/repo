public class Road implements Comparable<Road>{

	Town source, destination;
	String name;
	int weight;
	
	public Road(Town source, Town destination, int degrees, String name)
	{
		this.source=new Town(source);
		this.destination=new Town(destination);
		this.weight=degrees;
		this.name=name;
	}
	
	public Road(Town source, Town destination, String name)	{this(source, destination, 1, name);}
	
	
	@Override
	public int compareTo(Road o) {
		return this.name.compareTo(o.getName());
	}
	
	public boolean contains(Town town)
	{
		if(source.equals(town))
			return true;;
		if(destination.equals(town))
			return true;
		else
			return false;
	}
	
	@Override
	public boolean equals(Object r)
	{
		if (this.source.equals(((Road)r).source)&&this.destination.equals(((Road)r).destination))
			return true;
		else if (this.source.equals(((Road)r).destination)&&this.destination.equals(((Road)r).source))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}

	public Town getSource() {return source;}

	public Town getDestination() {return destination;}

	public String getName() {return name;}

	public int getWeight() {return weight;}

}
