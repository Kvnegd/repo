/**
 * 
 * @author kevin Eguida
 *
 */
public class Road implements Comparable<Road>{

	Town source, destination;
	String name;
	int weight;
	
	/**
	 * Parameterized constructor
	 * @param source
	 * @param destination
	 * @param degrees
	 * @param name
	 */
	public Road(Town source, Town destination, int degrees, String name)
	{
		this.source=new Town(source);
		this.destination=new Town(destination);
		this.weight=degrees;
		this.name=name;
	}
	
	/**
	 * Parameterized constructor with the weight fixed to 1
	 * @param source
	 * @param destination
	 * @param name
	 */
	public Road(Town source, Town destination, String name)	{this(source, destination, 1, name);}
	
	/**
	 * Returns 0 if the road names are the same, a positive 
	 * or negative number if the road names are not the same
	 */
	@Override
	public int compareTo(Road o) {
		return this.name.compareTo(o.getName());
	}
	
	/**
	 * Returns true if the road contains the town on one of its ends. Returns false otherwise.
	 * @param town
	 * @return true if the road contains the town on one of its ends/false otherwise.
	 */
	public boolean contains(Town town)
	{
		if(source.equals(town))
			return true;;
		if(destination.equals(town))
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of this road. 
	 * Remember that a road that goes from point A to point B 
	 * is the same as a road that goes from point B to point A.
	 */
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
	
	/**
	 * Over ridden toString method
	 */
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
