import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * @author Kevin Eguida
 *
 */

public class CourseDBStructure implements CourseDBStructureInterface 
{
	LinkedList<CourseDBElement>[] bucketArray;
	int tableSize;
	int size;
	
	/**
	 * constructor that creates a bucket-array based hash table 
	 * with a estimated table size
	 * @param the estimated table size
	 */
	public CourseDBStructure(int n)
	{	
		tableSize=this.fourKPlus3(n,1.5);
		bucketArray=new LinkedList[tableSize];
	}
	
	/**
	 * constructor that creates a bucket-array based hash table 
	 * with a specified table size. (For testing purposes)
	 * @param indicates that this constructor is for testing
	 * purposes only
	 * @param the specified table size
	 */
	public CourseDBStructure(String testing, int size)
	{
		this.tableSize=size;
		bucketArray=new LinkedList[tableSize];
	}

	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If an CourseDatabaseElement with the same crn already exists, update the information
	*  
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	@Override
	public void add(CourseDBElement element) {
		int index = element.hashCode()%bucketArray.length;
		
		if(bucketArray[index]==null)
		{
			bucketArray[index]=new LinkedList();
			bucketArray[index].add(element);
			size++;
		}
		else
		{
			if (bucketArray[index].contains(element))
			{
				(bucketArray[index].get(bucketArray[index].indexOf(element))).setId(element.id);
				(bucketArray[index].get(bucketArray[index].indexOf(element))).setCredits(element.credits);
				(bucketArray[index].get(bucketArray[index].indexOf(element))).setRoom(element.room);
				(bucketArray[index].get(bucketArray[index].indexOf(element))).setInstructor(element.instructor);
			}
			else
			{
				bucketArray[index].add(element);
				size++;
			}
		}
		
	}

	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		
		int hashCode= Integer.valueOf(crn).hashCode();
		int index = hashCode%bucketArray.length;
		
		if (bucketArray[index]!=null)
		{
			for(int i=0; i<bucketArray[index].size(); i++)
			{
				if (( bucketArray[index].get(i)).crn==crn)
				{
					return bucketArray[index].get(i);
				}
			}
			throw new IOException();
		}
		else
			throw new IOException();
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {
		
		ArrayList<String> courses = new ArrayList(size);
		for(int i=0; i<bucketArray.length; i++)
		{
			if (bucketArray[i]!=null)
			{
				for(int j=0; j<bucketArray[i].size(); j++)
				{
					courses.add(bucketArray[i].get(j).toString());
				}
			}
		}
		return courses;
	}

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	@Override
	public int getTableSize() 
	{
		return tableSize;
	}
	
	/**
	 * finds a 4K+3 prime just greater than n/loading factor.
	* @param the estimated table size n
	* @param the loading factor
	* @return the next 4k+3 prime after the estimated size table
	*/
	public static int fourKPlus3(int n, double loadfactor)
	{  boolean fkp3 = false;
	   boolean aPrime = false;
	   int prime, highDivisor, d;
	   double pctd = loadfactor;

	   prime = (int)(n/loadfactor);
	   if(prime % 2 == 0) // if even make odd
	      prime = prime +1;
	   while(fkp3 == false) // not a 4k+3 prime
	   {  while(aPrime == false) // not a prime
	      {  highDivisor = (int)(Math.sqrt(prime) + 0.5);
	         for(d = highDivisor; d > 1; d--)
	         {  if(prime % d == 0)
	               break; // not a prime
	         }
	         if(d != 1) // prime not found
	            prime = prime + 2;
	         else
	            aPrime = true;
	      } // end of the prime search loop
	      if((prime - 3) % 4 == 0)
	         fkp3 = true;
	      else
	      {  prime = prime + 2;
	         aPrime = false;
	      }
	   } // end of 4k+3 prime search loop
	   return prime;
	}



}
