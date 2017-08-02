package Redundancy;

import java.util.HashMap;
import java.util.Scanner;

public class LongestRepeated {
	String input ;
	static HashMap<String, Integer> map = new HashMap<>();
	
	public static int LRSS(String str)
    {
        int n = str.length();
  
        
        int[][] dp = new int[n+1][n+1];
        for (int i=1; i<=n; i++){
            for (int j=1; j<=n; j++){
                if (str.charAt(i-1) == str.charAt(j-1) && i!=j)
                    dp[i][j] =  1 + dp[i-1][j-1];          
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[n][n];
    }
	
	
	public static void main(String[] args) {
		
		LongestRepeated lr = new LongestRepeated();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the string:");
		lr.input = s.next();
		
		System.out.println("Logest Repeated Sequence:= "+ LRSS( lr.input));
		
//		for ( int i =0; i<lr.input.length()-1; i++){
//			for ( int j=0; j<lr.input.length()-1; j++){
//				String sub = lr.input.substring(i,j);
//				if (map.putIfAbsent(sub, findOccurrence(sub))!= null){
//					map.put(sub, map.get(sub)+1);
//				}
//			}
		}
	}

