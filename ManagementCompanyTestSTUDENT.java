import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ManagementCompanyTestSTUDENT {
	Property p1 ,p2,p3,p4,p5,p6;
	ManagementCompany m;
	
	@Before
	public void setUp() throws Exception {
		// Variables
		m= new ManagementCompany("Allianz", "0007",9);
		
		p1 = new Property("Owe 1", "Almadies", 500000, "Eguida Kossi", 5, 0, 2, 2);
		p2 = new Property("Owe 2", "Ouakam", 250000, "Eguida Kossi", 9, 9, 1, 1);
		p3 = new Property("Owe 3", "Plateau", 300000, "Eguida Kossi", 5, 5, 3, 4);
				
		// Add properties
		m.addProperty(p1);
		m.addProperty(p2);
		m.addProperty(p3);
		
	}

	@After
	public void tearDown() {
		//student set mgmt co to null  
		p1=p2=p3=p4=p5=p6=null;
		m=null;
	}

	@Test
	public void testAddPropertyDefaultPlot() {
		
		p4 = new Property ("Big head", "Rockville", 500000, "Eyosas");
		p5 = new Property ("JD", "Germantown", 99, "Amongus", 2, 1, 2, 3);
		p6 = new Property ("Cuidad", "Kouloumier", 997, "Kawa");
		
		assertEquals(m.addProperty(p4),3,0);
		assertEquals(m.addProperty(p5),4,0);
		assertEquals(m.addProperty(p6),-1,0);	
	}
 
	@Test
	public void testMaxRentProp() {
		assertEquals(m.maxRentProp(),500000,0);
	}

	@Test
	public void testTotalRent() {
		assertEquals(m.totalRent(),1050000,0);
	}

 }