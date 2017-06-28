#------------------------------------------------------------------------------
# makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c 
TEST    = Dictionary.c DictionaryTest.c 
FILES   = Makefile README
OBJECTS = Dictionary.o DictionaryClient.o
TESTOBJECTS = Dictionary.o DictionaryTest.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

test :
	gcc -c $(FLAGS) $(TEST)
	gcc -o DictionaryTest $(TESTOBJECTS)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

cleanTest :
	rm -f DictionaryTest $(TESTOBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit:
	submit cmps012b-pt.s16 lab5 $(FILES) $(SOURCES) $(HEADERS) DictionaryTest.c
