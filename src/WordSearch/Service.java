package WordSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Service {
	 
	public static void main(String[] args) throws IOException {
		
		WordSearch ws= new WordSearch();
		
		Scanner s=new Scanner(System.in); // getting in puts from user to create an grid
		System.out.println("Enter the number of Rows?");
        int rows = s.nextInt();
        System.out.println("Enter the number of Colums?");
        int cols = s.nextInt();
		
        char grid[][]=ws.createGrid(rows, cols);
        ws.printGrid();
        ws.setGrid(grid);
        System.out.println("loading the dictionary to Linked List");
        ws.loadDictionaryLL();
        System.out.println("loading the dictionary to AVL BinaryTree");
        ws.loadDictionaryAvlTree();

        System.out.println("Load Completed....");
        System.out.println("Searching for possible words from the grid created to compare");
        
        long startTime , endTime;
        startTime=System.currentTimeMillis();
        System.out.println("Start timer for LinkeList:="+ startTime);
        
        for (int r=0; r<rows;r++){
        	for(int c=0; c<cols;c++){
        		for (int i=1; i<=8; i++){
        			String x = ws.findWords(r, c, i);
        			if (x.length()>=4){
        				if (ws.list.contains(x)){
    						ws.compDictLL();
        				}
        			}
        		}

			}
		}
        endTime = System.currentTimeMillis();
        System.out.println("End timer for LinkeList:="+ endTime);
        System.out.println("Time consumed for LinkedList:="+ (endTime - startTime));
        
        startTime=System.currentTimeMillis();
        System.out.println("\nStart time for AVL Tree:="+ startTime);
        
        for (int r=0; r<rows;r++){
        	for(int c=0; c<cols;c++){
        		for (int i=1; i<=8; i++){
        			String x = ws.findWords(r, c, i);
        			if (x.length()>=4){
        				if (ws.list.contains(x)){
        					ws.compDictAvlTree();
        				}
        			}
//    				if (ws.list1.contains(ws.findWords(r, c, i))){
//    						ws.compDictAvlTree();
//    				}
        		}

			}
		}
        endTime = System.currentTimeMillis();
        System.out.println("End timer for AVLTree:="+ endTime);
        System.out.println("Time consumed for AVLTree:="+ (endTime - startTime));
        System.out.println();
        
        System.out.println(" ");
        System.out.println("The Puzzle has "+ws.countLL+" words listed in the Dictionary in Linked List");
        System.out.println("The Puzzle has "+ws.countAVL+" words listed in the Dictionary in AVL binary Search tree");
        }
	
}

