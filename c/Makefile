# -*- MakeFile -*-

# ERROR: make: *** No rule to make target `pg-mutex.print.cc', needed by `pg-mutex.print'.  Stop.
# MEANS YOU TYPE FILENAME WRONG

# target : dependencies
#	command

# CXX= g++
CXX= clang++
CFLAGS= -O3 -Wall -g -Werror -std=c++11
LDFLAGS = -lm -pthread -lthreads -lrand


# if you just write this then call make => cc pg-pthread.c -o pg-pthread
# all: $(SOURCES)



# SOURCES = pg-dining-philosopher.cc
# EXEC= pg-dining-philosopher
# pg-dining-philosopher: $(SOURCES)
# 	$(CXX) $(CFLAGS) $(SOURCES) -o $(EXEC) 


# SOURCES = pg-mutex.cc
# EXEC = pg-mutex
# pg-mutex: $(SOURCES)
# 	$(CXX) $(CFLAGS) $(SOURCES) -o $(EXEC)


SOURCES = pg-mutex-print.cc
EXEC= pg-mutex-print
pg-mutex-print: $(SOURCES)
	$(CXX) $(CFLAGS) $(SOURCES) -o $(EXEC) 

