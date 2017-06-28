import java.io.*;
import java.util.Scanner;

class LC {

   public static void main(String[] args) throws IOException {

      // check number of command line arguments
      if(args.length != 1){
         System.err.println("Usage: LC file");
         System.exit(1);
      }
      
      // count the number of lines in file
      Scanner in = new Scanner(new File(args[0]));
      in.useDelimiter("\\Z");
      String s = in.next(); 
      in.close();
      String[] lines = s.split("\n");
      int lineCount = lines.length;
      
      // print out number of lines in file
      System.out.println( args[0]+" contains "+lineCount+" lines" );
   }
}
