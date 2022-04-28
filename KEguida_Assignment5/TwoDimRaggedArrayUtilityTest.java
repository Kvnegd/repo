//package _solution;
//package _solutionF2018;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoDimRaggedArrayUtilityTest {
	private double[][] dataSet1 = {{1,2,3},{4,5},{6,7,8}};
	private double[][] dataSet2 = {{7,2,9,4},{5},{8,1,3},{11,6,7,2}};
	private double[][] dataSet3 = {{7.2,2.5,9.3},{5.9},{8.1,1.7,3.3},{11.6,6.9,7.3,2.7}};
	//dataSet3 has fewer elements in first row than in other rows
	private double[][] dataSet4 = {{-2.5,-5.3,6.1},{-4.4,8.2},{2.3,-7.5},{-4.2,7.3,-5.9,2.6}};
	//dataSet4 has negative elements as well as positive
	
	private File inputFile,outputFile;

	@Before
	public void setUp() throws Exception {
		outputFile = new File("TestOut.txt");
	}

	@After
	public void tearDown() throws Exception {
		dataSet1 = dataSet2 = dataSet3 = dataSet4 = null;
		inputFile = outputFile = null;
	}

	/**
	 * Test getTotal method
	 * Returns the total of all the elements in the two dimensional array
	 */
	@Test
	public void testGetTotal() {
		assertEquals(36.0,TwoDimRaggedArrayUtility.getTotal(dataSet1),.001);
		assertEquals(65.0,TwoDimRaggedArrayUtility.getTotal(dataSet2),.001);
		assertEquals(66.5,TwoDimRaggedArrayUtility.getTotal(dataSet3),.001);
		assertEquals(-3.3,TwoDimRaggedArrayUtility.getTotal(dataSet4),.001);
	}

	/**
	 * Test getAverage method
	 * Returns the average of all the elements in the two dimensional array
	 */
	@Test
	public void testGetAverage() {
		assertEquals(4.5,TwoDimRaggedArrayUtility.getAverage(dataSet1),.001);
		assertEquals(5.417,TwoDimRaggedArrayUtility.getAverage(dataSet2),.001);
		assertEquals(6.045,TwoDimRaggedArrayUtility.getAverage(dataSet3),.001);
		assertEquals(-.3,TwoDimRaggedArrayUtility.getAverage(dataSet4),.001);
		//fail("Not yet implemented");
	}

	
	/**
	 * Test getRowTotal method
	 * Returns the total of all the elements of the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetRowTotal() {
		assertEquals(9.0,TwoDimRaggedArrayUtility.getRowTotal(dataSet1,1),.001);
		assertEquals(5.0,TwoDimRaggedArrayUtility.getRowTotal(dataSet2,1),.001);
		assertEquals(22.0,TwoDimRaggedArrayUtility.getRowTotal(dataSet2,0),.001);
		assertEquals(28.5,TwoDimRaggedArrayUtility.getRowTotal(dataSet3,3),.001);
		assertEquals(5.9,TwoDimRaggedArrayUtility.getRowTotal(dataSet3,1),.001);
		assertEquals(3.8,TwoDimRaggedArrayUtility.getRowTotal(dataSet4,1),.001);
		assertEquals(-.2,TwoDimRaggedArrayUtility.getRowTotal(dataSet4,3),.001);
		//fail("Not yet implemented");
	}
	

	/**
	 * Test getColumnTotal method
	 * Returns the total of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */
	@Test
	public void testGetColumnTotal() {
		assertEquals(11.0,TwoDimRaggedArrayUtility.getColumnTotal(dataSet1,0),.001);
		assertEquals(19.0,TwoDimRaggedArrayUtility.getColumnTotal(dataSet2,2),.001);
		assertEquals(11.1,TwoDimRaggedArrayUtility.getColumnTotal(dataSet3,1),.001);
		assertEquals(-8.8,TwoDimRaggedArrayUtility.getColumnTotal(dataSet4,0),.001);
		assertEquals(2.7,TwoDimRaggedArrayUtility.getColumnTotal(dataSet4,1),.001);
		//fail("Not yet implemented");
	}
	

	/**
	 * Test getHighestInRow method
	 * Returns the largest of all the elements in the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetHighestInRow() {
		assertEquals(3.0,TwoDimRaggedArrayUtility.getHighestInRow(dataSet1,0),.001);
		assertEquals(8.0,TwoDimRaggedArrayUtility.getHighestInRow(dataSet2,2),.001);
		assertEquals(5.9,TwoDimRaggedArrayUtility.getHighestInRow(dataSet3,1),.001);
		assertEquals(6.1,TwoDimRaggedArrayUtility.getHighestInRow(dataSet4,0),.001);
		assertEquals(8.2,TwoDimRaggedArrayUtility.getHighestInRow(dataSet4,1),.001);
		//fail("Not yet implemented");
	}

	/**
	 * Test getHighestInRowIndex method
	 * Returns the index of the largest of all the elements in the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetHighestInRowIndex() {
		assertEquals(2,TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet1,0));
		assertEquals(0,TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet2,2));
		assertEquals(0,TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet3,1));
		assertEquals(2,TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet4,0));
		assertEquals(1,TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet4,1));
	}
	
	/**
	 * Test getLowestInRowIndex method
	 * Returns the index of the smallest of all the elements in the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetLowestInRowIndex() {
		assertEquals(0,TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet1,0));
		assertEquals(1,TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet2,2));
		assertEquals(0,TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet3,1));
		assertEquals(1,TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet4,0));
		assertEquals(0,TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet4,1));
	}

	/**
	 * Test getLowestInRow method
	 * Returns the smallest of all the elements in the row.
	 * Row 0 refers to the first row in the two dimensional array
	 */
	@Test
	public void testGetLowestInRow() {
		assertEquals(6.0,TwoDimRaggedArrayUtility.getLowestInRow(dataSet1,2),.001);
		assertEquals(5.0,TwoDimRaggedArrayUtility.getLowestInRow(dataSet2,1),.001);
		assertEquals(2.5,TwoDimRaggedArrayUtility.getLowestInRow(dataSet3,0),.001);
		assertEquals(-4.4,TwoDimRaggedArrayUtility.getLowestInRow(dataSet4,1),.001);
		assertEquals(-7.5,TwoDimRaggedArrayUtility.getLowestInRow(dataSet4,2),.001);
		//fail("Not yet implemented");
	}
	

	/**
	 * Test getHighestInColumn method
	 * Returns the largest of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */
	@Test
	public void testGetHighestInColumn() {
		assertEquals(8.0,TwoDimRaggedArrayUtility.getHighestInColumn(dataSet1,2),.001);
		assertEquals(6.0,TwoDimRaggedArrayUtility.getHighestInColumn(dataSet2,1),.001);
		assertEquals(11.6,TwoDimRaggedArrayUtility.getHighestInColumn(dataSet3,0),.001);
		assertEquals(8.2,TwoDimRaggedArrayUtility.getHighestInColumn(dataSet4,1),.001);
		assertEquals(6.1,TwoDimRaggedArrayUtility.getHighestInColumn(dataSet4,2),.001);
		//fail("Not yet implemented");
	}

	/**
	 * Test getHighestInColumn method
	 * Returns the index of the largest of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */
	@Test
	public void testGetHighestInColumnIndex() {
		assertEquals(2,TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet1,2));
		assertEquals(3,TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet2,1));
		assertEquals(3,TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet3,0));
		assertEquals(1,TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet4,1));
		assertEquals(0,TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet4,2));
		//fail("Not yet implemented");
	}
	/**
	 * Test getLowestInColumn method
	 * Returns the smallest of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */

	@Test
	public void testGetLowestInColumn() {
		assertEquals(2.0,TwoDimRaggedArrayUtility.getLowestInColumn(dataSet1,1),.001);
		assertEquals(3.0,TwoDimRaggedArrayUtility.getLowestInColumn(dataSet2,2),.001);
		assertEquals(5.9,TwoDimRaggedArrayUtility.getLowestInColumn(dataSet3,0),.001);
		assertEquals(-4.4,TwoDimRaggedArrayUtility.getLowestInColumn(dataSet4,0),.001);
		assertEquals(-7.5,TwoDimRaggedArrayUtility.getLowestInColumn(dataSet4,1),.001);
		//fail("Not yet implemented");
	}

	/**
	 * Test getLowestInColumnIndex method
	 * Returns the index of the smallest of all the elements in the column. If a row in the two dimensional array
	 * doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * Column 0 refers to the first column in the two dimensional array
	 */

	@Test
	public void testGetLowestInColumnIndex() {
		assertEquals(0,TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet1,1));
		assertEquals(2,TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet2,2));
		assertEquals(1,TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet3,0));
		assertEquals(1,TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet4,0));
		assertEquals(2,TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet4,1));
		//fail("Not yet implemented");
	}
	/**
	 * Test getHighestInArray method
	 * Returns the largest of all the elements in the two dimensional array.
	 */
	@Test
	public void testGetHighestInArray() {
		assertEquals(8.0,TwoDimRaggedArrayUtility.getHighestInArray(dataSet1),.001);
		assertEquals(11.0,TwoDimRaggedArrayUtility.getHighestInArray(dataSet2),.001);
		assertEquals(11.6,TwoDimRaggedArrayUtility.getHighestInArray(dataSet3),.001);
		assertEquals(8.2,TwoDimRaggedArrayUtility.getHighestInArray(dataSet4),.001);
		//fail("Not yet implemented");
	}
	

	/**
	 * Test getLowestInArray method
	 * Returns the smallest of all the elements in the two dimensional array.
	 */
	@Test
	public void testGetLowestInArray() {
		assertEquals(1.0,TwoDimRaggedArrayUtility.getLowestInArray(dataSet1),.001);
		assertEquals(1.0,TwoDimRaggedArrayUtility.getLowestInArray(dataSet2),.001);
		assertEquals(1.7,TwoDimRaggedArrayUtility.getLowestInArray(dataSet3),.001);
		assertEquals(-7.5,TwoDimRaggedArrayUtility.getLowestInArray(dataSet4),.001);
		//fail("Not yet implemented");
	}
	
	/**
	 * Test the writeToFile method
	 * write the array to the outputFile File
	 * then read it back to make sure formatted correctly to read
	 * 
	 */
	@Test
	public void testWriteToFile() throws FileNotFoundException {
		double[][] array=null;
		try {
			TwoDimRaggedArrayUtility.writeToFile(dataSet4, outputFile);
		} catch (Exception e) {
			fail("This should not have caused an Exception");
		}
		array = TwoDimRaggedArrayUtility.readFile(outputFile);
		assertEquals(-2.5, array[0][0],.001);
		assertEquals(-5.3, array[0][1],.001);
		assertEquals(6.1, array[0][2],.001);
		assertEquals(-4.4, array[1][0],.001);
		assertEquals(8.2, array[1][1],.001);
		assertEquals(2.3, array[2][0],.001);
		assertEquals(-7.5, array[2][1],.001);
		assertEquals(-4.2, array[3][0],.001);
		assertEquals(7.3, array[3][1],.001);
		assertEquals(-5.9, array[3][2],.001);
		assertEquals(2.6, array[3][3],.001);		
		
	}
	
	/**
	 * Test the readFile method
	 * reads from a file and then test that the returned two dimensional array of doubles
	 * is ragged.
	 */
	@Test
	public void testReadFile() {
		double[][] array=null;
		try {
			inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("-2.5 -5.3 6.1\n" +
					"-4.4 8.2\n" +
					"2.3 -7.5\n" +
					"-4.2 7.3 -5.9 2.6");
			inFile.close();
			array = TwoDimRaggedArrayUtility.readFile(inputFile);
			assertEquals(-2.5, array[0][0],.001);
			assertEquals(-5.3, array[0][1],.001);
			assertEquals(6.1, array[0][2],.001);
			assertEquals(-4.4, array[1][0],.001);
			assertEquals(8.2, array[1][1],.001);
			assertEquals(2.3, array[2][0],.001);
			assertEquals(-7.5, array[2][1],.001);
			assertEquals(-4.2, array[3][0],.001);
			assertEquals(7.3, array[3][1],.001);
			assertEquals(-5.9, array[3][2],.001);
			assertEquals(2.6, array[3][3],.001);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an FileNotFoundException");
		}

		//testing that the array is a ragged array
		try{
			assertEquals(0.0, array[1][2],.001);
			fail("This should have caused a ArrayIndexOutOfBoundsException");

		} catch (ArrayIndexOutOfBoundsException e){

			assertTrue("Correctly threw ArrayIndexOutOfBoundsException", true);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an Exception");
		}

		//testing that the array is a ragged array
		try{
			assertEquals(0.0, array[2][2],.001);
			fail("This should have caused a ArrayIndexOutOfBoundsException");

		} catch (ArrayIndexOutOfBoundsException e){

			assertTrue("Correctly threw ArrayIndexOutOfBoundsException", true);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an Exception");
		}
	}
	
	
}