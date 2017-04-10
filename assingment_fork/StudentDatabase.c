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

void recieve(int master_socket,struct sockaddr_in address,char *fn2)
{

	
	
	int client_socket,len;
	struct sockaddr_in cli;
	 pid_t pid;
	puts("Listening for Client...");
 	for(; ;)
 	{
		len=sizeof(cli);
		//accepting client connection
		client_socket=accept(master_socket,(struct sockaddr*)&cli,&len);
		puts("\nConnected to Client...");
		//creating child process
		if((pid=fork()) == 0)
		{
		
			puts("Child process created...");
			close(master_socket);
			str_echo(client_socket,fn2);
			close(client_socket);
			exit(0);
		}
		close(client_socket);
 	}

 }


		
/*str echo */
 
void str_echo(int client_socket,char *fn2)
{


	int i,number_of_subjects=ZERO,number_bytes=ONE,key=ZERO;
	int k=ZERO,flag=ONE,count=ZERO;
	FILE  *fp1=NULL;
	createHashtable();
	char *temp;
	struct node *newnode;


	puts("Message from Client...");
	/*sending student information */

	while(1)
		
		{	number_bytes=recv(client_socket,(union data *)&Data, sizeof(Data),0);
			key=ntohs(Data.key);
			
			
			
				number_bytes=recv(client_socket,(union data *)&Data, sizeof(Data),0);
				
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






		

      
