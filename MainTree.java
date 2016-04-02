//************************************************************************************
//Program: Binary Search Tree 
//Date: 04-27-15
//Author: Tark Raj Ojha 
//Purpose: The goal will be to parse a large text file and determine the word frequency
//		   of every word in the text file. You will use file input to read in, analyze
//		   and output statistics about the file. You should provide an adequate user 
//  	   interface to accomplish the following tasks:
//*************************************************************************************
package trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader; //All the pakages the program uses
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MainTree {
	
	/**
	 * This method checks the validity of the String 
	 * @param element
	 * @return boolean value.
	 */

	private static boolean isValid(String element){
		for(int i=0; i<element.length();i++){
			if((element.charAt(i)>=65 && element.charAt(i)<=90) || (element.charAt(i)>=97 && element.charAt(i)<=122))
				return true;
			else return false;
		}
		return true;
	}

/**
 * This is the main program which linked all the object class of  binaryTree class and and implement 
 * as follows. 
 * @param args - needed for the class.
 * @throws IOException throws the file not found exeception.
 */
	public static void main(String[] args)throws IOException {
		
		// Creating a Scanner class.
		Scanner scanD=new Scanner(System.in);
		
		int list[] = new int[15]; 							// Creates an Array
		int numOfwords=0; 									// Declares a variable to count the total nodes
		int repeatedWords=0; 								// Keeps the count of the repeated counts
		int frequency=0; 									// Tracks the number of frequency of the selected word
		String[] RepeatedWordList=new String[189509];	    // String of repeated words
		BinaryTree tree = BinaryTree.BSTree();              // Instance of the class BST
		Scanner scan=null;									// Set Scanner scan null
		String delim="! @#$%^&*()_+-=/*-+.,.;[]\\<>?:{}\""; // Checks to see the word contains any of the following characters
		File file = new File("binarytree.txt"); 			// Reads the file called data
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found."); 
		}


		StringTokenizer tok; 								//Tokenizer to separate the each word in aline from the text file
		
		// While there is more lines in the array
		while(scan.hasNextLine()){
			tok=new StringTokenizer(scan.nextLine(),delim);
			
			// While there is more words left in the sentences 
			while (tok.hasMoreElements())
			{
				//Changes everything to lowercase
				String nextword = tok.nextToken().toLowerCase(); 

				if(isValid(nextword)){ 
					numOfwords++; 
					//If duplicate found go and increase that duplicates word count
					if(tree.contains(nextword)){
						System.out.println("NA");

						RepeatedWordList[repeatedWords]=nextword;
						repeatedWords++;
						
					}
					else{
						tree.insert(nextword);
					}

					
				}
			}

		}
		
		//Prints the inorder traversal
		tree.printInOrder(); 


		System.out.println("Total Num of Words in the given datalist:"+numOfwords);

		System.out.println("Please, Enter a word, I will find the frequency of it:");
		String inputWord=scanD.nextLine();

		//Checks if the tree has the value
		
		if(tree.contains(inputWord)){ 
			for(int i=0;i<repeatedWords;i++){
				String currWord=RepeatedWordList[i]; 
				if(currWord.equals(inputWord)) 
					++frequency;
			}
			System.out.println("The word is repeated "+ (frequency+1)+" times");
		}
		for(int i=1;i<499;i++){ 
			String uniqueWords = null;
			for(int j=1;j<repeatedWords;j++){
				if(tree.anArray[i]!=RepeatedWordList[j]){
					uniqueWords=tree.anArray[i];
				}

			}
			System.out.print("   Unique Words: "+uniqueWords);

		}




		System.out.println("\n \n PLEASE WAIT WHILE THE PROGRAM IS LOADING");

		
		int count2 = 0; 						//The variables that is going to be used in the method below
		int count1 = 0;
		String pupular1 =null;
		String popular2 =null;


		for (int i = 0; i < repeatedWords; i++) 
		{
			pupular1 = RepeatedWordList[i];
			count1 = 1;    //see edit

			for (int j = i + 1; j < repeatedWords; j++)
			{
				if (pupular1 == RepeatedWordList[j]) count1++;
			}

			if (count1 > count2) 
			{
				popular2 = pupular1;
				count2 = count1;
			}

			else if(count1 == count2)
			{
				if(popular2.compareToIgnoreCase(pupular1)<0){

					popular2 = popular2;
				}    
				else popular2=pupular1;

			}


		}
		System.out.println();
		//Creates two local variable popular and displays the most popular word in the list.
		System.out.println("MOST FREQUENT WORD: "+popular2); 

		System.out.println("\n The List of words in Sorted order:");
		
		//The following displays the list of words in sorted order.
		for(int i=0;i<1895;i++){

			System.out.print(" "+tree.anArray[i]);
		}



		System.out.println("\n \n PLEASE WAIT WHILE THE PROGRAM IS LOADING");
		System.out.println("\n \n THE LIST GIVES THE FREQUENCY LIST OF THE WORDS IN THE BST");

		int[] countList=new int[1000]; 			//Initializes the variables that is going to be used in the next method
		String[] frequentWordList=new String[1000];
		
		
		for(int i=0;i<1000;i++){
			int counter=0;
			String currWord=tree.anArray[i];
			for(int j=0;j<repeatedWords;j++){
				if(currWord.equalsIgnoreCase(RepeatedWordList[j])){
					counter++;
				}
				countList[i]=counter;
			}

			frequentWordList[i]=currWord;
		}
		int min=0,index=0;
		for(int i=0;i<1000;i++){
			min=countList[i];

			index=i;

			System.out.println("The frequency of word "+frequentWordList[index]+" is Repeated "+ countList[index]+" times");
		}


	}
}
