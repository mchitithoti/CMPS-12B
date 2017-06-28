import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class FileReverse{
   public static void main(String[] args) throws IOException{

      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] token = null;
      int i, n, lineNumber = 0;

      if(args.length < 2){
         System.out.println("Usage: FileCopy <input file> <output file>");
         System.exit(1);
      }

      //open files
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      //read lines from in
      while(in.hasNextLine()){
         lineNumber++;

         line = in.nextLine().trim() + " ";
         token = line.split("\\s+");

         n = token.length;
         //prints reversed tokens to out
         for(i = 0; i < n; i++){
            out.println(stringReverse(String.valueOf(token[i]), (token[i]).length()));
         }
      }
 
      //close files
      in.close();
      out.close();
   }

   public static String stringReverse(String s, int n){
      if(n <= 1){
         return s;
      }n--;
         return stringReverse((s.substring(1)), n) + s.charAt(0);
   }
}
