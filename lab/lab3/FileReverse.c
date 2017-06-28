/* Juan Andreas
 * jandreas
 * 12B
 * 4/18/2016
 * FileReverse in C
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void stringReverse(char* s){
   int i = 0;
   int j = strlen(s)-1;
   char temp;
   while(i < j){
   temp = s[i];
   s[i] = s[j];
   s[j] = temp;
   i++;
   j--; 
   }
}

int main(int argc, char* argv[]){
   FILE* in;
   FILE* out;
   char word[256];
   //command line check
   if(argc != 3){
   printf("Usage: %s <input file> <output file>\n", argv[0]);
   exit(EXIT_FAILURE);
   }
   //open input file for reading
   in = fopen(argv[1], "r");
   if(in == NULL){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }
   //open output file for writing
   out = fopen(argv[2], "w");
   if(out == NULL){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   //read words from input file, print on seperate lines to output file
   while(fscanf(in, " %s", word)!=EOF){
      stringReverse(word);
      fprintf(out, "%s\n", word);
   }
   //close in and out
   fclose(in);
   fclose(out);
}
