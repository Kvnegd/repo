import java.util.ArrayList;

/**    
 *  @author Kevin Eguida
 *
 */
public class MyQueue<T> implements QueueInterface<T>
{
	private T[] AQueue;
	private int num_of_elements;
	private int frontIndex;
	private int BackIndex;
	private final int MAX_CAPACITY;
	
	/** Default Constructor that creates a default NotationQueue 
	 *  with 10 elements max, a default number */
	public MyQueue() {this(10);}
	
	/** Constructor that creates a NotationQueue with a specified number
	 *  of elements
	 * 
	 * @param size The number of max elements you want the NotationQueue to contain
	 */
	public MyQueue(int size)
	{
		MAX_CAPACITY=size;
		AQueue = (T[]) new Object[MAX_CAPACITY];
		frontIndex=0;
		BackIndex=-1;
		num_of_elements = 0;
	}
	
	/** Checks if the NotationQueue is empty or not
	 * 
	 * @return True if the queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return num_of_elements==0;
	}
	
	/** Checks if the NotationQueue is full or not
	 * 
	 * @return True if the queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		return num_of_elements==MAX_CAPACITY;
	}

	/** Removes the bottom most element of the NotationQueue, or in other
	 *  words, the first item that was put in the queue. When something is removed
	 *  the first item is the next item that was put in.
	 * 
	 * @throws QueueUnderflowException Occurs if you try to dequeue when the queue is empty
	 * @return The element that was removed
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
	
		if (isEmpty())
			throw new QueueUnderflowException();
		
		T front = AQueue[frontIndex];
		AQueue[frontIndex]=null;
		
		frontIndex = (frontIndex+1)%MAX_CAPACITY;
		--num_of_elements;
		return front;
	}

	/** Checks the number of elements in the queue
	 * 
	 * @return The number of elements in the queue
	 */
	@Override
	public int size() {
		return num_of_elements;
	}

	/** Adds an element to the end of the queue
	 * 
	 * @param e The element you want to enqueue
	 * 
	 * @throws QueueOverflowException Occurs if you try to enqueue when the queue is full
	 * @return True of the element was enqueued
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		
		if (num_of_elements==MAX_CAPACITY)
			throw new QueueOverflowException();
		
		AQueue[BackIndex+1]=e;
		BackIndex = (BackIndex+1)%MAX_CAPACITY;
		num_of_elements++;
		
		return true;
	}
	
	/** Gets the items in the queue and shows it without a delimiter
	 * 
	 * @return A string of the data in the queue, with no delimiter
	 */
	@Override
	public String toString() {

		return toString("");
	}


	/** Gets the items in the queue and shows it with delimiter passed through
	 * 
	 * @param delimiter The delimiter you want the data separated by
	 * 
	 * @return A string of the data in the queue, separated by the delimiter passed through
	 */

	@Override
	public String toString(String delimiter) {

		String elements="";
		int index = frontIndex;
		
		while(index!=BackIndex) {
			elements += AQueue[index] + delimiter;
			index=(index+1)%MAX_CAPACITY;
		}
		
		return elements + AQueue[BackIndex];
	}

	/** fills up the queue with provided data
	 *
	 *	@param a list containing information to fill up the queue
	 *
	 */
	@Override
	public void fill(ArrayList<T> list) {
		
		for(int i=BackIndex+1; i<list.size(); i++)
		{
			enqueue(list.get(i));
		}		
	}

}
