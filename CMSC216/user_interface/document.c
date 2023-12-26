/*
Name: Kevin Eguida 
University ID: 117911411 
Directory ID: keguida 
*/
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "document.h"

/*
This function initializes a document. An empty document has 0 paragraphs.
This function returns FAILURE if doc or name are NULL, or if the length of name exceeds MAX_STR_SIZE.
It returns SUCCESS otherwise
*/
int init_document(Document *doc, const char *name){
   if(doc && name && strlen(name) < MAX_STR_SIZE){
      strcpy(doc->name, name);
      doc->number_of_paragraphs = 0;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function resets a document, making it empty.
This function returns FAILURE if doc is NULL. It returns SUCCESS otherwise
*/
int reset_document(Document *doc){
   if(doc){
      doc->number_of_paragraphs = 0;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function prints the document in this fashion:
Document name: ""
Number of Paragraphs: n
First Paragraph, First line
First Paragraph, Second line
First Paragraph, nth line

nth Paragraph, First line
nth Paragraph, Second line
nth Paragraph, nth line
Each paragraph is seperated by a blank line
This function returns FAILURE if doc is NULL. It returns SUCCESS otherwise
*/
int print_document(Document *doc){
   if(doc){
      int i, j;
      printf("Document name: \"%s\"\n", doc->name);
      printf("Number of Paragraphs: %d\n", doc->number_of_paragraphs);
      for(i = 0; i < doc->number_of_paragraphs; i++){
         for(j = 0; j < doc->paragraphs[i].number_of_lines; j++){
            printf("%s\n", doc->paragraphs[i].lines[j]);
         }
         /*We add a blank space after each paragraphs if the next paragraph has lines.
           If we have reached the maximum number of paragraph possible, there's no need to 
           add a blank space*/
         if(i != MAX_PARAGRAPHS - 1 && doc->paragraphs[i + 1].number_of_lines != 0){
            printf("\n");
         }
      }
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function adds a new paragraph after the specified paragraph number. If the there are paragraphs
after the specified paragraph number, they will be shifted to the right in order to add the new paragraph.
Paragraphs number start at 1, and calling the function with 0 as the paragrath number will add a paragrath 
at the beginning of the document.
This function returns FAILURE if doc is NULL, or if the paragraph number exceeds MAX_PARAGRATHS or
refers to a paragraph not yet added. It returns SUCCESS otherwise
*/
int add_paragraph_after(Document *doc, int paragraph_number){
   if(doc && doc->number_of_paragraphs < MAX_PARAGRAPHS && paragraph_number <= doc->number_of_paragraphs){
      /*If paragraph_number is valid and lesser than the total number of paragraphs,
        all paragraphs after paragraph_number will be shifted 1 unit to the right in the array of paragraphs.*/
      if(paragraph_number < doc->number_of_paragraphs){
         int i;
         Paragraph p1;
         for(i = doc->number_of_paragraphs - 1; i >= paragraph_number; i--){
            doc->paragraphs[i + 1] = doc->paragraphs[i]; 
         }
         /*resets the paragraph where a new paragraph must be added.*/
         doc->paragraphs[paragraph_number] = p1;
      }
      /*Adds a paragrah after the specified paragraph number*/
      doc->paragraphs[paragraph_number].number_of_lines = 0;
      doc->number_of_paragraphs++;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function works like the add_paragraph_after function but for lines. Line numbers start at 1, and
calling the function with 0 as the line number will place the new line at the start of the paragrath.
This function returns FAILURE if doc is NULL, if new_line is NULL, if paragraph_number isn't between 1 and the number
of paragraphs added, or if line_number isn't between 0 and MAX_PARAGRATH_LINES
if It returns SUCCESS otherwise
*/
int add_line_after(Document *doc, int paragraph_number, int line_number, const char *new_line){
   /*The function returns FAILURE if doc or new_line is NULL, if the paragraph number is smaller or equal to
     the number of paragraphs added, if the specified paragraph already reached it's maximum
     number of lines, if line_number is smaller or equal to the number of lines added in the 
     specified document.*/
   if(doc && new_line && paragraph_number > 0 && paragraph_number <= doc->number_of_paragraphs &&
   doc->paragraphs[paragraph_number - 1].number_of_lines < MAX_PARAGRAPH_LINES && 
   line_number <= doc->paragraphs[paragraph_number - 1].number_of_lines){
      /*If line_number is valid and lesser than the total number of lines in the specified paragraph,
        all lines after the specified line will be shifter 1 unit to the right in the array of lines*/
      if(line_number < doc->paragraphs[paragraph_number - 1].number_of_lines){
         int i;
         for(i = doc->paragraphs[paragraph_number - 1].number_of_lines - 1; i >= line_number; i--){
            strcpy(doc->paragraphs[paragraph_number - 1].lines[i + 1], doc->paragraphs[paragraph_number - 1].lines[i]);
         }
      }
      strcpy(doc->paragraphs[paragraph_number - 1].lines[line_number], new_line);
      doc->paragraphs[paragraph_number - 1].number_of_lines++;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function computes the number of line in a given paragraph, and stores the value in the variable
pointed by number_of_lines.
This function returns FAILURE if doc is NULL, if number_of_lines is NULL, or if paragraph number is invalid.
It returns SUCCESS otherwise
*/
int get_number_lines_paragraph(Document *doc, int paragraph_number, int *number_of_lines){
   /*The function returns FAILURE if doc or number_of_lines is NULL, or paragraph_number
     is larger than the number of available paragraphs.*/
   if(doc && paragraph_number <= doc->number_of_paragraphs && number_of_lines){
      *number_of_lines = 0;
      *number_of_lines = doc->paragraphs[paragraph_number - 1].number_of_lines;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function computes the number of line in the entire document, and stores the value in the variable
pointed by number_of_lines.
This function returns FAILURE if doc is NULL. It returns SUCCESS otherwise
*/
int get_number_lines(Document *doc, int *number_of_lines){
   /*The function returns FAILURE if doc or number_of_lines is NULL*/
   if(doc && number_of_lines){
      int i, total_lines = 0;
      *number_of_lines = 0;
      for(i = 0; i < doc->number_of_paragraphs; i++){
         total_lines += doc->paragraphs[i].number_of_lines;
      }
      *number_of_lines = total_lines;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function adds a line after the last line in the specified paragraph.
This function returns FAILURE if doc is NULL, new_line is NULL, or paragraph_number is invalid.
It returns SUCCESS otherwise.
*/
int append_line(Document *doc, int paragraph_number, const char *new_line){
/*The function returns FAILURE if doc or new_line is NULL, if the paragraph number is smaller or equal to
     the number of paragraphs added, or if the specified paragraph already reached it's maximum
     number of lines.*/
   if(doc && new_line && paragraph_number > 0 && paragraph_number <= doc->number_of_paragraphs &&
   doc->paragraphs[paragraph_number - 1].number_of_lines < MAX_PARAGRAPH_LINES){
      strcpy(doc->paragraphs[paragraph_number - 1].lines[doc->paragraphs[paragraph_number - 1].number_of_lines], new_line);
      doc->paragraphs[paragraph_number - 1].number_of_lines++;
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function removes the specified line at the specified paragraph.
This function returns FAILURE if doc is NULL, if paragraph_number is invalid, or if line_number is invalid.
It returns SUCCESS otherwise
*/
int remove_line(Document *doc, int paragraph_number, int line_number){
   if(doc && paragraph_number <= doc->number_of_paragraphs && line_number <= doc->paragraphs[paragraph_number - 1].number_of_lines){
      int i;
      for(i = line_number; i < doc->paragraphs[paragraph_number - 1].number_of_lines; i++){
         strcpy(doc->paragraphs[paragraph_number - 1].lines[i-1], doc->paragraphs[paragraph_number - 1].lines[i]);
      }
      /*Deletes the last line in the paragraph after it was copied on the line before it.*/
      strcpy(doc->paragraphs[paragraph_number - 1].lines[i], "");
      doc->paragraphs[paragraph_number - 1].number_of_lines--;
      return SUCCESS;      
   }
   return FAILURE;
}

/*
This function creates a document based on an array of strings. The document always starts at paragraph 1.
Each string in data corresponds to a line in the document, and if a line is the empty string, a new paragraph is added.
This function returns FAILURE if doc is NULL, if data is NULL, of if data_lines is smaller than 0.
It returns SUCCESS otherwise
*/
int load_document(Document *doc, char data[][MAX_STR_SIZE + 1], int data_lines){
   if(doc && data && data_lines > 0){
      int i, paragraph_number = 0;
      
      /*adds a new paragraph to the doc*/
      add_paragraph_after(doc, paragraph_number++);

      for(i = 0; i < data_lines; i++){
         /*If the string at i is an empty string, a new paragraph is created.*/
         if(!strcmp(data[i], "")){
            add_paragraph_after(doc, paragraph_number++);
         }
         else
            append_line(doc, paragraph_number, data[i]);
      }
      return SUCCESS;   
   }
   return FAILURE;
}

/*
This function replaces every occurences of the target string in the document with the replacement string
This function returns FAILURE if doc is NULL, if target is NULL or the empty string, or if replacement is NULL. It returns SUCCESS otherwise
*/
int replace_text(Document *doc, const char *target, const char *replacement){
   if(doc && target && *target && replacement){
      int i, j, k, target_length = strlen(target), replacement_length = strlen(replacement);
      char *ptr, after_target[MAX_STR_SIZE + 1];
      
      for(i = 0; i < doc->number_of_paragraphs; i++){
         for(j = 0; j < doc->paragraphs[i].number_of_lines; j++){
            /*Finds the first occurence of the target on the line*/
            ptr = strstr(doc->paragraphs[i].lines[j], target);
            /*While there's still occurences of the target on the line, we replace the occurences*/
            while(ptr){
              if(ptr){
                 strcpy(after_target, ptr + target_length);
                 k=0;
                 while(replacement[k]){
                    ptr[k] = replacement[k];
                    k++;
                 }
                 strcpy(ptr + replacement_length, after_target);
              }
              /*Looks for other occurences of the target on the line*/
              ptr++;
              ptr = strstr(ptr, target);
            }
         }
      }
      return SUCCESS;
   }
   return FAILURE;
}

/* 
This function surrounds every occurences of the target string in the document with HIGHLIGHT_START_STR and 
HIGHLIGHT_END_STR. 
This function returns FAILURE if doc is NULL, or if target in NULL. It returns SUCCESS otherwise 
*/ 
int highlight_text(Document *doc, const char *target){ 
   if(doc && target){ 
      int i, j, target_length = strlen(target); 
      char *ptr, after_target[MAX_STR_SIZE + 1]; 
       
      for(i = 0; i < doc->number_of_paragraphs; i++){ 
         for(j = 0; j < doc->paragraphs[i].number_of_lines; j++){ 
            /*Finds the first occurence of the target on the line*/ 
            ptr = strstr(doc->paragraphs[i].lines[j], target); 
            /*While there's still occurences of the target on the line, we surround the occurences with 
              the highlight strings.*/ 
            while(ptr){ 
              if(ptr){ 
                 /*Saves a copy of the line from the end of the target to the end of the line*/ 
                 strcpy(after_target, ptr + target_length); 
                 /*Replaces the first character of the target with the highlight string*/ 
                 *ptr = *HIGHLIGHT_START_STR; 
                 /*copies the target string after HIGHLIGHT_START_STR and puts  HIGHLIGHT_END_STR at the end.*/ 
                 strcpy(ptr + 1, target); 
                 ptr[target_length + 1] = *HIGHLIGHT_END_STR; 
                 /*Puts back the rest of the line after HIGHLIGHT_END_STR*/  
                 strcpy(ptr + target_length + 2, after_target); 
              } 
              /*Looks for other occurences of the target on the line*/ 
              ptr = ptr + target_length + 2; 
              ptr = strstr(ptr, target); 
            } 
         } 
      } 
      return SUCCESS; 
   } 
   return FAILURE; 
} 

/*
This function removes every occurences of the target string in the document
This function returns FAILURE if doc is NULL, of if target in NULL. It returns SUCCESS otherwise
*/
int remove_text(Document *doc, const char *target){
   if(doc && target){
      replace_text(doc, target, "");
      return SUCCESS;
   }
   return FAILURE;
}

/*
This function is similar to load_document, except that data is loaded from a file instead of from an
array (in memory). By default the function adds a new paragraph. A blank line (line with only spaces
as defined by isspace()) marks the the beginning of a new paragraph. The function returns FAILURE
if doc is NULL, filename is NULL, opening the file failed, or the number of paragraphs would exceed
MAX_PARAGRAPHS; otherwise the function returns SUCCESS. If the number of paragraphs would exceed
MAX_PARAGRAPHS, the function adds paragraphs until the maximum limit is reached. Notice no error
message is generated if the file cannot be opened
*/
int load_file(Document *doc, const char *filename){
   if(doc && filename){
      int i = 0, paragraph_number = 0, new_paragraph;
      char buf[MAX_STR_SIZE + 1], *ptr;
      FILE *input_stream;
      
      input_stream = fopen(filename, "r");
      if(input_stream == NULL){
         return FAILURE;
      }
      
      /*adds a new paragraph to the doc*/
      add_paragraph_after(doc, paragraph_number++);
      
      /*Reads input from the file until the end of the file*/
      while(fgets(buf, MAX_STR_SIZE + 1, input_stream)){
         /*Line holds the buf string without the '\n' character*/
         char line[MAX_STR_SIZE + 1];
         /*new_paragraph is true until a non whitespace character was detected in the buffer*/
         new_paragraph = 1;
         ptr = buf;
         /*If the entire buffer is whitespaces character, new_paragraph is true.*/
         while(*ptr != '\n' && *ptr != '\0' && new_paragraph){
            if(!isspace(*ptr)){
               new_paragraph = 0;
            }
            ptr++;
         }
         
         if(new_paragraph){
            /*If the doc already reached its maximum number of paragraphs, adding a new paragraph returns FAILURE*/
            if(doc->number_of_paragraphs == MAX_PARAGRAPHS){
               return FAILURE;
            }
            add_paragraph_after(doc, paragraph_number++);
         }
         else{
            /*Puts ptr at the beginning of buf*/
            ptr = buf;
            i = 0;
            /*Copies buf into line without the '\n'*/
            while(*ptr != '\n' && ptr != NULL){
               line[i] = *ptr;
               i++;
               ptr++;
            }
            line[i] = '\0';
            append_line(doc, paragraph_number, line);
         }
      }
      fclose(input_stream);
      return SUCCESS;
   }
   return FAILURE;
}
/*
This function prints the paragraphs associated with the specified document to the specified file (overwriting the file). 
Each paragraph is separated by newline. The function returns FAILURE if doc is NULL, filename is NULL, or the file cannot be
opened; otherwise the function returns SUCCESS. Notice no error message is generated if the file cannot
be opened.
*/
int save_document(Document *doc, const char *filename){
   if(doc && filename){
      int i, j;
      FILE *output_stream;
      
      output_stream = fopen(filename, "w");
      if(output_stream == NULL){
         return FAILURE;
      }
      
      for(i = 0; i < doc->number_of_paragraphs; i++){
         for(j = 0; j < doc->paragraphs[i].number_of_lines; j++){
            fprintf(output_stream, "%s\n", doc->paragraphs[i].lines[j]);
         }
         /*We add a blank space after each paragraphs if the next paragraph has lines.
           If we have reached the maximum number of paragraph possible, there's no need to 
           add a blank space*/
         if(i != MAX_PARAGRAPHS - 1 && doc->paragraphs[i + 1].number_of_lines != 0){
            fprintf(output_stream, "\n");
         }
      }
      fclose(output_stream);
      return SUCCESS;
   }
   return FAILURE;
}