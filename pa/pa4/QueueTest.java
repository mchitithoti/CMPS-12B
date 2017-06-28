//-----------------------------------------------------------------------------
// QueueTest.java
// Test Client for the Queue class
//-----------------------------------------------------------------------------

public class QueueTest {

   public static void main(String[] args){
      Queue A = new Queue();
      A.enqueue("five"); A.enqueue("three"); A.enqueue("nine"); A.enqueue("seven"); A.enqueue("eight");
      System.out.println(A.toString());
	  System.out.println(A.length());
      A.dequeue(); A.dequeue(); A.dequeue();
      System.out.println(A.toString());
      System.out.println(A.length());
      Queue B = new Queue();
      System.out.println(A.isEmpty());
      System.out.println(B.isEmpty());
      B.enqueue("seven"); B.enqueue("eight");
      A.enqueue("twelve");
      B.enqueue("thirteen");
      System.out.println(A.toString());
      System.out.println(B.toString());
      A.dequeueAll();
      System.out.println(A.toString());
	  System.out.println(B.peek());
      System.out.println(A.isEmpty());
   }
}