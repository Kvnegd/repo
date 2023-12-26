#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sysexits.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#define MAX_CMD_LINE 1024
#define MAX_STACK 16


int main(){

   int valid_command, pid, total_dir = 0;
   char cmd_line[MAX_CMD_LINE + 1] = "", cmd1[MAX_CMD_LINE + 1] = "", cmd2[MAX_CMD_LINE + 1] = "", dir[MAX_CMD_LINE] = "", *argv[3];
   char *dir_stack[MAX_STACK];


   do{
         valid_command = 1;
         *cmd_line = '\0';
         *cmd1 = '\0';
         *cmd2 = '\0';
         *dir = '\0';

         printf("shell_jr: ");
         fflush(stdout);
         
         if(fgets(cmd_line, MAX_CMD_LINE + 1, stdin) == NULL){
            break;
         }

         sscanf(cmd_line, " %s ", cmd1);

         if(!strcmp(cmd1, "exit")){
            printf("See you\n");
         }
         else if(!strcmp(cmd1, "goodbye")){
            printf("See you\n");
         }
         
         else if(!strcmp(cmd1, "cd")){
            sscanf(cmd_line, " %s %s", cmd1, cmd2);
            chdir(cmd2);
         }
         else if(!strcmp(cmd1, "pushd")){
            if(total_dir < MAX_STACK){
               sscanf(cmd_line, " %s %s", cmd1, cmd2);
            
               getcwd(dir, MAX_CMD_LINE); 
               dir_stack[total_dir] = malloc(strlen(dir) + 1);
               strcpy(dir_stack[total_dir], dir);
               total_dir++;
             
               chdir(cmd2);
            }
            else
               printf("Directory stack is full\n");
         }

         else if(!strcmp(cmd1, "dirs")){
            int i;
            for(i = 0; i < total_dir; i++){
               printf("%s\n", dir_stack[i]);
            }
         }
         
         else if(!strcmp(cmd1, "popd")){
            if(total_dir != 0){
               chdir(dir_stack[--total_dir]);
               free(dir_stack[total_dir]);
            }
            else
               printf("Directory stack is empty\n");
         }
         else{
            pid = fork();
            if(pid){ /*Parent process*/
               int status;
               wait(&status);
               if(WEXITSTATUS(status)) /*If WEXITSTATUS is not 0 then something went wrong in the child*/
                  valid_command = 0;
            }
            else{ /*Child process*/
               sscanf(cmd_line, " %s %s", cmd1, cmd2);
               argv[0] = cmd1;
               argv[1] = cmd2;
               argv[2] = NULL;
               execvp(argv[0], argv);
               /*If we are still in this process after execvp, then command failed which means the command doesn't exist*/
               exit(EX_OSERR);
            }
         }

         if(!valid_command){
         printf("Failed to execute %s\n", cmd1);
         fflush(stdout);
      }

   }while(!valid_command || (strcmp(cmd1, "goodbye") && strcmp(cmd1, "exit")));
   
   exit(0);
}

