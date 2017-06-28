// charType.c
// jandreas
// Juan Andreas
// 12m
// counts and sorts types of characters 
// from in file to out file

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>

#define MAX_STRING_LENGTH 100

// extraction prototype
void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
   FILE* in;		// handle for input file
   FILE* out;		// handle for output file
   char* line;		// string holding input line
   char* alpha;		// string holding alphabetical chars
   char* num;		// string holding numerical chars
   char* pun;		// string holding punctuation chars
   char* white;		// string holding whitespace characters
   int lineNum = 1; 	// line number

   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }
   // open input file for reading
   if ( (in=fopen(argv[1],"r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }
   // open output file for reading
   if( (out=fopen(argv[2], "w"))==NULL ){
     printf("Unable to write to file %s\n", argv[2]);
     exit(EXIT_FAILURE);
   }
   // allocate strings on the heap
   line = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   alpha = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   num = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   pun = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   white = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
   assert( line != NULL && alpha != NULL && num != NULL && pun != NULL && white != NULL);
   // read each line in input until end of line, and extracts four different char types
   while ( fgets(line, MAX_STRING_LENGTH, in) != NULL){
      extract_chars(line, alpha, num, pun, white);
      fprintf(out, "line %d contains: \n", lineNum);

      // if-true- prints for plural
      // else-true- prints for singular

      // for alphabeticals
      if(strlen(alpha)>1){
         fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);
      }else {
         fprintf(out, "%d alphabetic character: %s\n", (int)strlen(alpha), alpha);
      } 
      // for numericals
      if(strlen(num)>1){
         fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(num), num);
      }else{
         fprintf(out, "%d alphabetic character: %s\n", (int)strlen(num), num);
      }
      // for punctuations
      if(strlen(pun)>1){
         fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(pun), pun);
      }else {
         fprintf(out, "%d alphabetic character: %s\n", (int)strlen(pun), pun);
      }
      // for whitespaces
      if(strlen(white)>1){
         fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(white), white);
      }else {
         fprintf(out, "%d alphabetic character: %s\n", (int)strlen(white), white);
      }
      // increments line number to be printed out for each line 
      lineNum++;
   }
   // free heap memory
   free(line);
   line = NULL;
   free(alpha);
   alpha = NULL;
   free(num);
   num = NULL;
   free(pun);
   pun = NULL;
   free(white);
   white = NULL;
   // close input and output files
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;

}

void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, al=0, nu=0, pu=0, wh=0;
   // keep going through loop until null is hit
   while (s[i] != '\0' && i<MAX_STRING_LENGTH){
      // takes uppercase and alphabetical chars
      if( isupper((int)s[i])){
         a[al++] = s[i];
      }else if( isalpha((int)s[i])){
         a[al++] = s[i];
      // takes numerical chars
      }else if( isdigit((int)s[i])){
         d[nu++] = s[i];
      // takes punctuation chars
      }else if(ispunct((int)s[i])){
         p[pu++] = s[i];
      // takes whitespace
      }else{
         w[wh++] = s[i];
      }
      i++;
   }
   a[al] = '\0';
   d[nu] = '\0';
   p[pu] = '\0';
   w[wh] = '\0';
}
