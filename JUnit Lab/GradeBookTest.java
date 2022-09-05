import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {
	GradeBook b1, b2;
	
	@BeforeEach
	void setUp() throws Exception {
		b1 = new GradeBook(5);
		b2 = new GradeBook(5);
		b1.addScore(50);
		b1.addScore(25);
		b1.addScore(0);
		b2.addScore(100);
		b2.addScore(90);
	}

	@AfterEach
	void tearDown() throws Exception {
		b1 = b2 = null;
	}

	@Test
	void testSum() {
		assertEquals(75, b1.sum());	
		assertEquals(190, b2.sum());	
		
	}
	
	@Test
	void testAddScore() {
		assertTrue(b1.toString().equals("50.0 25.0 0.0 "));	
		assertTrue(b2.toString().equals("100.0 90.0 "));	
		assertEquals(3, b1.getScoresSize());	
		assertEquals(2, b2.getScoresSize());	
	}
	
	@Test
	void testMinimum() {
		assertEquals(0, b1.minimum());
        assertEquals(90, b2.minimum());
	}
	
	@Test
	void testFinalScore() {
		assertEquals(75, b1.finalScore());
        assertEquals(100, b2.finalScore());

	}
	
	

}
