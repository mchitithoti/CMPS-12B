// Dictionary.java
// Juan Andreas
// jandreas
// lab7
// Linked List implementation of the Dictionary ADT

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Dictionary implements DictionaryInterface{
	
   //--------------------------------------------------------------------------
   // Initialize Node 
   private class Node{
      Pair item;
	  Node left;
	  Node right;

      Node(Pair x){
		 item = x;
         left = null;
		 right = null;
      }
   }
   
   // private inner Pair class
   private class Pair{
	   String key;
	   String value;
	   
	   Pair(String x, String y){
		   key = x;
		   value = y;
	   }
   }
   
   // Fields
   private Node root;
   private int numPairs;

   // IntegerList()
   // constructor
   public Dictionary(){
      root = null;
      numPairs = 0;
   }
   
   //findKey()
   // returns the Node containing key k in the subtree rooted at R, or returns
   // null if no such Node exists
   private Node findKey(Node R, String k){
	   if(R==null || R.item.key.equals(k)){
		   return R;
	   }
	   if(R.item.key.compareTo(k)>0)
		   return findKey(R.left, k);
	   else
		   return findKey(R.right, k);
   }
   
   Node findParent(Node N, Node R){
	   Node P = null;
	   if(N!=R){
		   P = R;
		   while(P.left!=N && P.right!=N){
			   if(N.item.key.compareTo(P.item.key)<0)
				   P = P.left;
			   else
				   P = P.right;
		   }
	   }
	   return P;
   }
   
   //findLeftmost()
   // returns the leftmost Node in the subtree rooted at R, or null if R is null
   Node findLeftmost(Node R){
	   Node L = R;
	   if(L!=null) for( ; L.left!=null; L=L.left);
	   return L;
   }
   
   // printInOrder()
   // prints the (key, value) pairs belonging to the subtree rooted at R in order
   // of increasing keys to file pointed to by out
   void printInOrder(Node R){
	   if(R!=null){
		   printInOrder(R.left);
		   System.out.println(R.item.key+" "+R.item.value);
		   printInOrder(R.right);
	   }
   }
   
   // deleteAll()
   // deletes all Nodes in the subtree rooted at N
   void deleteAll(Node N){
	   if(N!=null){
		   deleteAll(N.left);
		   deleteAll(N.right);
	   }
   }
   
   // public functions ----------------------------------------------
   
   // isEmpty()
   public boolean isEmpty(){
	   return(numPairs==0);
   }
 
   // size()
   // pre: none
   // post: returns the number of elements in this Dictionary
   public int size() {
     return numPairs;
   }
   
   // lookup()
   // pre: none
   // post: returns value to associated key, or null if key doesn't exists
   public String lookup(String k){
	   Node N;
	   N = findKey(root, k);
	   return( N==null ? null : N.item.value);
   }
   
   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   // post: !isEmpty(), size() is increased by one
   public void insert(String k, String v) throws DuplicateKeyException{
	   Node N, A, B;
	   if(findKey(root, k)!=null){
		   throw new DuplicateKeyException("Dictionary Error: cannot insert duplicate keys");
	   }
	   N = new Node(new Pair(k, v));
	   B = null;
	   A = root;
	   while(A!=null){
		   B = A;
		   if(A.item.key.compareTo(k)>0) A = A.left;
		   else A = A.right;
	   }
	   if(B==null) root = N;
	   else if(B.item.key.compareTo(k)>0) B.left = N;
	   else B.right = N;
	   numPairs++;
   }
   
   // delete()
   // deletes pair with the given key
   // pre: lookup(D, key)!=null
   public void delete(String k) throws KeyNotFoundException{
	   Node N, P, S;
	   if(findKey(root, k)==null){
		   throw new KeyNotFoundException("Dictionary Error: cannot delete non-existent key");
	   }
	   N = findKey(root, k);
	   if(N==null){
		   throw new KeyNotFoundException("Dictionary Error: cannot delete non-existent key");
	   }
	   if(N.left==null && N.right==null){ // case 1
	       if(N==root){
			   root = null;
		   }else{
			   P = findParent(N, root);
			   if(P.right==N) P.right=null;
			   else P.left = null;
		   }
	   }else if(N.right==null){ // case 2(left but no right child)
		   if(N==root){
			   root = N.right;
		   }else{
			   P = findParent(N, root);
			   if(P.right == N) P.right = N.left;
			   else P.left = N.left;
		   }
	   }else if(N.left==null){ // case 2(right but no left child)
		   if(N==root){
			   root = N.right;
		   }else{
			   P = findParent(N, root);
			   if(P.right==N) P.right = N.right;
			   else P.left = N.right;
		   }
		}else{                 // case 3 N.left!=null && N.right!=null
			S = findLeftmost(N.right);
			N.item.key = S.item.key;
			N.item.value = S.item.value;
			P = findParent(S, N);
			if(P.right==S) P.right = S.right;
			else P.left = S.right;
		}
		numPairs--;
   }
   
   // makeEmpty()
   // pre: none
   public void makeEmpty(){
	  deleteAll(root);
      root = null;
      numPairs = 0;
   }
   
   // toString()
   // returns a String representation of this Dictionary
   // overrides Object's toString() method
   // pre: none
   public String toString(){
      printInOrder(root);
	  return "";
   }
}
   
   