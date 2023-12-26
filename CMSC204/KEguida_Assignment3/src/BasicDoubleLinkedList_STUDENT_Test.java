import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Person> linkedPerson;
	StringComparator comparator;
	PComparator comparatorPerson;
	
	public Person a=new Person("Kevin", 18);
	public Person b=new Person("Eric", 22);
	public Person c=new Person("Sandra", 25);
	public Person d=new Person("Natacha", 28);
	public Person e=new Person("Eddie", 30);
	public Person f=new Person("Papa", 59);
	
	public String s1="Just";
	public String s2="The";
	public String s3="Two";
	public String s4="Of";
	public String s5="Us";

	public ArrayList<Person> fill = new ArrayList<Person>();
	

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd(s4);
		linkedString.addToEnd(s3);
		comparator = new StringComparator();
		
		linkedPerson= new BasicDoubleLinkedList<Person>();
		linkedPerson.addToEnd(b);
		linkedPerson.addToEnd(c);
		comparatorPerson = new PComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedPerson = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedPerson.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals(s3, linkedString.getLast());
		linkedString.addToEnd(s5);
		assertEquals(s5, linkedString.getLast());		
		assertEquals(c,linkedPerson.getLast());
		linkedPerson.addToEnd(d);
		assertEquals(d,linkedPerson.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals(s4, linkedString.getFirst());
		linkedString.addToFront(s1);
		assertEquals(s1, linkedString.getFirst());
		
		assertEquals(b,linkedPerson.getFirst());
		linkedPerson.addToFront(a);
		assertEquals(a,linkedPerson.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(s4, linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		
		assertEquals(b,linkedPerson.getFirst());
		linkedPerson.addToFront(a);
		assertEquals(a,linkedPerson.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(s3, linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		
		assertEquals(c,linkedPerson.getLast());
		linkedPerson.addToEnd(d);
		assertEquals(d,linkedPerson.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Person> list;
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		list = linkedPerson.toArrayList();
		assertEquals(a,list.get(0));
		assertEquals(b,list.get(1));
		assertEquals(c,list.get(2));
		assertEquals(d,list.get(3));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront(s1);
		linkedString.addToEnd(s5);
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(s1, iterator.next());
		assertEquals(s4, iterator.next());
		assertEquals(s3, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(s5, iterator.next());
		
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(d, iteratorPerson.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(d, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasPrevious());
		assertEquals(d, iteratorPerson.previous());
		assertEquals(c, iteratorPerson.previous());
		assertEquals(b, iteratorPerson.previous());
		assertEquals(a, iteratorPerson.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();		
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(d, iteratorPerson.next());
		
		try{
			//no more elements in list
			iteratorPerson.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();		
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(d, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasPrevious());
		assertEquals(d, iteratorPerson.previous());
		assertEquals(c, iteratorPerson.previous());
		assertEquals(b, iteratorPerson.previous());
		assertEquals(a, iteratorPerson.previous());
		
		try{
			//no more elements in list
			iteratorPerson.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedPerson.addToFront(a);
		linkedPerson.addToEnd(d);
		ListIterator<Person> iteratorPerson = linkedPerson.iterator();		
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(a, iteratorPerson.next());
		assertEquals(b, iteratorPerson.next());
		assertEquals(c, iteratorPerson.next());
		assertEquals(true, iteratorPerson.hasNext());
		assertEquals(d, iteratorPerson.next());
		
		try{
			//remove is not supported for the iterator
			iteratorPerson.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedPerson.getFirst());
		assertEquals(c, linkedPerson.getLast());
		linkedPerson.addToFront(a);
		assertEquals(a, linkedPerson.getFirst());
		linkedPerson.remove(a, comparatorPerson);
		assertEquals(b, linkedPerson.getFirst());
		//remove from the end of the list
		linkedPerson.addToEnd(d);
		assertEquals(d, linkedPerson.getLast());
		linkedPerson.remove(d, comparatorPerson);
		assertEquals(c, linkedPerson.getLast());
		//remove from middle of list
		linkedPerson.addToFront(a);
		assertEquals(a, linkedPerson.getFirst());
		assertEquals(c, linkedPerson.getLast());
		linkedPerson.remove(b, comparatorPerson);
		assertEquals(a, linkedPerson.getFirst());
		assertEquals(c, linkedPerson.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedPerson.getFirst());
		linkedPerson.addToFront(a);
		assertEquals(a, linkedPerson.getFirst());
		
		assertEquals(a, linkedPerson.retrieveFirstElement());
		assertEquals(b,linkedPerson.getFirst());
		assertEquals(b, linkedPerson.retrieveFirstElement());
		assertEquals(c,linkedPerson.getFirst());
		
		assertEquals(s4, linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals(s4,linkedString.getFirst());
		assertEquals(s4, linkedString.retrieveFirstElement());
		assertEquals(s3,linkedString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedPerson.getLast());
		linkedPerson.addToEnd(d);
		assertEquals(d, linkedPerson.getLast());
		assertEquals(d, linkedPerson.retrieveLastElement());
		assertEquals(c,linkedPerson.getLast());
		
		assertEquals(s3, linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals(s3,linkedString.getLast());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class PComparator implements Comparator<Person>
	{

		@Override
		public int compare(Person arg0, Person arg1) {
			// person sorted in increasing order of age
			return Integer.valueOf(arg0.getAge()).compareTo(arg1.getAge());
			
		}
		
	}
	
	private class Person{
		String name;
		int age;
		
		public Person(String name, int age){
			this.name = name;
			this.age = age;
		}
		
		public String getName(){
			return name;
		}
		
		public int getAge(){
			return age;
		}
		
		public String toString() {
			return (getName()+" "+getAge());
		}
	}
}
