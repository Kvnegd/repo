/**
 * 
 * @author Kevin Eguida
 *
 */
public class CourseDBElement implements Comparable
{
	
	String id;
	int crn, credits;
	String room;
	String instructor;
	
	/** Constructor that creates an empty CourseDBElement 
	 */
	 public CourseDBElement() 
	{
		this(null,0,0,null,null);
	}
	
	/** Constructor that creates an CourseDBElement 
	 * object with the specified information
	 * @param the course ID
	 * @param the course crn
	 * @param the number of credits this course has
	 * @param the class room
	 * @param the name of the instructor
	 */
	public CourseDBElement(String id, int crn, int credits, String room, String instructor) 
	{
		this.id=id;
		this.crn=crn;
		this.credits=credits;
		this.room=room;
		this.instructor=instructor;
	}

	/**
	 * Implements the compareTo method from 
	 * Comparable interface.
	 * @param another CourseDBElement
	 */
	@Override
	public int compareTo(Object o) {
		if(this.crn>(int)o)
			return 1;
		else if(this.crn<(int)o)
			return -1;
		else
			return 0;
	}

	/**
	 * getters for the ID
	 * @return the course ID
	 */
	public String getID() {
		return id;
	}

	/**
	 * setter for the course ID
	 * @param the course ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * getters for the crn
	 * @return the course crn
	 */
	public int getCRN() {
		return crn;
	}

	/**
	 * setter for the course crn
	 * @param the course crn
	 */
	public void setCrn(int crn) {
		this.crn = crn;
	}

	/**
	 * getters for the number of credit
	 * @return the number of credit this course has
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * setter for the number of credits
	 * @param the number of credits this course has
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * getters for the classroom
	 * @return the course's classroom
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * setter for the classroom
	 * @param the course's classroom
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * getters for the instructor's name
	 * @return the instructor's name
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * setter for the instructor's name
	 * @param the instructor's name
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * Formats the output of the courseBDElement toString call
	 */
	@Override
	public String toString() {
		return "\nCourse:"+this.id+" CRN:"+this.crn+" Credits:"+this.credits+" Instructor:"+this.instructor+" Room:"+this.room;
	}
	
	/**
	 * overrides the equals method
	 * @return true if their crn are equal, returns false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if(this.crn==(((CourseDBElement) o).getCRN()))
			return true;
		else
			return false;
	}	
	
	/**
	 * return the hashCode of a CourseDBElement based on
	 * its CRN
	 */
	public int hashCode() {
		return Integer.valueOf(crn).hashCode();
	}
}
