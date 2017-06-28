// Dictionary.java
// Juan Andreas
// jandreas
// pa3
// Linked List implementation of the Dictionary ADT

public class Dictionary implements DictionaryInterface{

   //--------------------------------------------------------------------------
   // Initialize Node 
   private class Node{
      String ref;
      String val;
      Node next;
           /*  item field  */
      Node(String key, String value){
         this.ref = key;
         this.val = value; 
         next = null;
      }
   }

   // Fields
   private Node head;
   private int numItems;

   // IntegerList()
   // constructor
   public Dictionary(){
      head = null;
      numItems = 0;
   }

   // -------------------------------------------------------------------------
   // ADTs

   // isEmpty()
   // pre: none
   // returns true if Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // returns the number of pairs in Dictionary
   public int size(){
      return numItems;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key){
      Node N = head;
      while(N != null){
         if(N.ref.equals(key)){
            return N.val;
         }
         N = N.next;
      }
      return null;
   }      

   // insert()
   // inserts new pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
      if(lookup(key) != null){ // if it exists
         throw new DuplicateKeyException("cannot insert duplicate keys");
      }else if(head == null){
         Node N = new Node(key, value);
         head = N;
         numItems++;
         }else{
            Node N = head;
            while(N != null){
               if(N.next == null){
                  break;
               }
               N = N.next;
            }
         N.next = new Node(key, value);
         numItems++; 
         }
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
      if(lookup(key) == null){ // if arg key does not exist
         throw new KeyNotFoundException("cannot delete non-existent key");
      }else{
         if(numItems <= 1){
            Node N = head;
            head = head.next;
            N.next = null;
            numItems--;
         }else{
            Node N = head;
            if(N.ref.equals(key)){
               head = N.next;
               numItems--;
            }else{
               while(!N.next.ref.equals(key)){
                  N = N.next;
               }
               N.next = N.next.next;
               numItems--;
            }
         }
      }
   }

   // makeEmpty()
   // pre: none
   public void makeEmpty(){
      head = null;
      numItems = 0;
   }

   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = head;

      for( ;N!=null; N=N.next){
         sb.append(N.ref).append(" ").append(N.val).append("\n");
      }
      return new String(sb);
   }
}  
