import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	
	private Road road;
	private Town town1, town2;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("myFirstTown");
		town2 = new Town("mySecondTown");
		road = new Road(town1, town2, 0, ":3");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = null;
		road = null;
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, road.compareTo(new Road(town2, town1, 10, ":3")));
	}
	
	@Test
	public void testContains()
	{
		Town t = new Town("et non.");
		assertEquals(true, road.contains(town1));
		assertEquals(true, road.contains(town2));
		assertEquals(false, road.contains(t));
	}
	
	@Test
	public void testToString() {
		assertEquals(":3", road.toString());
		
	}
	
	@Test
	public void testEquals() 
	{
		Road roadTester = new Road(town1, town2, 0, "x)");
		Road testerRoad = new Road(town2, town1, 12, ">:(");
		
		assertEquals(true, road.equals(roadTester));
		assertEquals(true, road.equals(testerRoad));
		
	}
}
