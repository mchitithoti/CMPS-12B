//-----------------------------------------------------------------------------
// IntegerQueueInterface.java
// interface for the IntegerQueue ADT
//-----------------------------------------------------------------------------

public interface IntegerQueueInterface{

   // isEmpty()
   // pre: none
   // post: returns true if this IntgerQueue is empty, false otherwise
   public boolean isEmpty();

   // enqueue()
   // adds x to back of this IntegerQueue
   // pre: none
   // post: !isEmpty()
   public void enqueue(int x);

   // dequeue()
   // deletes and returns item from front of this IntegerQueue
   // pre: !isEmpty()
   // post: this IntegerQueue will have one fewer element
   public int dequeue() throws QueueEmptyException;

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public int peek() throws QueueEmptyException;

   // dequeueAll()
   // sets this IntegerQueue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException;
}