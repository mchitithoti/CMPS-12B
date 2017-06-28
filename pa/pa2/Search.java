/* Juan Andreas
 * jandreas
 * 12B
 * searches target words from input files
 * prints out location of target words into output file
 */ 
import java.io.*;
import java.util.Scanner;

class Search{

   public static void main(String[] args) throws IOException{

      int lineCount = 0;
      int i = 0;
      int place = 0;
      String[] list;
      int[] line;

      // check command line arguments
      if(args.length < 2){
         System.err.println("Usage: Search file target1 [target2 ..]");
         System.exit(1);
      }
      //count the number of lines
      Scanner in = new Scanner(new File(args[0]));
      while(in.hasNextLine()){
         in.nextLine();
         lineCount++;
      }

      list = new String[lineCount];
      line = new int[lineCount];

      Scanner in1 = new Scanner(new File(args[0]));
      while(in1.hasNextLine()){
         list[i] = in1.nextLine();
         line[i] = i;
         i++;
      }

      mergeSort(list, line, 0, lineCount-1);

      for(i = 1; i < args.length; i++){
         place = binarySearch(list, 0, lineCount-1, args[i]);
         if(!(binarySearch(list, 0, lineCount-1, args[i]) == -1)){
            place = line[place]+1;
            System.out.println(args[i]+" found on line "+ place);
         }else{
            System.out.println(args[i]+ " not found");
         }
      }
   }

   public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         mergeSort(word, lineNumber, p, q);
         mergeSort(word, lineNumber, q+1, r);
         merge(word, lineNumber, p, q, r);
      }
   }

   public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] left = new int[n1];
      int[] right = new int[n2];
      int i, j, k;

      for(i = 0; i < n1; i++){
         L[i] = word[p+i];
         left[i] = lineNumber[p+i];
      }
      for(j = 0; j < n2; j++){ 
         R[j] = word[q+j+1];
         right[j] = lineNumber[q+j+1];
      }
      i = 0; 
      j = 0;
      for(k = p; k <= r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j])<0 ){
               word[k] = L[i];
               lineNumber[k] = left[i];
               i++;
            }else{
               word[k] = R[j];
               lineNumber[k] = right[j];
               j++;
            }
         }else if( i<n1 ){
            word[k] = L[i];
            lineNumber[k] = left[i];
            i++;
         }else{
            word[k] = R[j];
            lineNumber[k] = right[j];
            j++;
         }
      }
   }

   public static int binarySearch(String[] A, int p, int r, String target){
      int q;
      if(p > r){
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q])==0){
            return q;
         }else if(target.compareTo(A[q]) < 0){
            return binarySearch(A, p, q-1, target);
         }else{ // target > 0
            return binarySearch(A, q+1, r, target);
         }
      }
   }
}  
