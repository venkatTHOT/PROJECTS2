
#include<stdio.h>
#include<math.h>
#include<string.h>
#include<stdlib.h>
#include "ClientFunction.h"




void read(char *fn1,int server_address)
{

	int ch,c,i,roll;
	int stop=ONE; 
	int key;
	int number_of_subjects,k=ZERO,flag=ONE,count=ZERO;
	
	FILE  *fp1=NULL;
	float CGPA;
	char name[MAX];
	char line[MAX]={ZERO};
	char *data1[MAX];
	
	fp1=fopen(fn1,"r");
	char *temp;
	char *token;
	
	struct node *newnode;
	if(fp1!=NULL)
		printf("file opened successfully.\n");
	else
	{
		printf("could not open file \n");
		exit(1);
	}
	
	while(fgets(line,sizeof(line),fp1)!=NULL)
	{
		line[strlen(line)-1]='\0';
		
		
		k=ZERO;
		if(!strcmp(line,"# initial addition of database"))
		{	flag=ONE;
			ch=ONE;c=THREE;
			stop=ZERO;
			Data.key=htons(opt_idata);
			key=htons(opt_idata);
			
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}
		else if(!strcmp(line,"# add students"))
		{
			ch=ONE;
			c=ONE;
			flag=ONE;stop=ZERO;
			Data.key=htons(opt_addStu);
			key=htons(opt_addStu);
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}
		else if(!strcmp(line,"# add course"))
		{
			ch=ONE;
			c=TWO;stop=ZERO;
			Data.key=htons(opt_addCor);
			key=htons(opt_addCor);
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}
		
		else if(!strcmp(line,"# modify student") || !strcmp(line,"# modify student "))
		{
			ch=TWO;
			c=ONE;stop=ZERO;
			Data.key=htons(opt_modStu);
			key=htons(opt_modStu);
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}
		else if(!strcmp(line,"# modify course"))
		{
			ch=TWO;
			c=TWO;stop=ZERO;
			Data.key=htons(opt_modCor);
			key=htons(opt_modCor);
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}
		else if(!strcmp(line,"# delete student"))
		{
			ch=THREE;
			c=ONE;stop=ZERO;
			Data.key=htons(opt_delStu);
			key=htons(opt_delStu);
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}
		else if(!strcmp(line,"# delete course"))
		{
			ch=THREE;
			c=TWO;stop=ZERO;
			Data.key=htons(opt_delCor);
			key=htons(opt_delCor);
			send(server_address,(void *) &(Data),sizeof(Data),0);
			continue;
		}

			
		token=strtok(line,", ");
		
		while(token !=NULL)
		{
			
			data1[k]=token;
			
			k++;
			token = strtok(NULL,", ");
		}
		if(stop!=ZERO)
			{
				Data.key=key;
				send(server_address,(void *) &(Data),sizeof(Data),0);
				stop=ZERO;
			}		
		switch(ch)
		{		
			case 1:
				
				switch(c)
				{
				
					case 1:	
					
						
						Data.addStu.roll=htons(atoi(data1[0]));
						 
						strcpy(Data.addStu.name,data1[1]);
						 
						Data.addStu.CGPA=atof(data1[2]);	
						Data.addStu.number_of_subjects=htons(atoi(data1[3]));		
						 
						send(server_address,(void *) &(Data),sizeof(Data),0);
						flag=ZERO;
						stop=ONE;
						break;
		
					case 2:
									
									
 
									Data.addCor.roll=htons(atoi(data1[0]));
									 
									Data.addCor.coursecode=htons(atoi(data1[1]));	
									Data.addCor.marks=htons(atoi(data1[2]));	
									send(server_address,(void *) &(Data),sizeof(Data),0);	
													
									stop=ONE;
                                               				 
                                               				 break;

					case 3:	
						if(flag==ONE)
							{	
											

										Data.iData.roll=htons(atoi(data1[0]));
										strcpy(Data.addStu.name,data1[1]);
										Data.iData.CGPA=atof(data1[2]);	
										Data.iData.number_of_subjects=htons(atoi(data1[3]));                                                    														
										
										 
										number_of_subjects=atoi(data1[3]);
										 
										flag=ZERO;
								
							}
						else

							{
										
										
										
										
										
										
										
										
										Data.iData.subDetails[count].coursecode=htons(atoi(data1[0]));
										

										Data.iData.subDetails[count].marks=htons(atoi(data1[1]));
										
										 
										count++;
										if(count==number_of_subjects)
										{	

											send(server_address,(void *) &(Data),sizeof(Data),0);
											flag=ONE;count=ZERO;stop=ONE;
											}									
										}	
									break;
					

						}break;

			case 2:
				switch(c)
				{
					case 1:
								

								Data.modStu.roll=htons(atoi(data1[0]));
								
								Data.modStu.CGPA=atof(data1[1]);	
								
						
										
									
								send(server_address,(void *) &(Data),sizeof(Data),0);stop=ONE;
								 
								break;
					case 2:
						 		
								
								Data.modCor.roll=htons(atoi(data1[0]));
								Data.modCor.coursecode=htons(atoi(data1[1]));	
								Data.modCor.marks=htons(atoi(data1[2]));
	
								send(server_address,(void *) &(Data),sizeof(Data),0);stop=ONE;
				                               				
				                                break;

						
						}
				break;

			case 3:
                                
                                switch(c)
                                {
                                        case 1:
						
						Data.delStu.roll=htons(atoi(data1[0]));
						send(server_address,(void *) &(Data),sizeof(Data),0);   
						stop=ONE;                        
                                                
                                                break;
                                        case 2:
                                                
						Data.delCor.roll=htons(atoi(data1[0]));
						Data.delCor.coursecode=htons(atoi(data1[1]));
						send(server_address,(void *) &(Data),sizeof(Data),0);stop=ONE;
                                                
                                                break;


                                }
                                break;
			
			case 4:
				exit(0);
		


			}
		


}
				close(server_address);
				fclose(fp1);
			
}




