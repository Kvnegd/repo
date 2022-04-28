import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoDimRaggedArrayUtilitySTUDENT_Test {
	//STUDENT fill in dataSetSTUDENT with values, it must be a ragged array
	private double[][] dataSetSTUDENT = {{9, 15, 1, 20, 30}, {20, 50, 30}, {10, 15}};
	
	File inputFile,outputFile;

	@Before
	public void setUp() throws Exception {
		outputFile = new File("TestOut.txt");
	}

	@After
	public void tearDown() throws Exception {
		dataSetSTUDENT = null;
		inputFile = outputFile = null;
	}

	/**
	 * Student Test getTotal method
	 * Return the total of all the elements in the two dimensional array
	 */
	@Test
	public void testGetTotal() {
		assertEquals(200, TwoDimRaggedArrayUtility.getTotal(dataSetSTUDENT), 0.001);
	}

	/**
	 * Student Test getAverage method
	 * Return the average of all the elements in the two dimensional array
	 */
	@Test
	public void testGetAverage() {
		assertEquals(20, TwoDimRaggedArrayUtility.getAverage(dataSetSTUDENT), 0.001);
	}

	/**
	 * Student Test getRowTotal method
	 * Return the total of all the elements of the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetRowTotal() {
		assertEquals(25, TwoDimRaggedArrayUtility.getRowTotal(dataSetSTUDENT, 2), 0.001);	
	}


	/**
	 * Student Test getColumnTotal method
	 * Return the total of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */
	@Test
	public void testGetColumnTotal() {
		assertEquals(80, TwoDimRaggedArrayUtility.getColumnTotal(dataSetSTUDENT, 1), 0.001);	
	}


	/**
	 * Student Test getHighestInArray method
	 * Return the largest of all the elements in the two dimensional array.
	 */
	@Test
	public void testGetHighestInArray() {
		assertEquals(50, TwoDimRaggedArrayUtility.getHighestInArray(dataSetSTUDENT), 0.001);

	}
	

	/**
	 * Test the writeToFile method
	 * write the array to the outputFile File
	 * then read it back to make sure formatted correctly to read
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void testWriteToFile() throws FileNotFoundException {
		double[][] array=null;
		
		try {
			TwoDimRaggedArrayUtility.writeToFile(dataSetSTUDENT, outputFile);
		} catch (Exception e) {
			fail("This should not have caused an Exception");
		}
		array = TwoDimRaggedArrayUtility.readFile(outputFile);
		assertEquals(9, array[0][0],.001);
		assertEquals(15, array[0][1],.001);
		assertEquals(1, array[0][2],.001);
		assertEquals(20, array[0][3],.001);
		assertEquals(30, array[0][4],.001);
		assertEquals(20, array[1][0],.001);
		assertEquals(50, array[1][1],.001);
		assertEquals(30, array[1][2],.001);
		assertEquals(10, array[2][0],.001);
		assertEquals(15, array[2][1],.001);

	
	}

}