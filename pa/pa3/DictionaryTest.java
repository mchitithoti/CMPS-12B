/* DictionaryTest.java
 * Juan Andreas
 * jandreas
 * 12b
 * Test for Dictionary
 */

public class DictionaryTest{

   public static void main(String[] args){
      Dictionary D = new Dictionary();

      System.out.println("This is the original list: ");
      D.insert("1","z");
      D.insert("2","y");
      System.out.println(D);
      //empties linked list
      D.makeEmpty();
      //refills list
      D.insert("1","a");
      D.insert("2","b");
      D.insert("3","c");

      System.out.println("This is the new list: ");
      System.out.println(D);
      //finds matching values to keys
      System.out.println("These are the values of the new list keys: ");
      System.out.println(D.lookup("1"));
      System.out.println(D.lookup("2"));
      System.out.println(D.lookup("3"));
      System.out.println();

      D.delete("3");

      System.out.println("This is the final list: ");
      System.out.print(D);
      System.out.println();
      System.out.println("List empty?");
      System.out.println(D.isEmpty());
   }
}
