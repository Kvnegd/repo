import java.util.ArrayList;

/**
 * 
 * @author Kevin Eguida
 *
 */

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	
	TreeNode<String> root= new TreeNode<String>("");
	
	/**
	 * constructor that creates a MorseCodeTree object and 
	 * builds the tree. Calls the build tree method.
	 */
	MorseCodeTree()
	{
		buildTree();
	}

	/**
	 * Returns a reference to the root
	 * @return reference to root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * sets the root of the Tree
	 * @param newNode a TreeNode<T> that will be the new root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		this.root=newNode;
	}

	/**
	 * Adds the letter to the correct position in the tree based on the Morse code
	 * This method will call the recursive method addNode
	 * @param code the code for the new node to be added
	 */
	@Override
	public void insert(String code, String letter) {
		addNode(this.root, code, letter);		
	}

	/**
	 * This is a recursive method that adds element to the correct position 
	 * in the tree based on the code.
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		String[] fcode= code.split("");
		String Lcode="";
		for(int i=1; i<fcode.length;i++)
		{
			Lcode += fcode[i];
		}
		if (fcode.length==1)
		{
			if(fcode[0].equals("."))
				root.leftChild= new TreeNode<String>(letter);
			else
				root.rightChild= new TreeNode<String>(letter);
		}
		else
		{
			if(fcode[0].equals("."))
				addNode(root.leftChild,Lcode,letter);
			else
				addNode(root.rightChild,Lcode,letter);
		}
		
	}
	
	/**
	 * This method builds the LinkedConverterTree by inserting TreeNodes<String letter>
	 * into their proper locations
	 * 
	 */
	@Override
	public void buildTree() {
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	/**
	 * Fetch the letter in the tree based on the Morse code
	 * This method will call the recursive method fetchNode
	 * 
	 * @param code the code that describes the traversals within the tree
	 * @return the letter that corresponds to the Morse code (the return type should be String)
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(this.root, code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode
	 * that corresponds with the code
	 * 
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of fetchNode
	 * @return the data corresponding to the code (the return type should be String)
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		String[] fcode= code.split("");
		String Lcode="";
		for(int i=1; i<fcode.length;i++)
		{
			Lcode += fcode[i];
		}
		if (fcode.length==1)
		{
			if(fcode[0].equals("."))
				return (String) root.leftChild.getData();
			else
				return (String) root.rightChild.getData();
		}
		else
		{
			if(fcode[0].equals("."))
				return fetchNode(root.leftChild,Lcode);
			else
				return fetchNode(root.rightChild,Lcode);
		}
	}

	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> LNRTree = new ArrayList();
		LNRoutputTraversal(this.root, LNRTree);
		return LNRTree;
	}

	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root!=null)
		{
			LNRoutputTraversal(root.leftChild,list);
			list.add(root.getData());
			LNRoutputTraversal(root.rightChild,list);
		}
	}
	
	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @param data data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {return null;}

	/**
	 * This operation is not supported for a LinkedConverterTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {return null;}

}
