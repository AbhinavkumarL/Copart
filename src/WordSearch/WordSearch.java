package WordSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;



/**
 * This is used to find the different possible words from the grid and compare with the dictionary words
 * @author Abhinav
 */
 class AvlTree<AnyType extends Comparable<? super AnyType>>
	{
	 
	  /** The tree root. */
	    private AvlNode<AnyType> root;
	    /**
	     * Construct the tree.
	     */
	    public AvlTree( )
	    {
	        root = null;
	    }

	    /**
	     * Insert into the tree; duplicates are ignored.
	     * @param x the item to insert.
	     */
	    public void insert( AnyType x )
	    {
	        root = insert( x, root );
	    }

	    /**
	     * Remove from the tree. Nothing is done if x is not found.
	     * @param x the item to remove.
	     */
	    public void remove( AnyType x )
	    {
	        root = remove( x, root );
	    }

	       
	    /**
	     * Internal method to remove from a subtree.
	     * @param x the item to remove.
	     * @param t the node that roots the subtree.
	     * @return the new root of the subtree.
	     */
	    private AvlNode<AnyType> remove( AnyType x, AvlNode<AnyType> t )
	    {
	        if( t == null )
	            return t;   // Item not found; do nothing
	            
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            t.left = remove( x, t.left );
	        else if( compareResult > 0 )
	            t.right = remove( x, t.right );
	        else if( t.left != null && t.right != null ) // Two children
	        {
	            t.element = findMin( t.right ).element;
	            t.right = remove( t.element, t.right );
	        }
	        else
	            t = ( t.left != null ) ? t.left : t.right;
	        return balance( t );
	    }
	    
	    /**
	     * Find the smallest item in the tree.
	     * @return smallest item or null if empty.
	     * @throws InterruptedException 
	     */
	    public AnyType findMin( ) throws InterruptedException
	    {
	        if( isEmpty( ) )
	            throw new InterruptedException( );
	        return findMin( root ).element;
	    }

	    /**
	     * Find the largest item in the tree.
	     * @return the largest item of null if empty.
	     * @throws InterruptedException 
	     */
	    public AnyType findMax( ) throws InterruptedException
	    {
	        if( isEmpty( ) )
	            throw new InterruptedException( );
	        return findMax( root ).element;
	    }

	    /**
	     * Find an item in the tree.
	     * @param x the item to search for.
	     * @return true if x is found.
	     */
	    public boolean contains( AnyType x )
	    {
	        return contains( x, root );
	    }

	    /**
	     * Make the tree logically empty.
	     */
	    public void makeEmpty( )
	    {
	        root = null;
	    }

	    /**
	     * Test if the tree is logically empty.
	     * @return true if empty, false otherwise.
	     */
	    public boolean isEmpty( )
	    {
	        return root == null;
	    }

	    /**
	     * Print the tree contents in sorted order.
	     */
	    public void printTree( )
	    {
	        if( isEmpty( ) )
	            System.out.println( "Empty tree" );
	        else
	            printTree( root );
	    }

	    private static final int ALLOWED_IMBALANCE = 1;
	    
	    // Assume t is either balanced or within one of being balanced
	    private AvlNode<AnyType> balance( AvlNode<AnyType> t )
	    {
	        if( t == null )
	            return t;
	        
	        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
	            if( height( t.left.left ) >= height( t.left.right ) )
	                t = rotateWithLeftChild( t );
	            else
	                t = doubleWithLeftChild( t );
	        else
	        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
	            if( height( t.right.right ) >= height( t.right.left ) )
	                t = rotateWithRightChild( t );
	            else
	                t = doubleWithRightChild( t );

	        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
	        return t;
	    }
	    
	    public void checkBalance( )
	    {
	        checkBalance( root );
	    }
	    
	    private int checkBalance( AvlNode<AnyType> t )
	    {
	        if( t == null )
	            return -1;
	        
	        if( t != null )
	        {
	            int hl = checkBalance( t.left );
	            int hr = checkBalance( t.right );
	            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
	                    height( t.left ) != hl || height( t.right ) != hr )
	                System.out.println( "OOPS!!" );
	        }
	        
	        return height( t );
	    }
	    
	    
	    /**
	     * Internal method to insert into a subtree.
	     * @param x the item to insert.
	     * @param t the node that roots the subtree.
	     * @return the new root of the subtree.
	     */
	    private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t )
	    {
	        if( t == null )
	            return new AvlNode<>( x, null, null );
	        
	        int compareResult = x.compareTo( t.element );
	        
	        if( compareResult < 0 )
	            t.left = insert( x, t.left );
	        else if( compareResult > 0 )
	            t.right = insert( x, t.right );
	        else
	            ;  // Duplicate; do nothing
	        return balance( t );
	    }

	    /**
	     * Internal method to find the smallest item in a subtree.
	     * @param t the node that roots the tree.
	     * @return node containing the smallest item.
	     */
	    private AvlNode<AnyType> findMin( AvlNode<AnyType> t )
	    {
	        if( t == null )
	            return t;

	        while( t.left != null )
	            t = t.left;
	        return t;
	    }

	    /**
	     * Internal method to find the largest item in a subtree.
	     * @param t the node that roots the tree.
	     * @return node containing the largest item.
	     */
	    private AvlNode<AnyType> findMax( AvlNode<AnyType> t )
	    {
	        if( t == null )
	            return t;

	        while( t.right != null )
	            t = t.right;
	        return t;
	    }

	    /**
	     * Internal method to find an item in a subtree.
	     * @param x is item to search for.
	     * @param t the node that roots the tree.
	     * @return true if x is found in subtree.
	     */
	    private boolean contains( AnyType x, AvlNode<AnyType> t )
	    {
	        while( t != null )
	        {
	            int compareResult = x.compareTo( t.element );
	            
	            if( compareResult < 0 )
	                t = t.left;
	            else if( compareResult > 0 )
	                t = t.right;
	            else
	                return true;    // Match
	        }

	        return false;   // No match
	    }

	    /**
	     * Internal method to print a subtree in sorted order.
	     * @param t the node that roots the tree.
	     */
	    private void printTree( AvlNode<AnyType> t )
	    {
	        if( t != null )
	        {
	            printTree( t.left );
	            System.out.println( t.element );
	            printTree( t.right );
	        }
	    }

	    /**
	     * Return the height of node t, or -1, if null.
	     */
	    private int height( AvlNode<AnyType> t )
	    {
	        return t == null ? -1 : t.height;
	    }

	    /**
	     * Rotate binary tree node with left child.
	     * For AVL trees, this is a single rotation for case 1.
	     * Update heights, then return new root.
	     */
	    private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
	    {
	        AvlNode<AnyType> k1 = k2.left;
	        k2.left = k1.right;
	        k1.right = k2;
	        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
	        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
	        return k1;
	    }

	    /**
	     * Rotate binary tree node with right child.
	     * For AVL trees, this is a single rotation for case 4.
	     * Update heights, then return new root.
	     */
	    private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
	    {
	        AvlNode<AnyType> k2 = k1.right;
	        k1.right = k2.left;
	        k2.left = k1;
	        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
	        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
	        return k2;
	    }

	    /**
	     * Double rotate binary tree node: first left child
	     * with its right child; then node k3 with new left child.
	     * For AVL trees, this is a double rotation for case 2.
	     * Update heights, then return new root.
	     */
	    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
	    {
	        k3.left = rotateWithRightChild( k3.left );
	        return rotateWithLeftChild( k3 );
	    }

	    /**
	     * Double rotate binary tree node: first right child
	     * with its left child; then node k1 with new right child.
	     * For AVL trees, this is a double rotation for case 3.
	     * Update heights, then return new root.
	     */
	    private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
	    {
	        k1.right = rotateWithLeftChild( k1.right );
	        return rotateWithRightChild( k1 );
	    }

	    private static class AvlNode<AnyType>
	    {
	            // Constructors
	        AvlNode( AnyType theElement )
	        {
	            this( theElement, null, null );
	        }

	        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
	        {
	            element  = theElement;
	            left     = lt;
	            right    = rt;
	            height   = 0;
	        }

	        AnyType           element;      // The data in the node
	        AvlNode<AnyType>  left;         // Left child
	        AvlNode<AnyType>  right;        // Right child
	        int               height;       // Height
	    }

 }

 
 /****************************** PUBLIC CLASS FOR WORD PUZZLE ******************/
public class WordSearch {
	char[][] grid=null;
	int countLL=0;
	int countAVL=0;
	File file;
	LinkedList<String> list;
	AvlTree<String> list1;
	
	public void loadDictionaryLL() throws IOException{
		String line;
		file=new File("/Users/Abhinav/Desktop/words.txt");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader (fr);
		list=new LinkedList<String>();
		 while((line = br.readLine()) != null)  
         {
			 list.add(line);
         }
	}
	
	public void loadDictionaryAvlTree() throws IOException{
		String line;
		file=new File("/Users/Abhinav/Desktop/words.txt");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader (fr);
		list1=new AvlTree<String>();
		 while((line = br.readLine()) != null)  
         {
			 list1.insert(line);
         }
	}
	public void compDictLL(){
		countLL++;
	}
	public void compDictAvlTree(){
		countAVL++;
	}
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	public char[][] createGrid(int i, int j){
		 grid = new char[i][j];
		 Random rand=new Random();
		 Scanner s = new Scanner(System.in);
		 
		 for (int x=0;x<i;x++){
			 for (int y=0;y<j;y++){
				 grid[x][y]=(char)(97+rand.nextInt(26));
//				 grid[x][y] = s.next().charAt(0);
			 }
		 }
		 return grid; 
	}
	public void printGrid(){
		
		System.out.println("The Grid you just created is :");
		 for (int x=0;x<grid.length;x++){
			 for (int y=0;y<grid[x].length;y++){
				 System.out.print(" "+grid[x][y]+" ");
			 }
			 System.out.println(" ");
		 }
	}
	/*
	 * This method will get the element specified by the position
	 */
	public char getElement(int row, int col){
		
		return grid[row][col];
		
	}
	
	public String findWords(int row, int col, int orient ){
		String word="";
		switch(orient) {
		case 1:
			// going towards right
			for(int i=col;i< grid[0].length;i++){
				word += getElement(row, i);
				//if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		case 2:
			//going  right-down-diagonally
			for(int i=row, j=col;i< grid.length && j<grid[0].length;i++,j++){
				word += getElement(i,j);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		case 3:
			//going down vertically
			for(int i=row;i< grid.length ;i++){
				word += getElement(i,col);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
			
		case 4:
			//going left-down-diagonally

			for(int i=row,j=col;i< grid.length && j>=0 ;i++,j--){
				word += getElement(i,j);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		case 5:
			//going left horizontal
			for(int j=col;j>=0 ;j--){
				word += getElement(row,j);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		case 6:
			//going left-up-diagonally
			for(int i=row,j=col;i>=0 && j>=0 ;i--,j--){
				word += getElement(i,j);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		case 7:
			// going up vertically
			for(int i=row;i>=0 ;i--){
				word += getElement(i,col);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		case 8:
			// going up-right-diagonally
			for(int i=row,j=col;i>=0 && j<grid[0].length ;i--, j++){
				word += getElement(i,j);
				if ( word.length()==1) continue;
				//System.out.println(word);
			}
			break;
		}
//			if ( word.length()>=4){
//				return word;
//			}
			return word;
		}
 	}
 
 
