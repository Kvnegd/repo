import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] town;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		 town = new Town[11];
		 
		 town[0]=new Town("Frederick");
		 town[1]=new Town("Clarksburg");
		 town[2]=new Town("Poolesville");
		 town[3]=new Town("Boyds");
		 town[4]=new Town("Germantown");
		 town[5]=new Town("Olney");
		 town[6]=new Town("Darnestown"); 
		 town[7]=new Town("Gaithersburg"); 
		 town[8]=new Town("Potomac"); 
		 town[9]=new Town("Rockville"); 
		 town[10]=new Town("Bethesda"); 
		 
		 for (int i = 0; i < 11; i++) {
			  graph.addVertex(town[i]);
		  }
		 
		 graph.addEdge(town[0], town[1], 14, "I270-N");
		 graph.addEdge(town[2], town[1], 13, "MD109");
		 graph.addEdge(town[4], town[1], 5, "I270-NC");
		 graph.addEdge(town[1], town[5], 14, "MD108");
		 graph.addEdge(town[3], town[1], 8, "MD121");
		 graph.addEdge(town[3], town[2], 9, "MD117-W");
		 graph.addEdge(town[3], town[4], 4, "MD117-E");
		 graph.addEdge(town[4], town[5], 15, "MD118-E");
		 graph.addEdge(town[2], town[6], 8, "MD28");
		 graph.addEdge(town[6], town[4], 6, "MD118-W");
		 graph.addEdge(town[7], town[4], 6, "I270-C");
		 graph.addEdge(town[7], town[5], 11, "Bowie Mill");
		 graph.addEdge(town[6], town[7], 7, "MD124");
		 graph.addEdge(town[5], town[9], 8, "MD97");
		 graph.addEdge(town[7], town[9], 7, "I270-SC");
		 graph.addEdge(town[8], town[6], 8, "MD190-W");
		 graph.addEdge(town[9], town[8], 6, "MD189");
		 graph.addEdge(town[8], town[10], 7, "MD190-E");
		 graph.addEdge(town[9], town[10], 7, "I270-S");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		town = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(town[7], town[5],11, "Bowie Mill"), graph.getEdge(town[5], town[7]));
		assertEquals(new Road(town[2], town[6],8, "MD28"), graph.getEdge(town[6], town[2]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(town[0], town[10]));
		graph.addEdge(town[10], town[0], 21, "I270 Express");
		assertEquals(true, graph.containsEdge(town[0], town[10]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Damascus");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(town[0], town[1]));
		assertEquals(false, graph.containsEdge(town[0], town[7]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Germantown")));
		assertEquals(false, graph.containsVertex(new Town("Kingsview")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Bowie Mill", roadArrayList.get(0));
		assertEquals("I270-C", roadArrayList.get(1));
		assertEquals("I270-SC", roadArrayList.get(5));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(town[1]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("I270-N", roadArrayList.get(0));
		assertEquals("I270-NC", roadArrayList.get(1));
		assertEquals("MD108", roadArrayList.get(2));
		assertEquals("MD109", roadArrayList.get(3));
		assertEquals("MD121", roadArrayList.get(4));
		
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(town[0], town[1]));
		graph.removeEdge(town[0], town[1], 14, "I270N");
		assertEquals(true, graph.containsEdge(town[0], town[1]));
		graph.removeEdge(town[1], town[0], 14, "I270-N");
		assertEquals(false, graph.containsEdge(town[0], town[1]));

	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(town[0]));
		graph.removeVertex(town[0]);
		assertEquals(false, graph.containsVertex(town[0]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> townArrayList = new ArrayList<String>();
		for(Town t : towns)
			townArrayList.add(t.getName());
		Collections.sort(townArrayList);
		assertEquals("Bethesda", townArrayList.get(0));
		assertEquals("Boyds", townArrayList.get(1));
		assertEquals("Clarksburg", townArrayList.get(2));
		assertEquals("Darnestown", townArrayList.get(3));
		assertEquals("Germantown", townArrayList.get(6));
		assertEquals("Rockville", townArrayList.get(10));
	}

	 @Test
	  public void testFrederickToDarnestown() {
		  String beginTown = "Frederick", endTown = "Darnestown";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Frederick via I270-N to Clarksburg 14 mi",path.get(0).trim());
			  assertEquals("Clarksburg via I270-NC to Germantown 5 mi",path.get(1).trim());
			  assertEquals("Germantown via MD118-W to Darnestown 6 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testFrederickToBethesda() {
		  String beginTown = "Frederick", endTown = "Bethesda";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Frederick via I270-N to Clarksburg 14 mi",path.get(0).trim());
			  assertEquals("Clarksburg via I270-NC to Germantown 5 mi",path.get(1).trim());
			  assertEquals("Germantown via I270-C to Gaithersburg 6 mi",path.get(2).trim());
			  assertEquals("Gaithersburg via I270-SC to Rockville 7 mi",path.get(3).trim());
			  assertEquals("Rockville via I270-S to Bethesda 7 mi",path.get(4).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
}