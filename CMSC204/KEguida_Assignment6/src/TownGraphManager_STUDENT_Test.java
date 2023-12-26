import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	private TownGraphManager graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		
		//Please make sure "US town.txt from blackboard is in the src folder"
		graph = new TownGraphManager();
		town = new String[12];
		File file = new File("src/US towns.txt");
		graph.populateTownGraph(file);
		 
		town[0]="Chicago";
		town[1]="Detroit";
		town[2]="New York";
		town[3]="Kansas City";
		town[4]="Indianapolis";
		town[5]="Washington";
		town[6]="Dallas"; 
		town[7]="Nashville"; 
		town[8]="Richmond"; 
		town[9]="Atlanta"; 
		town[10]="New Orleans"; 
		town[11]="Miami"; 
		
		//This connection is missing in the US town.txt document.
		graph.addRoad("Chicago", "Indianapolis", 182, "I-65(N)");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("I-10", roads.get(0));
		assertEquals("I-20", roads.get(1));
		assertEquals("I-24", roads.get(2));
		assertEquals("I-40(E)", roads.get(3));
		graph.addRoad(town[5], town[11], 69,"I-00");
		roads = graph.allRoads();
		assertEquals("I-00", roads.get(0));
		assertEquals("I-10", roads.get(1));
		assertEquals("I-20", roads.get(2));
		assertEquals("I-24", roads.get(3));
		assertEquals("I-40(E)", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("I-95(S)", graph.getRoad(town[5], town[8]));
		assertEquals("I-94", graph.getRoad(town[0], town[1]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Los Angeles"));
		graph.addTown("Los Angeles");
		assertEquals(true, graph.containsTown("Los Angeles"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Los Angeles"));
		graph.addTown("Los Angeles");
		ArrayList<String> path = graph.getPath(town[1],"Los Angeles");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Atlanta"));
		assertEquals(false, graph.containsTown("Los Angeles"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[7], town[8]));
		assertEquals(false, graph.containsRoadConnection(town[8], town[11]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("I-10", roads.get(0));
		assertEquals("I-20", roads.get(1));
		assertEquals("I-24", roads.get(2));
		assertEquals("I-69", roads.get(10));
		assertEquals("I-70(E)", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[10], town[11]));
		graph.deleteRoadConnection(town[10], town[11], "I-10");
		assertEquals(false, graph.containsRoadConnection(town[10], town[11]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Detroit"));
		graph.deleteTown(town[1]);
		assertEquals(false, graph.containsTown("Detroit"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Atlanta", roads.get(0));
		assertEquals("Chicago", roads.get(1));
		assertEquals("Dallas", roads.get(2));
		assertEquals("Detroit", roads.get(3));
		assertEquals("New York", roads.get(9));
	}

	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[0],town[4]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Chicago via I-65(N) to Indianapolis 182 mi",path.get(0).trim());

	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[5],town[11]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Washington via I-95(S) to Richmond 109 mi",path.get(0).trim());
		  assertEquals("Richmond via I-85 to Atlanta 533 mi",path.get(1).trim());
		  assertEquals("Atlanta via I-75(E) to Miami 664 mi",path.get(2).trim());
	}

}