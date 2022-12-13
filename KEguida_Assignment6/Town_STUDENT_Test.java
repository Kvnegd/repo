import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {
	
	Town town;
	
	@Before
	public void setUp() throws Exception {
		town = new Town("myTown");
	}

	@After
	public void tearDown() throws Exception {
		town = null;
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, town.compareTo(new Town("myTown")));
	}
	
	@Test
	public void testToString() {
		assertEquals("myTown", town.toString());
		
	}
	
	@Test
	public void testHashCode() {
		assertEquals((new String("myTown")).hashCode(), town.hashCode());
		
	}
	
	@Test
	public void testEquals() {
		assertEquals(true, town.equals(new Town("myTown")));
	}
	
	@Test
	public void testGetName() {
		assertEquals("myTown", town.getName());
	}
}
