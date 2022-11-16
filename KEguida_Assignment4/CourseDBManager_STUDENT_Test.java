import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test {

	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		dataMgr = new CourseDBManager();
	}

	/**
	 * Set dataMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("MATH282",23633,4,"High-Technology 205","Luc E. Desir");
			dataMgr.add("MATH282",23633,4,"High-Technology 205","Luc E. Desir"); //Should not do anything
			assertEquals("\nCourse:MATH282 CRN:23633 Credits:4 Instructor:Luc E. Desir Room:High-Technology 205",dataMgr.showAll().get(0));
			
			dataMgr.add("MATH282",23633,4,"Science Center 217","Luc E. Desir"); // Info updated
			assertEquals("\nCourse:MATH282 CRN:23633 Credits:4 Instructor:Luc E. Desir Room:Science Center 217",dataMgr.showAll().get(0));
			
			dataMgr.add("MATH182",23666,4,"Science Center 218","Zhou Dong"); // new element
			assertEquals("\nCourse:MATH182 CRN:23666 Credits:4 Instructor:Zhou Dong Room:Science Center 218",dataMgr.showAll().get(0));
			assertEquals("\nCourse:MATH282 CRN:23633 Credits:4 Instructor:Luc E. Desir Room:Science Center 217",dataMgr.showAll().get(1));
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		dataMgr.add("COMM108",22864,3,"Humanities 173","Dr. Aaron D. Johnson");
		dataMgr.add("CMSC204",22566,4,"Zoom","Khandan Monshi");
		dataMgr.add("CHEM132",30559,4,"BE206","Richard M. Pires");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:COMM108 CRN:22864 Credits:3 Instructor:Dr. Aaron D. Johnson Room:Humanities 173");
	 	assertEquals(list.get(1),"\nCourse:CHEM132 CRN:30559 Credits:4 Instructor:Richard M. Pires Room:BE206");
		assertEquals(list.get(2),"\nCourse:CMSC204 CRN:22566 Credits:4 Instructor:Khandan Monshi Room:Zoom");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			//Please modify the path so that it works on your end
			File file = new File("C:\\Users\\kegui\\Desktop\\courses.txt");
			
			dataMgr.readFile(file);
			assertEquals("CMSC100",dataMgr.get(21556).getID());
			assertEquals(3,dataMgr.get(21565).getCredits());
			assertEquals("HT300",dataMgr.get(20484).getRoom());
			assertEquals("Sascha Simkanich",dataMgr.get(23363).getInstructor());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
