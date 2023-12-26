/**
 * 
 * @author Kevin Eguida
 *
 */

public class TreeNode<T> {
	
	TreeNode leftChild;
	TreeNode rightChild;
	private T data;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode is the data attached to the node
	 */
	TreeNode(T dataNode)
	{
		leftChild=null;
		rightChild=null;
		data=dataNode;
	}
	
	/**
	 * Used to make deep copies
	 * @param node is the node to copy
	 */
	TreeNode(TreeNode<T> node)
	{
		leftChild=node.leftChild;
		rightChild=node.rightChild;
		data=node.data;
	}
	
	/**
	 * Return the data within this TreeNode
	 * @return the data within this TreeNode
	 */
	public T getData() {
		return data;
	}

}
