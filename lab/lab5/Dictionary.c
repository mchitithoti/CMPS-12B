//-----------------------------------------------------------------------------
// Dictionary.c
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types and functions ------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node head;
   Node tail;
   int numItems;
} DictionaryObj;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->head = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if(D->numItems>0){
	   return 0;
   }else{
	   return 1;
   }
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N = D->head;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   while(N != NULL){
	   if(strcmp(N->key, k)==0)
              return N->value;
	      N = N->next;
   }
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if(lookup(D, k) != NULL){
	   fprintf(stderr, "Dictionary Error: cannot insert an existing key\n");
   }else if(D->head == NULL){
      Node N = newNode(k, v);
      D->head = N;
      D->numItems++;
   }else{
      Node N = D->head;
      while(N != NULL){
         if(N->next == NULL){
            break;
         }
         N = N->next;
      }
      N->next = newNode(k, v);
      D->numItems++; 
   }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   if( lookup(D, k)==NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot delete non-existent key\n");
      exit(EXIT_FAILURE);
   }else{
      if(D->numItems <= 1){
         Node N = D->head;
         D->head = (D->head)->next;
         N->next = NULL;
         D->numItems--;
      }else{
         Node N = D->head;
         if(strcmp((N->key), k)==0){
            D->head = N->next;
            D->numItems--;
         }else{
            while(!(strcmp((N->next->key),k)==0)){
               N = N->next;
            }
            N->next = N->next->next;
            D->numItems--;
         }
      }
   }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   D->head = NULL;
   D->tail = NULL;
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->head; N!=NULL; N=N->next) fprintf(out, "%s %s \n", N->key, N->value);
   fprintf(out, "\n");
}

