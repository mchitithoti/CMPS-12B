// ListTest.java
// jandreas
// Juan Andreas
// lab6
// Test file for List ADT

public class ListTest{

   public static void main(String[] args){
      List<String> Q = new List<String>();
      List<String> L = new List<String>();
      List<List<String>> Z = new List<List<String>>();
      int i, j, k;

      Q.add(1, "einz");
      Q.add(2, "zwei");
    
      L.add(1, "un");
      L.add(2, "deux");
     
      Z.add(1, Q);
      Z.add(2, L);

      System.out.println("Q: "+Q);
      System.out.println("L: "+L);
      System.out.println("Z: "+Z);
      System.out.println("Q.equals(Q) is "+Q.equals(Q));
      System.out.println("Q.equals(L) is "+Q.equals(L));
      System.out.println("Q.equals(Z) is "+Q.equals(Z));
      System.out.println("Q.size() is "+Q.size());
      System.out.println("L.size() is "+L.size());
      System.out.println("Z.size() is "+Z.size());

      Q.remove(1);
      L.remove(2);

      System.out.println("Q.size() is "+Q.size());
      System.out.println("L.size() is "+L.size());
      System.out.println("L.get(1) is "+L.get(1));
      System.out.println("Z: "+Z);
      System.out.println();
      try{
         System.out.println(Q.get(200));
      }catch(ListIndexOutOfBoundsException e){
         System.out.println("Caught Exception: ");
         System.out.println(e);
         System.out.println("Continue");
      }
      System.out.println();
      System.out.println("bye");
   }
}
