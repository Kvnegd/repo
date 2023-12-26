import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Kevin Eguida
 *
 */

public class BasicDoubleLinkedList<T>
{
	protected int size;
	protected Node firstNode, lastNode;
	
	/** Constructor that creates an empty BasicDoubleLinkedList 
	 */
	protected BasicDoubleLinkedList()
	{
		size=0;
		firstNode=null;
		lastNode=null;
	}
	
	/**Returns the number of nodes in the list.
	 */
	protected int getSize() {return size;}
	
	/**Returns but does not remove the first element from the list.
	 */
	protected T getFirst() {return (T)firstNode.data;}
	
	/**Returns but does not remove the last element from the list. 
	 */
	protected T getLast() {return (T)lastNode.data;}
	
	/**Removes and returns the first element from the list.
	 */
	protected T retrieveFirstElement()
	{		
		Node temp=firstNode;
		firstNode=firstNode.next;
		size--;
		return (T) temp.data;
	}
	
	/**
	 * Removes and returns the last element from the list.
	 */
	protected T retrieveLastElement()
	{
		Node temp=lastNode;
		lastNode=lastNode.prev;
		size--;
		return (T) temp.data;
	}
	
	/**
	 *Adds element to the front 
	 *of the list and updated the size of the list
	 *@param the data to add to the list
	 */
	protected void addToFront(T data)
	{
		Node newNode = new Node(data);
		if (size==0) {
			firstNode= newNode;
			lastNode=newNode;
			size++;
		}
		else {
			firstNode.prev=newNode;
			newNode.next=firstNode;
			firstNode=newNode;
			size++;
		}
		
	}
	
	/**
	 *Adds an element to the end 
	 *of the list and updated the size of the list
	 *@param the data to add to the list
	 */
	protected void addToEnd(T data)
	{
		Node newNode = new Node(data);
		if (size==0) {
			lastNode= newNode;
			firstNode= newNode;
			size++;
		}
		else {
			newNode.prev=lastNode;
			lastNode.next=newNode;
			lastNode=newNode;
			size++;
		}
	}
	
	/**
	 * This method returns 
	 * an object of the DoubleLinkedListIterator.
	 * @throws NoSuchElementException
	 */
	protected ListIterator<T> iterator() throws NoSuchElementException{return new DoubleLinkedListIterator();}
	
	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 */
	protected ArrayList<T> toArrayList()
	{
		ArrayList<T> myList = new ArrayList<T>();
		DoubleLinkedListIterator<T> iterator = 
		(BasicDoubleLinkedList<T>.DoubleLinkedListIterator<T>) iterator();
		
		while(iterator.hasNext()) {
			myList.add(iterator.next());	
		}	
		
		return myList;
	}
	
	/**
	 * 
	 * @param targetData the data that you wish to remove
	 * @param comparator A comparator object that specifies 
	 * what characteristics of two objects make them equal.
	 * @return
	 */
	protected Node remove(T targetData, Comparator<T> comparator) 
	{
		Node current=firstNode;
		Node previous=null;
		Node removed=null;
		
		while(current!=null) {
			
			if(comparator.compare(targetData,(T) current.data)==0) {
				if(size==1) {
					removed=firstNode;
					firstNode=null;
					lastNode=null;
					size--;
					break;
				}
				else if(current==firstNode) {
					removed=current;
					firstNode.next.prev=null;
					firstNode=firstNode.next;
					size--;
					break;
				}
				else if(current==lastNode){
					removed=current;
					lastNode.prev.next=null;
					lastNode=lastNode.prev;
					size--;
					break;
				}
				else {
					removed=current;
					current.prev.next=current.next;
					current.next.prev=current.prev;
					size--;
					break;
				}
			}
			previous=current;
			current=current.next;
		}

		return removed;
	}
	
	/**
	 * 
	 * @author Kevin Eguida
	 *A generic inner class Node 
	 * @param <T>
	 */
	protected class Node<T>
	{
		T data;
		Node prev;
		Node next;
		
		/**
		 * Constructor that creates an unattached node with a specified data
		 * @param nodeData data to link to the node
		 */
		protected Node(T nodeData)
		{
			data= nodeData;
			prev=next=null;
		}		
	}

	/**
	 * 
	 * @author Kevin Eguida
	 *A generic inner class named DoubleLinkedListIterator that 
	 *implements java’s ListIterator interface (for the iterator method)
	 * @param <T>
	 */
	protected class DoubleLinkedListIterator<T> implements ListIterator<T>
	{
		protected Node cursor;
		protected boolean previousAfterNext=false;
		protected boolean NextAfterPrevious=false;
		
		/**
		 * Constructor to initialize the current 
		 * pointer to the head of the BasicDoubleLinkedList.
		 */
		protected DoubleLinkedListIterator()
		{
			cursor=new Node(null);
			cursor.next=firstNode;
		}

		/**
		 * checks if there's still elements after the pointer in the list
		 */
		@Override
		public boolean hasNext() {
			
			return cursor.next!=null;
		}

		/**
		 * advances in the iterator
		 */
		@Override
		public T next() throws NoSuchElementException{
			T result;
			if (hasNext())
			{
				if(NextAfterPrevious) 
				{
					NextAfterPrevious=false;
					return (T) cursor.data;
				}
				result = (T) cursor.next.data;
			    cursor = cursor.next; // Advance iterator
			    if(cursor.next==null)
			    {
			    	cursor=new Node(null);
					cursor.prev=lastNode;
					 previousAfterNext=false;
					return result;
			    }
			    previousAfterNext=true;
			    return result; // Return next entry in iteration
			}
			throw new NoSuchElementException();
		}

		/**
		 * checks if there's still elements before the pointer in the list
		 */
		@Override
		public boolean hasPrevious(){
			return cursor.prev!=null;
		}

		/**
		 * goes back in the iterator
		 */
		@Override
		public T previous() throws NoSuchElementException{
			T result;
			if (hasPrevious())
			{
				if(previousAfterNext) 
				{
					previousAfterNext=false;
					return (T) cursor.data;
				}
				result = (T) cursor.prev.data;
			    cursor = cursor.prev; // Advance iterator
			    if(cursor.prev==null)
			    {
			    	cursor=new Node(null);
			    	cursor.next=firstNode;
			    	NextAfterPrevious=false;
			    	return result;
				}
			    NextAfterPrevious=true;
			    return result; // Return next entry in iteration
			}
			throw new NoSuchElementException();
		}

		/**
		 * Unsupported operation
		 */
		@Override
		public int nextIndex() {throw new UnsupportedOperationException();}

		/**
		 * Unsupported operation
		 */
		@Override
		public int previousIndex(){throw new UnsupportedOperationException();}

		/**
		 * Unsupported operation
		 */
		@Override
		public void remove(){throw new UnsupportedOperationException();}

		/**
		 * Unsupported operation
		 */
		@Override
		public void set(Object e){throw new UnsupportedOperationException();}

		/**
		 * Unsupported operation
		 */
		@Override
		public void add(Object e){throw new UnsupportedOperationException();}
		
	}

}

