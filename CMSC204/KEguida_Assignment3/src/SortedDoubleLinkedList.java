import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * 
 * @author Kevin Eguida
 *
 */

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable
{
	Comparator<T> comparator;
	
	/**
	 * Creates an empty list that is associated 
	 * with the specified comparator.
	 * @param comparableObject
	 */
	public SortedDoubleLinkedList(Comparator<T> comparableObject)
	{
		super();
		comparator=comparableObject;
	}
	
	/**
	 * Inserts the specified element 
	 * at the correct position in the sorted list.
	 * @param data
	 */
	protected void add(T data) 
	{
		Node newNode = new Node(data);
		
		if (size==0) {
			firstNode= newNode;
			lastNode=newNode;
			size++;
		}
		else 
		{
			if(comparator.compare((T) firstNode.data,data)>0) 
			{
				super.addToFront(data);
			}
			else if(comparator.compare((T) lastNode.data,data)<0) 
			{
				super.addToEnd(data);
			}
			else
			{
				Node current=firstNode;
				while(current!=null) 
				{
					if(comparator.compare(data,(T) current.data)<0) {
							newNode.prev=current.prev;
							newNode.next=current;
							current.prev.next=newNode;
							current.prev=newNode;
							size++;
							break;
					}
					current=current.next;
				}
			}
		}
			
	}
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	@Override
	protected void addToFront(T data){throw new UnsupportedOperationException();}
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	@Override
	protected void addToEnd(T data){throw new UnsupportedOperationException();}

	/**
	 * Implements the iterator 
	 * by calling the super class iterator method.
	 */
	@Override
	public ListIterator iterator() {
		return super.iterator();
	}
	
	/**
	 * Implements the remove operation 
	 * by calling the super class remove method.
	 */
	@Override
	public Node remove(T data, Comparator<T>comparator)
	{
		return super.remove(data, comparator);
	}

}
