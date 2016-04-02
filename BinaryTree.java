/**
 * This class is used to demonstrate the operation going on BinaryTree
 * using various operator and reads a file and store the word in a Binary 
 * Search tree and counts the frequency of the word, also display the least
 * used word in the the following different method.
 */

package trees;
import java.util.NoSuchElementException; //Throws element not found exception


public class BinaryTree<T extends Comparable<? super T>> {

	/**
	 * This private method creates the node that we need for the binary search tree
	 * @param <T> is a generic class creates the nodes for the tree and variable 
	 * needed for the method.
	 */

	private static class node<T extends Comparable<? super T>> {

		T data;									// Generic class data type
		public static int numOfNodes;			// Total number of stored in node
		node<T> leftChild; 						// Creating a leftChild node for tree
		node<T> rightChild; 					// Creating a rightChild node for tree
		public int value;						// Value stored in the node


		/**
		 * Creating a instant of constructor class the parameter inserted is stored in the
		 * variable data, leftChild and rightChild node is set to null.
		 * Total number of nodes is set to zero 
		 */
		node(T theElement) { 
			data = theElement; 
			leftChild = rightChild = null; 
			numOfNodes=0;
		}
	}

	private node<T> root; 						//Creates a root of BST

	/**
	 * This method is creating the instant class and root set to null.
	 */

	public BinaryTree() {
		root = null;
	}


	public static<T extends Comparable<? super T>> BinaryTree<T> BSTree() {
		return new BinaryTree<T>();
	}

	/**
	 * This method adds the value of given to the given node in the insert method 
	 * recursively.
	 */

	public void insert(T value) { 
		root = insert(value, root);
	}

	/**
	 * This method removes the value of given to the given node in the remove method 
	 * recursively.
	 */

	public void remove(T value) {
		root = remove(value, root);
	}

	/**
	 * This method checks the value whether it contains or not in to the tree and returns
	 * the boolean value.
	 */

	public boolean contains(T value) {
		return valueOf(find(value, root)) != null;
	}

	private T valueOf(node<T> entry) {
		if(entry == null)
			return null;
		else 
			return entry.data;
	}

	/**
	 * This method enter the String value in to the node on BST.
	 */

	private node<T> insert(T value, node<T> entry) {
		if (entry == null)
			entry = new node<T>(value);
		else if (value.compareTo(entry.data) < 0)
			entry.leftChild = insert(value, entry.leftChild);
		else if (value.compareTo(entry.data) > 0)
			entry.rightChild = insert(value, entry.rightChild);
		else
			throw new RuntimeException("Duplicate Entry : " + value.toString());
		node.numOfNodes++;
		return entry;


	}

	/**
	 * This method remove the element from the tree. 
	 *  Replace with in-order successor (the leftChild-most child of the rightChild subtree)
	 *  Replace with in-order predecessor (the rightChild-most child of the leftChild subtree) 
	 */

	private node<T> remove(T value, node<T> entry) { 
		if (entry == null)
			throw new NoSuchElementException("Entry not found : " + value.toString());
		if (value.compareTo(entry.data) < 0)
			entry.leftChild = remove(value, entry.leftChild);
		else if (value.compareTo(entry.data) > 0)
			entry.rightChild = remove(value, entry.rightChild);
		else {
			// Entry found.
			if (entry.leftChild != null && entry.rightChild != null) {


				entry.data = findMin(entry.rightChild).data;
				entry.rightChild = removeInorderSuccessor(entry.rightChild);


			} else
				entry = (entry.leftChild != null) ? entry.leftChild : entry.rightChild;
		}

		return entry;
	}

	/**
	 * This method removes the parent node of the deleted node
	 * @param entry the element entered in the nodes
	 * @return the rightChild node.
	 */

	private node<T> removeInorderSuccessor(node<T> entry) {
		if (entry == null)
			throw new NoSuchElementException();
		else if (entry.leftChild != null) {
			entry.leftChild = removeInorderSuccessor(entry.leftChild);
			return entry;
		} else
			return entry.rightChild;
	}

	/**
	 * This method removes the child node of the deleted node
	 * @param entry the element entered in the nodes
	 * @return the leftChild node.
	 */

	private node<T> removeInorderPredecessor(node<T> entry) { 
		if (entry == null)
			throw new NoSuchElementException();
		else if (entry.rightChild != null) {
			entry.rightChild = removeInorderPredecessor(entry.rightChild);
			return entry;
		} else
			return entry.leftChild;
	}

	/**
	 * This method finds the minimum value exist in the BST
	 * @param entry the element entered in the nodes
	 * @return entry - minimum one.
	 */

	private node<T> findMin(node<T> entry) { 
		if (entry != null)
			while (entry.leftChild != null)
				entry = entry.leftChild;

		return entry;
	}

	/**
	 * This method finds the maximum value exist in the BST
	 * @param entry the element entered in the nodes
	 * @return entry - maximum one.
	 */

	private node<T> findMax(node<T> entry) { 
		if (entry != null)
			while (entry.rightChild != null)
				entry = entry.rightChild;

		return entry;
	}

	/**
	 * This method search the particular value exist in the binary search tree.
	 * @param value generic data 
	 * @param entry the element stored in the nodes
	 * @return entry element.
	 */
	public node<T> find(T value, node<T> entry) { 
		while (entry != null) { 
			if (value.compareTo(entry.data) < 0)
				entry = entry.leftChild;
			else if (value.compareTo(entry.data) > 0)
				entry = entry.rightChild;
			else
				return entry;
		}

		return null;
	}

	public int count=0;					 					
	public static String[] anArray=new String[189509];

	/**
	 * This method prints the value of tree and checks the tree empty or not 
	 * and stores into an array the elements from the tree
	 * @param entry the elements stored on to the nodes.
	 */

	private void printInOrder(node<T> entry) { 
		if (entry != null) { 
			printInOrder(entry.leftChild); 
			System.out.println(entry.data);
			anArray[count]=(String) entry.data;  
			count++;
			printInOrder(entry.rightChild);

		}

	}

	/**
	 * This method display the element stored in to the above method.
	 */

	public void displayArray(){
		for(int i=0;i<anArray.length;i++){
			System.out.print(" "+anArray[i]);
		}
	}

	/**
	 * This method prints the element stored in to the above method
	 * and recursively calls the method.
	 */

	public void printInOrder() { 
		printInOrder(root);

	}

}
