/* Juan Andreas
 * jandreas
 * 12B
 * 5/28/2016
 * Dictionary Test file
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"
 
#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four"};
   char* word2[] = {"dog","cat","chicken","cow"};
   int i;

   for(i=0; i<4; i++){
      insert(A, word1[i], word2[i]);
   }
   printf("This is the original list: \n");
   printDictionary(stdout,A);
   
   for(i=0; i<4; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }
   
   delete(A, "one");
   delete(A, "two");
   
   printf("\nThis is the edited list: \n");
   printDictionary(stdout, A);

   for(i=0; i<4; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }
   
   printf("\n");
   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));
   
   freeDictionary(&A);
   
   return(EXIT_SUCCESS);
}
