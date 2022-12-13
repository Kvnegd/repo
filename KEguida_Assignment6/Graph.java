import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Graph implements GraphInterface<Town, Road>{

	ArrayList<Road> roadList;
	ArrayList<Town> townList;
	
	public Graph ()
	{
		roadList= new ArrayList<Road>();
		townList = new ArrayList<Town>();
	}
	
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road r : roadList)
		{
			if(r.source.equals(sourceVertex)&&r.destination.equals(destinationVertex))
				return roadList.get(roadList.indexOf(r));
			if(r.source.equals(destinationVertex)&&r.destination.equals(sourceVertex))
				return roadList.get(roadList.indexOf(r));
		}
		return null;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (townList.contains(sourceVertex)&&townList.contains(destinationVertex))
		{
			Road newEdge = new Road(sourceVertex, destinationVertex, weight, description);
			Road edgeNew = new Road(destinationVertex, sourceVertex, weight, description);
			if (!roadList.contains(newEdge)&&!roadList.contains(edgeNew))
			{
				townList.get(townList.indexOf(sourceVertex)).edgeList.add(destinationVertex);
				townList.get(townList.indexOf(destinationVertex)).edgeList.add(sourceVertex);
				roadList.add(newEdge);
			}
			return newEdge;
		}
		else
			return null;
	}

	@Override
	public boolean addVertex(Town v) {
		if (!townList.contains(v))
		{
			townList.add(v);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Road road = new Road(sourceVertex, destinationVertex, 0, " ");
		for (Road r : roadList)
		{
			if(r.equals(road))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsVertex(Town v) {
		for (Town t : townList)
		{
			if(t.equals(v))
				return true;
		}
		return false;
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> edgeSet = new HashSet<Road>();
		for (Road r : roadList)
			edgeSet.add(r);
		return edgeSet;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edgesOf =new HashSet<Road>();
		for(Town t : vertex.edgeList)
		{
			edgesOf.add(getEdge(vertex,t));
		}
		return edgesOf;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road removed = new Road(sourceVertex, destinationVertex, weight, description);
		if (weight>-1)
		{
			if(removed.weight!=getEdge(sourceVertex, destinationVertex).weight)
			{
				System.out.println("In removeEdge: Road distances don't match.");
				return null;
			}
				
		}
		if (description!=null)
		{
			if(removed.name!=getEdge(sourceVertex, destinationVertex).name)
			{
				System.out.println("In removeEdge: Road names don't match.");
				return null;
			}
				
		}
		if(roadList.contains(removed)) {
			townList.get(townList.indexOf(sourceVertex)).edgeList.remove(destinationVertex);
			townList.get(townList.indexOf(destinationVertex)).edgeList.remove(sourceVertex);
			return roadList.remove(roadList.indexOf(removed));
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		ArrayList<Town> neighbors = new ArrayList();
		for (Town t : townList.get(townList.indexOf(v)).edgeList)
			neighbors.add(t);
		if (townList.contains(v))
		{
			for (Town t : neighbors)
			{
				removeEdge(v,t,getEdge(v,t).weight,getEdge(v,t).name);
			}
			townList.remove(v);
			return true;
		}
		return false;
	}

	@Override
	public Set<Town> vertexSet() {
		Set<Town> TownSet = new HashSet<Town>();
		for (Town t : townList)
			TownSet.add(t);
		return TownSet;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		Town currentTown = townList.get(townList.indexOf(destinationVertex));
		ArrayList<String> path = new ArrayList();
		Stack<String> stringStack = new Stack();
		if(!currentTown.edgeList.isEmpty())
		{
			while(currentTown.distanceTo!=0)
			{
				stringStack.push(currentTown.by.name + " via " + getEdge(currentTown,currentTown.by).name + " to " 
								+ currentTown.name + " " + getEdge(currentTown,currentTown.by).weight + " mi");
				currentTown=currentTown.by;
			}
			while(!stringStack.isEmpty())
				path.add(stringStack.pop());
		}
		return path;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		ArrayList<Town> unvisitedTown = new ArrayList();
		townList.get(townList.indexOf(sourceVertex)).distanceTo=0;
		townList.get(townList.indexOf(sourceVertex)).by = new Town(" ");
		for (Town t : townList)
		{
			if (!t.edgeList.isEmpty())
				unvisitedTown.add(t);
		}
		while(!unvisitedTown.isEmpty())
		{
			Town currentTown = new Town(" ");
			currentTown.distanceTo=Integer.MAX_VALUE;
			for (Town t : unvisitedTown) 
			{
				if (t.distanceTo<currentTown.distanceTo)
					currentTown=t;
			}
			for (Town t : currentTown.edgeList)
			{
				if(!t.visited)
				{
					if (townList.get(townList.indexOf(t)).distanceTo>(getEdge(currentTown,t).weight + townList.get(townList.indexOf(currentTown)).distanceTo))
					{
						townList.get(townList.indexOf(t)).distanceTo=getEdge(currentTown,t).weight + townList.get(townList.indexOf(currentTown)).distanceTo;
						townList.get(townList.indexOf(t)).by= currentTown;
					}
				}
			}
			currentTown.visited=true;
			unvisitedTown.remove(unvisitedTown.indexOf(currentTown));
		}
		
	}

}
