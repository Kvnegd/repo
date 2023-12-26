#include <stdio.h>
#include <string.h>
#include "document.h"

int main() {
   Document doc;
   const char *doc_name = "Loading Document ";
   char cmd1[20] = "", cmd2[20] = "com sci";
   /*   int data_lines = 8;
   char data[20][MAX_STR_SIZE + 1] = {
        "The first course you need to take",
	"is cmsc131.  This course will be",
	"followed by cmsc132 (which is also based on Java).",
	"",
	"The next course you will take is cmsc216.",
	"This course relies on C.",
	"",
	"Ruby and Ocaml will be covered in cmsc330"
   };
*/
   init_document(&doc, doc_name);
   
   load_file(&doc, "doc1.txt");
   
   print_document(&doc);
   
   replace_text(&doc, cmd1, cmd2);
   
   print_document(&doc);

   return 0;
}
