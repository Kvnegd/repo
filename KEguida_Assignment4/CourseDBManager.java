import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kevin Eguida
 *
 */
public class CourseDBManager implements CourseDBManagerInterface
{
	CourseDBStructure cbs = new CourseDBStructure(20);
	
	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element= new CourseDBElement(id, crn, credits, roomNum, instructor);
		cbs.add(element);
	}

	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * @throws IOException 
	 * 
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		return cbs.get(crn);
	}

	/**
	 * Reads the information of courses from a test file and adds them
	 * to the CourseDBStructure data structure
	 * @param input input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner readFile=new Scanner(input);
		String courses;
		String[] course;
		while (readFile.hasNextLine()) {
			courses=readFile.nextLine();
			course=courses.split(" ",5);
			CourseDBElement cde=new CourseDBElement(course[0],Integer.parseInt(course[1]),Integer.parseInt(course[2]),course[3],course[4]);
			cbs.add(cde);
		}
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
		return cbs.showAll();
		
	}

}
