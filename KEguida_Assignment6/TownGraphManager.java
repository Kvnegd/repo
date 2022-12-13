import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{

	Graph graph = new Graph();
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if (graph.addEdge(new Town(town1), new Town(town2), weight, roadName)!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		if (graph.getEdge(new Town(town1), new Town(town2))!=null)
			return graph.getEdge(new Town(town1), new Town(town2)).name;
		return null;
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		Town town=null;
		for(Town t : graph.townList)
		{
			if (t.equals(new Town(name)))
				town = t;
		}
		
		return town;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads= new ArrayList();
		for(Road r : graph.roadList)
		{
			roads.add(r.name);
		}
		Collections.sort(roads);
		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if (graph.removeEdge(new Town(town1), new Town(town2), graph.getEdge(new Town(town1), new Town(town2)).weight, graph.getEdge(new Town(town1), new Town(town2)).name)!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns= new ArrayList();
		for(Town t : graph.townList)
		{
			towns.add(t.name);
		}
		Collections.sort(towns);
		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File file) throws IOException{
		Scanner input = new Scanner(file);
		String[] line;
		String[] road;		
		while (input.hasNext())
		{
			line = input.nextLine().split(";");
			road = line[0].split(",");
			addTown(line[1]);
			addTown(line[2]);
			addRoad(line[1],line[2],Integer.valueOf(road[1]),road[0]);
		}
		
	}

}
