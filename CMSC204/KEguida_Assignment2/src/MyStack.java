import java.util.ArrayList;

/** 
 * @author Kevin Eguida
 *
 */
public class MyStack<T> implements StackInterface<T>
{
	T[] AStack;
	int num_of_entries;
	final int MAX_CAPACITY;
	
	/** Default constructor that initializes 
	 * the max size with 5 items, with 20 being the default 
	 */
	public MyStack() 
	{
		this(20);
	}
	
	/** Constructor that takes in a size and creates a NotationStack of that size
	 * 
	 * @param size The size desired of the NotationStack
	 */
	public MyStack(int size) 
	{
		MAX_CAPACITY = size;
		AStack = (T[]) new Object[size];
		num_of_entries=-1;
	}

	/** Checks if the Stack is empty by checking if the 
	 * number of elements is 0.
	 */
	@Override
	public boolean isEmpty() {
		if (num_of_entries==-1) return true; else return false;
	}

	/** Checks if the stack is full by checking if the 
	 * number of elements is equal to the max size 
	 */
	@Override
	public boolean isFull() {
		if (num_of_entries+1==MAX_CAPACITY) return true; else return false;
	}

	/** Removes the last item inserted into the stack and
	 *  returns it
	 * 
	 * @return The item removed from the top of the stack
	 * @throws StackUnderflowException  Occurs when you attempt to pop an item off of an empty Stack
	 */
	@Override
	public T pop() throws StackUnderflowException {
		
		if (isEmpty())
			throw new StackUnderflowException();
		
		T top = AStack[num_of_entries];
		AStack[num_of_entries]=null;
		--num_of_entries;
		
		return top;
	}

	/** Returns the topmost, or last item inserted into the stack
	 * 
	 * @return The last item inserted into the stack
	 * @throws StackUnderflowException Occurs when you attempt to check the top of an empty Stack
	 */
	@Override
	public T top() throws StackUnderflowException {
		
		return AStack[num_of_entries];
	}

	/** Returns the number of elements in the stack
	 * 
	 * @return The number of elements in the stack
	 */
	@Override
	public int size() {
	
		return num_of_entries+1;
	}

	/** Adds an item to the end of the stack
	 * 
	 * @param e The element you'd like to add to the Stack
	 * 
	 * @return True if the item is added to the stack
	 * @throws StackOverflowException Occurs when you attempt to push onto a full Stack
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		
		if (num_of_entries!=MAX_CAPACITY-1)
		{
			++num_of_entries;
			AStack[num_of_entries]=e;
			return true;
		}
		else throw new StackOverflowException();

	}
	
	/** Returns a string of the items in the Stack without a delimiter
	 * 
	 * @return A string of the items in the stack with no delimiter
	 */
	@Override
	public String toString() 
	{
		return toString("");
	}

	/** Returns a string of the items in the Stack with a delimiter, does
	 *  not add the extra delimiter to the end of the string
	 *
	 *	@param delimiter The string you'd like to use to split up each item in the stack
	 *
	 *  @return A string of the items in the stack split by the delimiter provided
	 */
	@Override
	public String toString(String delimiter) {
		String elements="";
		for (int i=0; i<(num_of_entries); i++)
		{
			elements += AStack[i] + delimiter;
		}
		return elements + AStack[num_of_entries];
	}

	/** fills up the stack with provided data
	 *
	 *	@param a list containing information to fill up the stack
	 *
	 */
	@Override
	public void fill(ArrayList<T> list) {
		
		for(int i=0; i<list.size(); i++)
		{
			push(list.get(i));
		}
		
	}

}
