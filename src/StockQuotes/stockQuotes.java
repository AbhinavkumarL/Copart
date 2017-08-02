package StockQuotes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.sound.sampled.Line;

public class stockQuotes {
	LinkedList<String> list;
	File file;
	boolean decrease;
	boolean increase;
	
	public void loadStocks() throws IOException{
		String line;
		file = new File("/Users/Abhinav/Desktop/stock.txt");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader (fr);
		list=new LinkedList();
		
		while((line = br.readLine()) != null)  
        {
			
			 list.add(line);
        }
	}
	
	public void printStarts(){
		int x;
		for( String s: list){
			x=(int)Double.parseDouble(s);
			for (int i =0; i<x; i++ ){
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void patternDetection(){
		
		System.out.println(Double.parseDouble(list.get(0)));
		System.out.println(Double.parseDouble(list.get(1)));
		System.out.println(Double.parseDouble(list.get(2)));
//		for (int i =2; i <list.size();i++){
//			if ( Double.parseDouble(list.get(i)) > Double.parseDouble(list.get(i-1))){
//				if (Double.parseDouble(list.get(i-1)) > Double.parseDouble(list.get(i-2))){
//					System.out.println(Double.parseDouble(list.get(i))+" BUY");
//				}else {
//					System.out.println(Double.parseDouble(list.get(i)));
//				}
//			}else if ( Double.parseDouble(list.get(i)) < Double.parseDouble(list.get(i-1)) ){
//				if (Double.parseDouble(list.get(i-1)) < Double.parseDouble(list.get(i-2))){
//					System.out.println(Double.parseDouble(list.get(i))+" SELL");
//				}else {
//					System.out.println(Double.parseDouble(list.get(i)));
//				}
//			}
//				
//		}
		
		for (int i=3; i <list.size()-1;i++){
			
			if ( Double.parseDouble(list.get(i)) > Double.parseDouble(list.get(i-1))){
				if (Double.parseDouble(list.get(i-1)) > Double.parseDouble(list.get(i-2))){
					if (Double.parseDouble(list.get(i-2)) < Double.parseDouble(list.get(i-3))){
						System.out.println(Double.parseDouble(list.get(i))+" BUY");
					}else {
						System.out.println(Double.parseDouble(list.get(i)));
					}
				}else {
					System.out.println(Double.parseDouble(list.get(i)));
				}
				
			}else if ( Double.parseDouble(list.get(i)) < Double.parseDouble(list.get(i-1)) ){
				
				if (Double.parseDouble(list.get(i-1)) < Double.parseDouble(list.get(i-2))){
					if (Double.parseDouble(list.get(i-2)) > Double.parseDouble(list.get(i-3))){
						System.out.println(Double.parseDouble(list.get(i))+" SELL");
					}else {
						System.out.println(Double.parseDouble(list.get(i)));
					}
				}else {
					System.out.println(Double.parseDouble(list.get(i)));
				}
				
			}	
		}
	}
	
	public static void main(String [] args) throws IOException{
		stockQuotes sq = new stockQuotes();
		sq.loadStocks();
		sq.printStarts();
		sq.patternDetection();
	}
}
