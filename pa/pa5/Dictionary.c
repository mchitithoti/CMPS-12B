//-----------------------------------------------------------------------------
// Juan Andreas
// jandreas
// 12B
// 5/28/2016
// Dictionary.c 
// ADT for Hash
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"
#define MAX_LEN 180

const int tableSize = 101;

// ------------------------------------------------------------------
// Provided hash functions

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
	int sizeInBits = 8*sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if ( shift == 0 )
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
	unsigned int result = 0xBAE86554;
	while (*input) {
		result ^= *input++;
		result = rotate_left(result, 5);
	}
	return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
	return pre_hash(key)%tableSize;
}

// ------------------------------------------------------------------
// private types and functions

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

// findKey
Node findKey(Node H, char* targetKey){
	while(H!=NULL){
		if(strcmp(H->key, targetKey)==0){
			break;
		} H = H->next;
	}return H;
}

// deleteAll
void deleteAll(Node N){
	if(N!=NULL){
		deleteAll(N->next);
		freeNode(&N);
	}
}

// ListObj
typedef struct ListObj{
	Node head;
}ListObj;

// List
typedef ListObj* List;

// constructor
List newList(void){
	List L = malloc(sizeof(ListObj));
	assert(L!=NULL);
	L->head = NULL;
	return L;
}

// Dictionary Object
typedef struct DictionaryObj{
   List table;
   int numItems;
} DictionaryObj;

// ------------------------------------------------------------------
// public functions

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->table = calloc(tableSize, sizeof(DictionaryObj));
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
// returns (true) if S is empty, (false) otherwise
// pre: none
int isEmpty(Dictionary D){
	if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
	return(D->numItems==0);
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
	Node N;
	List L;
	int tableIndex = hash(k);
	L = &D->table[tableIndex];
	
	if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
	N = findKey(L->head, k);
	if(N==NULL){
		return NULL;
	}else{
		return N->value;
	}
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	Node N = newNode(k, v);
	int tableIndex = hash(k);
	List L = &D->table[tableIndex];
	
	if(findKey(L->head, k) != NULL){
	   fprintf(stderr, "Dictionary Error: cannot insert an existing key\n");
	   exit(EXIT_FAILURE);
    }
	N->next = L->head;
	L->head= N;
	N = NULL;
	D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
	Node N;
	int tableIndex = hash(k);
	List L = &D->table[tableIndex];
	
	if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot delete non-existent key\n");
      exit(EXIT_FAILURE);
	}
	if(findKey(L->head, k)==L->head){
		N = L->head;
		L->head = L->head->next;
		N->next = NULL;
	}else{
		N = findKey(L->head, k);
		Node before = L->head;
		Node temp = L->head->next;
		while(temp != N){
			temp = temp->next;
			before = before->next;
		}
		before->next = N->next;
		N->next = NULL;
	}
	D->numItems--;
	freeNode(&N);
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
	List L;
	if(D==NULL){
		fprintf(stderr, "DICTIONARY ERROR: calling makeEmpty() on an invalid Dictionary reference\n");
		exit(EXIT_FAILURE);
    }
	if(D->numItems==0){
		exit(EXIT_FAILURE);
    }
	for(int i=0; i<tableSize; i++){
		L = &D->table[i];
		deleteAll(L->head);
	}
	D->table = NULL;
	D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
	Node N;
	List L;
	
	if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
      exit(EXIT_FAILURE);
    }
    for(int i=0; i<tableSize; i++){
		L = &D->table[i];
		N = L->head;
		while(N!=NULL){
			fprintf(out, "%s %s\n", N->key, N->value);
			N = N->next;
		}
	}
}
