// List.java
// jandreas
// Juan Andreas
// lab6
// List ADT

@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>{

   // ---------------------------------------------------------------
   // Node class
   private class Node{
	   T item;
	   Node next;
	   
	   Node(T x){
		   item = x;
		   next = null;
	   }
   }
   
   // list class fields
   private Node head;
   private int numItems;
   
   // List constructor
   public List(){
	   head = null;
	   numItems = 0;
   }

   // ---------------------------------------------------------------
   // ADT Ops
   
   // isEmpty
   // pre: none
   // post: returns true if this List is empty, false otherwise
   public boolean isEmpty(){
	   return(numItems == 0);
   }

   // size
   // pre: none
   // post: returns the number of elements in this List
   public int size(){
	   return numItems;
   }

   // get
   // pre: 1 <= index <= size()
   // post: returns item at position index
   public T get(int index) throws ListIndexOutOfBoundsException{
	  if( index<1 || index>numItems ){
        throw new ListIndexOutOfBoundsException(
           "IntegerList Error: get() called on invalid index: " + index);
      }
      Node N = find(index);
      return N.item;
   }

   // add
   // inserts newItem in this List at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem) throws ListIndexOutOfBoundsException{   
      if( index<1 || index>(numItems+1) ){
         throw new ListIndexOutOfBoundsException(
            "IntegerList Error: add() called on invalid index: " + index);
      }
      if(index==1){
         Node N = new Node(newItem);
         N.next = head;
         head = N;
      }else{
         Node P = find(index-1); // at this point index >= 2
         Node C = P.next;
         P.next = new Node(newItem);
         P = P.next;
         P.next = C;
      }
      numItems++;
   }

   // remove
   // deletes item from position index
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index) throws ListIndexOutOfBoundsException{
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "IntegerList Error: remove() called on invalid index: " + index);
      }
      if(index==1){
         Node N = head;
         head = head.next;
         N.next = null;
      }else{
         Node P = find(index-1);
         Node N = P.next;
         P.next = N.next;
         N.next = null;
      }
      numItems--;
   }

   // removeAll
   // pre: none
   // post: isEmpty()
   public void removeAll(){
	   head = null;
	   numItems = 0;
   }
   
   // find
   // pre: none
   // post: returns reference
   // functions used for add and get
   private Node find(int index){
	   Node N = head;
	   for(int i=1; i<index; i++) N = N.next;
	   return N;
   }
   
   // toString
   // pre: none
   // post: prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
	   StringBuffer sb = new StringBuffer();
       for(Node N = head; N != null; N = N.next){
		   sb.append(N.item).append(" ");
	   }
	   return new String(sb);
   }
   
   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs){
      boolean eq = false;
      List<T> R = null;
	  Node N = null;
	  Node M = null;
      
      if(rhs instanceof List){
         R = (List<T>) rhs;
         eq = (this.numItems == R.numItems);
		 
		 N = this.head;
		 M = R.head;
		 
	     while(eq == true && N != null){
		    eq = (N.item == M.item);
		    N = N.next;
		    M = M.next;
         }
	  }
      return eq;
   }
}
