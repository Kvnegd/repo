import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Person> sortedLinkedPerson;
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
	//alphabetic order: s1 s4 s2 s3 s5
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorPerson = new PComparator();
		sortedLinkedPerson = new SortedDoubleLinkedList<Person>(comparatorPerson);
		
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorPerson = null;
		sortedLinkedString = null;
		sortedLinkedPerson = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd(s4);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront(s5);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedPerson.add(a);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(d);
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Begin");
		sortedLinkedString.add(s3);
		sortedLinkedString.add(s1);
		sortedLinkedString.add("Zompobo");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals(s1, iterator.next());
		assertEquals(s3, iterator.next());
		assertEquals("Zompobo", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zompobo", iterator.previous());
		assertEquals(s3, iterator.previous());
		assertEquals(s1, iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulCarPrevious() {
		sortedLinkedPerson.add(e);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(d);
		//alphabetic order: a b c d e 
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(e, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(e, iterator.previous());
		assertEquals(d, iterator.previous());
		assertEquals(c, iterator.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedPerson.add(f);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(a);
		//ArrayList<Car> carList = sortedLinkedPerson.toArrayList();
		//alphabetic order: a b c d e f
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(f, iterator.next());
		try{
			//no more elements in list
			iterator.next();
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
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedPerson.add(e);
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(d);
		//ArrayList<Car> carList = sortedLinkedPerson.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Person> iterator = sortedLinkedPerson.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
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
	public void testAddPerson() {
		//alphabetic order: a b c d e f
		sortedLinkedPerson.add(c);
		sortedLinkedPerson.add(d);
		sortedLinkedPerson.add(e);
		assertEquals(c, sortedLinkedPerson.getFirst());
		assertEquals(e, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(a);
		assertEquals(a, sortedLinkedPerson.getFirst());
		assertEquals(e, sortedLinkedPerson.getLast());
		//deletes last element from linked list
		assertEquals(e,sortedLinkedPerson.retrieveLastElement());
		assertEquals(d, sortedLinkedPerson.getLast());
	}

	@Test
	public void testRemoveFirstPerson() {
		//alphabetic order: a b c d e f
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(c);
		assertEquals(b, sortedLinkedPerson.getFirst());
		assertEquals(c, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(a);
		assertEquals(a, sortedLinkedPerson.getFirst());
		// remove the first
		sortedLinkedPerson.remove(a, comparatorPerson);
		assertEquals(b, sortedLinkedPerson.getFirst());
	}
	
	@Test
	public void testRemoveEndPerson() {
		//alphabetic order: a b c d e f
		sortedLinkedPerson.add(b);
		sortedLinkedPerson.add(f);
		assertEquals(b, sortedLinkedPerson.getFirst());
		assertEquals(f, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(d);
		assertEquals(f, sortedLinkedPerson.getLast());
		//remove from the end of the list
		sortedLinkedPerson.remove(f, comparatorPerson);
		assertEquals(d, sortedLinkedPerson.getLast());
	}

	@Test
	public void testRemoveMiddleCar() {
		//alphabetic order: a b c d e f
		sortedLinkedPerson.add(a);
		sortedLinkedPerson.add(b);
		assertEquals(a, sortedLinkedPerson.getFirst());
		assertEquals(b, sortedLinkedPerson.getLast());
		sortedLinkedPerson.add(f);
		assertEquals(a, sortedLinkedPerson.getFirst());
		assertEquals(f, sortedLinkedPerson.getLast());
		assertEquals(3,sortedLinkedPerson.getSize());
		//remove from middle of list
		sortedLinkedPerson.remove(b, comparatorPerson);
		assertEquals(a, sortedLinkedPerson.getFirst());
		assertEquals(f, sortedLinkedPerson.getLast());
		assertEquals(2,sortedLinkedPerson.getSize());
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
