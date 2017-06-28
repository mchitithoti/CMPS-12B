//-----------------------------------------------------------------------------
// IntegerQueue.java
// Array based implementation of IntegerQueue ADT (with array doubling)
//-----------------------------------------------------------------------------

public class IntegerQueue implements IntegerQueueInterface{

   private static final int INITIAL_SIZE = 1;
   private int physicalSize;  // current length of underlying array
   private int[] item;        // array of IntegerQueue items
   private int numItems;      // number of items in this IntegerQueue
   private int front;         // index of front element
   private int back;          // index of back element

   // doubleItemArray()
   // doubles the physical size of the underlying array item[]
   private void doubleItemArray(){
      int[] newArray = new int[2*physicalSize];
      for(int i=0; i<numItems; i++) newArray[i] = item[(front+i)%physicalSize];
      item = newArray;
      physicalSize *=2;
      front = 0;
      back = numItems-1;
   }

   // IntegerQueue()
   // default constructor for the IntegerQueue class
   public IntegerQueue(){
      physicalSize = INITIAL_SIZE;
      item = new int[physicalSize];
      numItems = 0;
      front = 0;
      back = -1;
   }

   // isEmpty()
   // pre: none
   // post: returns true if this IntgerQueue is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // enqueue()
   // adds x to back of this IntegerQueue
   // pre: none
   // post: !isEmpty()
   public void enqueue(int x){
      if( numItems == physicalSize ) doubleItemArray();
      numItems++;
      back = (back+1)%physicalSize;
      item[back] = x;
   }

   // dequeue()
   // deletes and returns item from front of this IntegerQueue
   // pre: !isEmpty()
   // post: this IntegerQueue will have one fewer element
   public int dequeue() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot dequeue() empty queue");
      }
      int returnValue = item[front];
      front = (front+1)%physicalSize;
      numItems--;
      return returnValue;
   }

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public int peek() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      return item[front];
   }

   // dequeueAll()
   // sets this IntegerQueue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot dequeueAll() empty queue");
      }
      numItems = 0;
      front = 0;
      back = -1;
   }

   // toString()
   // overrides Object's toString() method
   public String toString(){
      String s = "";
      for(int i=0; i<numItems; i++){
         s += item[(front+i)%physicalSize] + " ";
      }
      return s;
   }

   // equals()
   // overrides Object's equals() method
   public boolean equals(Object rhs){
      IntegerQueue R = null;
      boolean eq = false;
      int i, n, m;

      if(rhs instanceof IntegerQueue){
         R = (IntegerQueue)rhs;
         eq = ( this.numItems == R.numItems );
         for(i=0; eq && i<numItems; i++){
            n = (this.front + i)%this.physicalSize;
            m = (R.front + i)%R.physicalSize;
            eq = ( this.item[n] == R.item[m] );
         }
      }
      return eq;
   }
}