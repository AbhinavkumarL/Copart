import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;

/**
 * Created by gokul on 8/2/2017.
 */
public class SmallestSubset {
    public static void main(String[] agrs){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> finalList = new ArrayList<>();
        ArrayList<Integer> minList = new ArrayList<>();
        int len =0;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\gokul\\IdeaProjects\\Copart\\src\\Sample.txt"));
            String line = bf.readLine();
            int sum = Integer.parseInt(line);
            String array = bf.readLine();
            String[] arrEle = array.split(",");
            for (int i=0; i< arrEle.length; i++){
                arr.add(Integer.parseInt(arrEle[i]));
            }
           finalList  = smallestSubset(arr,sum);
            for (ArrayList<Integer> list : finalList){
                len = list.size();
                if (list.size()< len){
                    minList = list;
                }
            }
            for (Integer e : minList){
                System.out.println(e);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<ArrayList<Integer>> smallestSubset(ArrayList arrElem, int sum){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int tempSum=0;
        for (int i=0 ; i<arrElem.size()-1; i++){
            ArrayList<Integer> tempList = new ArrayList<>();
            tempSum = (int)arrElem.get(i);
            tempList.add((int)arrElem.get(i));
            if (tempSum > sum) {
                tempList.add((int)arrElem.get(0));
                result.add(tempList);
                return result;
            }
            int j=i+1;
                do {
                    tempSum += (int)arrElem.get(j);
                    tempList.add((int)arrElem.get(j));
                    j++;
                }while (tempSum < sum && j<arrElem.size()-1);
                if (tempSum > sum) {
                    result.add(tempList);
                }
            }
    return result;
    }
}