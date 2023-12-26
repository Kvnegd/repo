/*
Name: Kevin Eguida 
University ID: 117911411 
Directory ID: keguida 
*/
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <sysexits.h>
#include "document.h"
#define MAX_CMD_LINE 1024

static void user_interface(FILE *);

int main(int argc, char **argv){

   if (argc == 1) {
      user_interface(stdin);
   } 
   else if(argc == 2){
      FILE *file = fopen(argv[1], "r");
      if(file == NULL){
         fprintf(stderr, "%s cannot be opened.\n", argv[1]);
         exit(EX_OSERR);
      }
      user_interface(file);
      fclose(file);
   }
   else{
      fprintf(stderr, "Usage: %s\n", argv[0]);
      fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
      exit(EX_USAGE);
   }
   exit(EXIT_SUCCESS);
}

static void user_interface(FILE *input_stream){
   int valid_command, paragraph_number, line_number;
   char cmd_line[MAX_CMD_LINE + 1] = "", cmd1[MAX_CMD_LINE + 1] = "", cmd2[MAX_CMD_LINE + 1] = "",  cmd3[MAX_CMD_LINE + 1] = "";
   char cmd4[MAX_CMD_LINE + 1] = "", *ptr;
   Document doc;
   /*Initializes a singular document name main_document*/
   init_document(&doc, "main_document");
   
   do{
         /*the command is valid unless proven otherwise.*/
         valid_command = 1;
         strcpy(cmd_line, "");
         strcpy(cmd1, "");
         strcpy(cmd2, "");
         strcpy(cmd3, "");
         strcpy(cmd4, "");
         if(input_stream == stdin)
            printf("> ");
         if(fgets(cmd_line, MAX_CMD_LINE + 1, input_stream) == NULL){
            break;
         } 
         
         
         sscanf(cmd_line, " %s ", cmd1);
         
         if(!strcmp(cmd1, "add_paragraph_after")){
            sscanf(cmd_line, " %s %s %s ", cmd1, cmd2, cmd3);
            
            /*Checks that cmd2 isn't missing*/
            if(*cmd2 == '\0'){
               valid_command = 0;
            }
            
            /*Checks that cmd2 represents a positive number*/
            ptr = cmd2;
            while(*ptr){
               /*If paragraph_number is negative, the command is invalid*/
               if(*ptr == '-'){
                  valid_command = 0;
                  break;
               }
               if(!isdigit(*ptr)){
                  valid_command = 0;
                  break;
               }
               ptr++;
            }
            
            /*Checks that there isn't additional information after cmd2*/
            if(*cmd3){
               valid_command = 0;
            }
            
            
            if(valid_command){
               paragraph_number = atoi(cmd2);
               if(add_paragraph_after(&doc, paragraph_number) == FAILURE){
                  printf("add_paragraph_after failed\n");
               }
            }
         }
         else if(!strcmp(cmd1, "add_line_after")){
            sscanf(cmd_line, " %s %s %s %s", cmd1, cmd2, cmd3, cmd4);
            
            /*Checks that paragraph_number isn't missing*/
            if(*cmd2 == '\0'){
               valid_command = 0;
            }
            
            /*Checks that line_number isn't missing*/
            if(*cmd3 == '\0'){
               valid_command = 0;
            }
            
            /*Checks that * isn't missing*/
            if(*cmd4 != '*'){
               valid_command = 0;
            }
            
            /*Checks that paragraph_number represents a positive number*/
            ptr = cmd2;
            while(*ptr){
               /*If paragraph_number is negative, the command is invalid*/
               if(*ptr == '-'){
                  valid_command = 0;
                  break;
               }
               if(!isdigit(*ptr)){
                  valid_command = 0;
                  break;
               }
               ptr++;
            }
            
            /*Checks that paragraph_number isn't 0*/
            paragraph_number = atoi(cmd2);
            if(paragraph_number == 0){
               valid_command = 0;
            }
            
            /*Checks that line_number represents a positive number*/
            ptr = cmd3;
            while(*ptr){
               /*If line_number is negative, the command is invalid*/
               if(*ptr == '-'){
                  valid_command = 0;
                  break;
               }
               if(!isdigit(*ptr)){
                  valid_command = 0;
                  break;
               }
               ptr++;
            }
            
            
            if(valid_command){
               int i = 0;
               ptr = strstr(cmd_line, "*");
               /*Skips the '*'*/
               ptr++;
               /*Copies the line to add in cmd4 without the '*'*/
               while(*ptr && *ptr != '\n'){
                  cmd4[i] = *ptr;
                  i++;
                  ptr++;
               }
               /*Null terminates cmd4*/
               cmd4[i] = '\0';
               line_number = atoi(cmd3);
               if(add_line_after(&doc, paragraph_number, line_number, cmd4) == FAILURE){
                  printf("add_line_after failed\n");
               }
            }
         }
         
         else if(!strcmp(cmd1, "print_document")){
            sscanf(cmd_line, " %s %s", cmd1, cmd2);
            /*If cmd2 is not null, then there is additional information print_document*/
            if(*cmd2){
               valid_command = 0;
            }
            else
               print_document(&doc);
         }
         
         /*If "quit" is not the only word on the line, it is an invalid command
           valid_command is then set to 0
         */
         else if(!strcmp(cmd1, "quit")){
            sscanf(cmd_line, " %s %s", cmd1, cmd2);
            /*If cmd2 is not null, then quit is not the only word on the line*/
            if(*cmd2){
               valid_command = 0;
            }
         }    
              
         else if(!strcmp(cmd1, "exit")){
            sscanf(cmd_line, " %s %s", cmd1, cmd2);
            /*If cmd2 is not null, then exit is not the only word on the line*/
            if(*cmd2){
               valid_command = 0;
            }
         }
         
         else if(!strcmp(cmd1, "append_line")){
            sscanf(cmd_line, " %s %s %s", cmd1, cmd2, cmd4);
            
            /*Checks that paragraph_number isn't missing*/
            if(*cmd2 == '\0'){
               valid_command = 0;
            }
            
            /*Checks that * isn't missing*/
            if(*cmd4 != '*'){
               valid_command = 0;
            }
            
            /*Checks that paragraph_number represents a positive number*/
            ptr = cmd2;
            while(*ptr){
               /*If paragraph_number is negative, the command is invalid*/
               if(*ptr == '-'){
                  valid_command = 0;
                  break;
               }
               if(!isdigit(*ptr)){
                  valid_command = 0;
                  break;
               }
               ptr++;
            }
            
            /*Checks that paragraph_number isn't 0*/
            paragraph_number = atoi(cmd2);
            if(paragraph_number == 0){
               valid_command = 0;
            }
            
            if(valid_command){
               int i = 0;
               ptr = strstr(cmd_line, "*");
               /*Skips the '*'*/
               ptr++;
               /*Copies the line to add in cmd4 without the '*'*/
               while(*ptr && *ptr != '\n'){
                  cmd4[i] = *ptr;
                  i++;
                  ptr++;
               }
               /*Null terminates cmd4*/
               cmd4[i] = '\0';
               if(append_line(&doc, paragraph_number, cmd4) == FAILURE){
                  printf("append_line failed\n");
               }
            }
         }
         else if(!strcmp(cmd1, "remove_line")){
            sscanf(cmd_line, " %s %s %s %s", cmd1, cmd2, cmd3, cmd4);
            
            /*Checks that paragraph_number isn't missing*/
            if(*cmd2 == '\0'){
               valid_command = 0;
            }
            
            /*Checks that line_number isn't missing*/
            if(*cmd3 == '\0'){
               valid_command = 0;
            }
            
            /*Checks that additional information doesn't appear after line number*/
            if(*cmd4){
               valid_command = 0;
            }
            
            /*Checks that paragraph_number represents a positive number*/
            ptr = cmd2;
            while(*ptr){
               /*If paragraph_number is negative, the command is invalid*/
               if(*ptr == '-'){
                  valid_command = 0;
                  break;
               }
               if(!isdigit(*ptr)){
                  valid_command = 0;
                  break;
               }
               ptr++;
            }
            
            /*Checks that paragraph_number isn't 0*/
            paragraph_number = atoi(cmd2);
            if(paragraph_number == 0){
               valid_command = 0;
            }
            
            /*Checks that line_number represents a positive number*/
            ptr = cmd3;
            while(*ptr){
               /*If line_number is negative, the command is invalid*/
               if(*ptr == '-'){
                  valid_command = 0;
                  break;
               }
               if(!isdigit(*ptr)){
                  valid_command = 0;
                  break;
               }
               ptr++;
            }
            
            /*Checks that line_number isn't 0*/
            line_number = atoi(cmd3);
            if(line_number == 0){
               valid_command = 0;
            }
            
            
            if(valid_command){
               if(remove_line(&doc, paragraph_number, line_number) == FAILURE){
                  printf("remove_line failed\n");
               }
            }
         }
         else if(!strcmp(cmd1, "load_file")){
            sscanf(cmd_line, " %s %s %s", cmd1, cmd2, cmd3);
            
            /*Checks that Filename isn't missing*/
            if(*cmd2 == '\0'){
               valid_command = 0;
            }
            
            /*If additional inforamtion appers after filename, the command is invalid*/
            if(*cmd3){
               valid_command = 0;
            }

            if(valid_command){
               if(load_file(&doc, cmd2) == FAILURE){
                  printf("load_file failed\n");
               }
            }
         }                            
         else if(!strcmp(cmd1, "replace_text")){
            int i = 0;          
            ptr = strstr(cmd_line, "\""); 
            /*If ptr is null then a " wasn't found on the line. Hence, both "TARGET" and "REPLACEMENT" are missing*/ 
            if(ptr == NULL) 
               valid_command = 0; 
             
            /*If the first " was found then we copy every character after the " until the next " into cmd2*/ 
            if(valid_command){ 
               /*Skips the first "*/ 
               ptr++; 
               while(*ptr != '\0' && *ptr != '"'){ 
                  cmd2[i] = *ptr; 
                  i++; 
                  ptr++; 
               } 
               cmd2[i] = '\0'; 
               /*If ptr is not pointing to a " then the second " wasn't found on the line. Hence, the command is invalid*/ 
               if(*ptr == '"'){ 
                  /*Skips the second "*/ 
                  ptr++; 
                  /*Finds the third "*/ 
                  ptr = strstr(ptr, "\""); 
                  /*If ptr is null then the third " wasn't found on the line. Hence, "REPLACEMENT" is missing*/ 
                  if(ptr == NULL) 
                     valid_command = 0; 
                  /*If the third " was found then we copy every character after the " until the last " into cmd3*/ 
                  if(valid_command){ 
                     i = 0; 
                     /*Skips the third "*/ 
                     ptr++; 
                     while(*ptr != '\0' && *ptr != '"'){ 
                        cmd3[i] = *ptr; 
                        i++; 
                        ptr++; 
                     } 
                     cmd3[i] = '\0'; 
                     /*If ptr is not pointing to a " then the last " is missing, hence the command is invalid*/ 
                     if(*ptr != '"') 
                        valid_command = 0;
                  
                     /*If there is another " on the line the command is invalid*/ 
                     ptr++;
                     ptr = strstr(ptr, "\"");
                     if(ptr != NULL){
                        valid_command = 0;
                     }
                  } 
               } 
               else 
                  valid_command = 0; 
            } 
                
            if(valid_command){ 
               if(replace_text(&doc, cmd2, cmd3) == FAILURE){ 
                  printf("replace_text failed\n"); 
               } 
            } 
         } 
         else if(!strcmp(cmd1, "highlight_text")){
            int i = 0;
            ptr = strstr(cmd_line, "\"");
            /*If ptr is null then a " wasn't found on the line. Hence, "TARGET" is missing*/
            if(ptr == NULL)
               valid_command = 0;
            
            /*If the first " was found then we copy every character after the " until the last " into cmd2*/
            if(valid_command){
               /*Skips the first "*/
               ptr++;
               while(*ptr != '\0' && *ptr != '"'){
                  cmd2[i] = *ptr;
                  i++;
                  ptr++;
               }
               cmd2[i] = '\0';
               /*If ptr is not pointing to a " then the last " wasn't found on the line. Hence, the command is invalid*/
               if(*ptr != '"')
                  valid_command = 0;
            }
            
            if(valid_command){
               highlight_text(&doc, cmd2);
            }
         } 
         else if(!strcmp(cmd1, "remove_text")){
            int i = 0;
            ptr = strstr(cmd_line, "\"");
            /*If ptr is null then a " wasn't found on the line. Hence, "TARGET" is missing*/
            if(ptr == NULL)
               valid_command = 0;
            
            /*If the first " was found then we copy every character after the " until the last " into cmd2*/
            if(valid_command){
               /*Skips the first "*/
               ptr++;
               while(*ptr != '\0' && *ptr != '"'){
                  cmd2[i] = *ptr;
                  i++;
                  ptr++;
               }
               cmd2[i] = '\0';
               /*If ptr is not pointing to a " then the last " wasn't found on the line. Hence, the command is invalid*/
               if(*ptr != '"')
                  valid_command = 0;
            }
            
            if(valid_command){
               remove_text(&doc, cmd2);
            }
         } 
         else if(!strcmp(cmd1, "save_document")){
            sscanf(cmd_line, " %s %s %s", cmd1, cmd2, cmd3);
            
            /*Checks that Filename isn't missing*/
            if(*cmd2 == '\0'){
               valid_command = 0;
            }
            
            /*If additional inforamtion appers after filename, the command is invalid*/
            if(*cmd3){
               valid_command = 0;
            }

            if(valid_command){
               if(save_document(&doc, cmd2) == FAILURE){
                  printf("save_document failed\n");
               }
            }
         } 
         else if(!strcmp(cmd1, "reset_document")){
            sscanf(cmd_line, " %s %s", cmd1, cmd2);
            /*If there is additional information after reset_document, the command is invalid*/
            if(*cmd2){
               valid_command = 0;
            }
            if(valid_command){
               reset_document(&doc);
            }
         }
         
         /*The line is a comment*/
         else if(!strcmp(cmd1, "#")){
            valid_command = 1;
         }
         
         /*The line only contains whitespace characters and should be ignored*/
         else if(!strcmp(cmd1, "")){
            valid_command = 1;
         }
         
         /*If the first word isn't any of the previous cases, then the command is invalid.*/
         else{
           valid_command = 0;
         }
         
         if(!valid_command){
         printf("Invalid Command\n");
      }
   /*The program will keep reading inputs from stdin until cmd1 is either quit or exit and there isn't additional information after*/
   }while(!valid_command || (strcmp(cmd1, "quit") && strcmp(cmd1, "exit")));
}