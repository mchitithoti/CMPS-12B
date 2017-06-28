#------------------------------------------------------------------------------
# jandreas
# Juan Andreas
# 12m
# Makefile for charType.c with Macros
#------------------------------------------------------------------------------

FLAGS = -std=c99 -Wall
CSRC = charType.c
SOURCES = $(CSRC) README Makefile
OBJECTS = charType.o
EXEBIN = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

make :
	gcc $(FLAGS) -o $(EXEBIN) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)

submit :
	submit cmps012b-pt.s16 lab4 $(SOURCES)

