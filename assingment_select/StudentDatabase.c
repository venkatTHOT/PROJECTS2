#include <stdio.h>
#include <string.h>   //strlen
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>   //close
#include <arpa/inet.h>    //close
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/time.h>
#include "StudentDatabase.h"

#define TRUE   1
#define FALSE  0
#define PORT 8001


struct hashtable *hashTable=NULL;
struct subjects_details *subj_head;
int hashindex=0;

void recieve(int master_socket,int client_socket[],int max_clients,struct sockaddr_in address,char *fn2)
{

	
	int i,number_of_subjects=ZERO,number_bytes=ONE,key=ZERO;
	int k=ZERO,flag=ONE,count=ZERO;
	FILE  *fp1=NULL;
	createHashtable();
	char *temp;
	struct node *newnode;
	int client_sock; 
	fd_set readfds;
	int max_sd;
	int  new_socket , activity,valread , sd,addrlen;
	char buffer[1025];  //data buffer of 1K
	 addrlen = sizeof(address);
    	puts("Waiting for connections ...");


    while(TRUE) 
    {
        //clear the socket set
        FD_ZERO(&readfds);
  
        //add master socket to set
        FD_SET(master_socket, &readfds);
        max_sd = master_socket;
         
        //add child sockets to set
        for ( i = 0 ; i < max_clients ; i++) 
        {
            //socket descriptor
            sd = client_socket[i];
             
            //if valid socket descriptor then add to read list
            if(sd > 0)
                FD_SET( sd , &readfds);
             
            //highest file descriptor number, need it for the select function
            if(sd > max_sd)
                max_sd = sd;
        }
  
        //wait for an activity on one of the sockets , timeout is NULL , so wait indefinitely
        activity = select( max_sd + 1 , &readfds , NULL , NULL , NULL);
    
        if ((activity < 0) && (errno!=EINTR)) 
        {
            printf("select error");
        }
          
        //If something happened on the master socket , then its an incoming connection
        if (FD_ISSET(master_socket, &readfds)) 
        {
            if ((new_socket = accept(master_socket, (struct sockaddr *)&address, (socklen_t*)&addrlen))<0)
            {
                perror("accept");
                exit(EXIT_FAILURE);
            }
          
            //inform user of socket number - used in send and receive commands
            printf("New connection , socket fd is %d , ip is : %s , port : %d \n" , new_socket , inet_ntoa(address.sin_addr) , ntohs(address.sin_port));
        
         

		//recieve database information


		while(1)
		
		{	number_bytes=recv(new_socket,(union data *)&Data, sizeof(Data),0);
			key=ntohs(Data.key);
			
			
			
				number_bytes=recv(new_socket,(union data *)&Data, sizeof(Data),0);
				
				if(key==ZERO)
				{	display(fn2);
					break;
				}
				else if(key==opt_addStu)
				{
				
					newnode=insertStudent(ntohs(Data.addStu.roll),Data.addStu.name,Data.addStu.CGPA,ntohs(Data.addStu.number_of_subjects));
					continue;
				}
				else if(key==opt_addCor)
				{
			
					addCourse(ntohs(Data.addCor.roll),ntohs(Data.addCor.coursecode),ntohs(Data.addCor.marks));
					continue;
				}
		
				else if(key==opt_modStu)
				{
			
					modifyStudent(ntohs(Data.modStu.roll),Data.modStu.CGPA);
					continue;
				}
				else if(key==opt_modCor)
				{	
					modifyCourse(ntohs(Data.modCor.roll),ntohs(Data.modCor.coursecode),ntohs(Data.modCor.marks));
					continue;
				}
				else if(key==opt_delStu)
				{
			
					 deleteStudent(ntohs(Data.delStu.roll));
					continue;
				}
				else if(key==opt_delCor)
				{
			
					deleteCourse(ntohs(Data.delCor.roll),ntohs(Data.delCor.coursecode));
					continue;
				}
				
				else if(key==opt_idata)
				{		
			
					newnode=insertStudent(ntohs(Data.iData.roll),Data.iData.name,Data.iData.CGPA,ntohs(Data.iData.number_of_subjects));
		
					for(i=ZERO;i<ntohs(Data.iData.number_of_subjects);i++)
					{
							subjectDetails(newnode,ntohs(Data.iData.subDetails[i].coursecode),ntohs(Data.iData.subDetails[i].marks));
							subj_head=NULL;

					}
								
								
					continue;
				}
			
		


				
		}

          }
        //else its some IO operation on some other socket :)
        for (i = 0; i < max_clients; i++) 
        {
            sd = client_socket[i];
              
            if (FD_ISSET( sd , &readfds)) 
            {
                //Check if it was for closing , and also read the incoming message
                if ((valread = read( sd , buffer, 1024)) == 0)
                {
                    //Somebody disconnected , get his details and print
                    getpeername(sd , (struct sockaddr*)&address , (socklen_t*)&addrlen);
                    printf("Host disconnected , ip %s , port %d \n" , inet_ntoa(address.sin_addr) , ntohs(address.sin_port));
                      
                    //Close the socket and mark as 0 in list for reuse
                    close( sd );
                    client_socket[i] = 0;
                }
                  
                
               
            }
        }
    }

}









		/************** function to create hashtable**************/
void createHashtable()
{
	hashTable=(struct hashtable*)calloc(HASH_TABLE_SIZE,sizeof(struct hashtable));

}

		/************** insertion **************/

	/************** For Student **************/

/************** insert/add student **************/

struct node * insertStudent(int roll,char *name,float CGPA,int number_of_subjects)
{
	struct node *newnode,*temp=NULL;
	 hashindex=roll%HASH_TABLE_SIZE;

        temp=hashTable[hashindex].head;
	while(temp!=NULL)
	{
		
		if(temp->roll==roll)
			return temp;
		temp=temp->next;

	}



	struct node *new_node=createNode(roll,name,CGPA,number_of_subjects);
	
	if(!hashTable[hashindex].head)
	{	
		hashTable[hashindex].head=new_node;
		
	}
	else
	{
		new_node->next=hashTable[hashindex].head;
		hashTable[hashindex].head=new_node;
		hashTable[hashindex].count++;
				
	}
	return new_node;
}



void addCourse(int roll ,int coursecode,int marks)
{
	struct node *temp;
	struct subjects_details *s_temp;
        hashindex=roll%HASH_TABLE_SIZE;
        temp=hashTable[hashindex].head;

        while(temp!=NULL)
        {
                if(temp->roll==roll)
                {
			s_temp=temp->head;
                        struct subjects_details *newnode;
			newnode=createNode1(coursecode,marks);
			newnode->next=s_temp;
			temp->head=newnode;
			break;
                }
                temp=temp->next;
        }

}



struct node * createNode(int roll,char *name,float CGPA,int number_of_subjects)
{
	
	struct node *newnode;
	
	//display();

	newnode=(struct node *)malloc(sizeof(struct node ));
	newnode->roll=roll;
	newnode->CGPA=CGPA;
	strcpy(newnode->name,name);
	newnode->number_of_subjects=number_of_subjects;
	newnode->next=NULL;
	return newnode;
}


		/************** subject details **************/
void subjectDetails(struct node *newnode_node,int coursecode,int marks)
{
	struct subjects_details *newnode;
	newnode=createNode1(coursecode,marks);
	
	if(!subj_head)
	{
		subj_head=newnode;
		newnode_node->head=subj_head;
		
	}
	else
	{	
		newnode->next=subj_head;
		subj_head=newnode;
		newnode_node->head=subj_head;
		
		
	}
	

}


struct subjects_details * createNode1(int coursecode,int marks)
{
			
	        struct subjects_details *new_node=(struct subjects_details *)malloc(sizeof(struct subjects_details));
		new_node->coursecode=coursecode;
        	new_node->marks=marks;
		new_node->next=NULL;
        	return new_node;
}


		/************** modification**************/

	/************** for student **************/	

/************** modify student **************/
void modifyStudent(int roll,float CGPA)
{
	struct node *temp;
	hashindex=roll%HASH_TABLE_SIZE;
	temp=hashTable[hashindex].head;
	if(!temp)
	{
		
		return;
	}
	while(temp!=NULL)
	{
		if(temp->roll=roll)
		{
			temp->CGPA=CGPA;
			break;
		}

	}
}



	/************** for course **************/

/************** modify course **************/

void modifyCourse(int roll ,int coursecode,int marks)
{
	struct node *temp;
	struct subjects_details *s_temp1;
        hashindex=roll%HASH_TABLE_SIZE;
        temp=hashTable[hashindex].head;
        if(!temp)
        {
              
                return;
        }

       while(temp!=NULL)
        {
                if(temp->roll==roll)
                {
                        s_temp1=temp->head;
			if(s_temp1->coursecode==coursecode)
			{
				s_temp1->marks=marks;
				
			}
			else
			{

		                while(s_temp1!=NULL)
		                {
		                        if(s_temp1->coursecode==coursecode)
		                        {
		                        	s_temp1->marks=marks;
						
		                        }
					
					s_temp1=s_temp1->next;
		                 }

                	}
		 }
                temp=temp->next;
         
	}
	

}


		/************** deletion **************/

	/************** for student **************/	

/************** delete student **************/
void deleteStudent(int roll)
{
       struct node *temp1,*temp2;
        hashindex=roll%HASH_TABLE_SIZE;
        temp1=hashTable[hashindex].head;
        if(!temp1)
        {
               
                return;
        }
	if(temp1->roll==roll)
	{
		hashTable[hashindex].head=temp1->next;
		free(temp1);
	}
	else
	{
        	while(temp1!=NULL)
        	{
                	if(temp1->roll==roll)
                	{
					
        			      temp2->next=temp1->next;
					free(temp1);
					break;     
                	}
			temp2=temp1;
			temp1=temp1->next;
			

        	}

	}

}


	/************** for course **************/

/************** delete course **************/
void deleteCourse(int roll,int coursecode)
{
	 struct node *temp;
        struct subjects_details *s_temp1,*s_temp2;
        hashindex=roll%HASH_TABLE_SIZE;
        temp=hashTable[hashindex].head;
        if(!temp)
        {
                
                return;
        }

        while(temp!=NULL)
        {
                if(temp->roll==roll)
                {
                        s_temp1=temp->head;
			if(s_temp1->coursecode==coursecode)
			{
				temp->head=s_temp1->next;
				free(s_temp1);
			}
			else
			{

		                while(s_temp1!=NULL)
		                {
		                        if(s_temp1->coursecode==coursecode)
		                        {
		                        	s_temp2->next=s_temp1->next;
						free(s_temp1);
		                         	break;
		                        }
					s_temp2=s_temp1;
					s_temp1=s_temp1->next;
		                 }

                	}
		 }
                temp=temp->next;
         
	}
	
}
 





		/************** display function**************/	
void display(char *fn2)
{

	struct node *temp;
	int i;
	FILE *fp2=NULL;
	fp2=fopen(fn2,"w");
	for(i=0;i<HASH_TABLE_SIZE;i++)
	{
		temp=hashTable[i].head;
		if(!temp)
			continue;
		else
		{
			while(temp!=NULL)
			{
				printf("%d, %s, %.1f, %d\n",temp->roll,temp->name,temp->CGPA,temp->number_of_subjects);
				fprintf(fp2,"%d, %s, %.1f, %d\n",temp->roll,temp->name,temp->CGPA,temp->number_of_subjects);
			        
				struct subjects_details *s_temp=temp->head;
				
				while(s_temp!=NULL)
				{
					printf("%d, %d\n",s_temp->coursecode,s_temp->marks);
					fprintf(fp2,"%d, %d\n",s_temp->coursecode,s_temp->marks);
					s_temp=s_temp->next;
				}
				temp=temp->next;

			}
			
		}
		
	}
	fclose(fp2);

}


	




		

      
