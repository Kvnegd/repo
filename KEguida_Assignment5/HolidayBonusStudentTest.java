import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HolidayBonusStudentTest {
	
	private double[][] dataSet1 = {{1.1, 2.2, 3.3, 4.4}, {5.5, 6.6, 7.7}, {8.8, 9.9}};
	  private double[][] dataSet2 = {{-1, 2, 0, 4}, {0}, {6, -1192, 8}, {-998, 10, -101, 12}};
	  private double[][] dataSet3 = {{-0.1, -3.8, -99, -7006}, {-9098, -21}, {0}, {-17.6, -8, -32.1}};

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test calculateHolidayBonus method with a high of 5000, low of 1000 and 2000 as other
	 */

	@Test
	public void testCalculateHolidayBonus() {
		
	    try {
	      double[] result = HolidayBonus.calculateHolidayBonus(dataSet1, 5000, 1000, 2000);
	      assertEquals(8000.0, result[0], .001);
	      assertEquals(9000.0, result[1], .001);
	      assertEquals(10000.0, result[2], .001);

	      result = HolidayBonus.calculateHolidayBonus(dataSet2, 5000, 1000, 2000);
	      assertEquals(3000.0, result[0], .001);
	      assertEquals(0.0, result[1], .001);
	      assertEquals(10000.0, result[2], .001);
	      assertEquals(10000.0, result[3], .001);

	      result = HolidayBonus.calculateHolidayBonus(dataSet3, 5000, 1000, 2000);
	      assertEquals(0.0, result[0], .001);
	      assertEquals(0.0, result[1], .001);
	      assertEquals(0.0, result[2], .001);
	      assertEquals(0.0, result[3], .001);
	    } catch (Exception e) {
	      fail("This should not have caused an Exception");
	    }
		
	}
	
	/**
	 * Test calculateTotalHolidayBonus method with a high of 5000, low of 1000 and 2000 as other
	 */
	@Test
	public void testCalculateTotalHolidayBonusA() {
	    assertEquals(27000.0, HolidayBonus.calculateTotalHolidayBonus(dataSet1, 5000, 1000, 2000),.001);
	    assertEquals(23000.0, HolidayBonus.calculateTotalHolidayBonus(dataSet2, 5000, 1000, 2000),.001);
	    assertEquals(0.0, HolidayBonus.calculateTotalHolidayBonus(dataSet3, 5000, 1000, 2000),.001);
	
	}

}
