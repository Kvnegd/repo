CC = gcc
CFLAGS = -ansi -Wall -g -O0 -Wwrite-strings -Wshadow \
-pedantic-errors -fstack-protector-all 
PROGS = shell_jr

all: $(PROGS)

shell_jr: shell_jr.o
	$(CC) -o shell_jr shell_jr.o
   
shell_jr.o: shell_jr.c
	$(CC) -g -c shell_jr.c

clean:
	rm -f *.o $(PROGS) a.out
