#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c
TESTSRC = Dictionary.c DictionaryTest.c
TESTOBJ = Dictionary.o DictionaryTest.o
OBJECTS = Dictionary.o DictionaryClient.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
TESTEXE = DictionaryTest

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

test :
	gcc -o $(TESTEXE) $(TESTOBJ)
	gcc -c $(FLAGS) $(TESTSRC)

testclean :
	rm -f $(TESTOBJ) DictionaryTest

check :
	valgrind --leak-check=full $(EXEBIN)

submit :
	submit cmps012b-pt.s16 pa5 README $(SOURCES) $(HEADERS) DictionaryTest.c Makefile
