

#define HASH_TABLE_SIZE 20
#define MAX 1024
#define ZERO 0
#define ONE 1
#define TRUE   1
#define FALSE  0

enum optcode {opt_addStu=1,opt_addCor,opt_modStu,opt_modCor,opt_delStu,opt_delCor,opt_idata};

union data Data;
union data{
	
	int key;
	struct initialData
	{
		
		int roll;
		float CGPA;
		char name[MAX];
		int number_of_subjects;
		struct subj_details
		{
	                int coursecode;
	                int marks;
		        
		}subDetails[MAX];

		
	}iData;
	
	struct addStudent
	{
		
		int roll;
		float CGPA;
		char name[MAX];
		int number_of_subjects;
		
	}addStu;
	
	struct addCourse
	{
		
		int roll;
		int coursecode;
		int marks;
	}addCor;	

	struct modifyStudent
	{
		
		int roll;
		float CGPA;
	}modStu;
	
	struct modifyCourse
	{
		
		int roll;
		int coursecode;
		int marks;
	
	}modCor;

	struct deleteStudent
	{
		
		int roll;

	}delStu;
	
	struct deleteCourse
	{
		
		int roll;
		int coursecode;
		int marks;
	}delCor;

			
};




			/************** STRUCTURES **************/

/************** Structure for collecting student information in structure node **************/

struct node
{
	int roll;
	char name[1000];
	float CGPA;
	int number_of_subjects;
	struct subjects_details *head; 
	struct node *next;

};

/************** Structure for collecting subject information in structure subjects_details **************/

struct subjects_details
{
                int coursecode;
                int marks;
		struct subjects_details *next;        
};

/************** Structure for storing hashed roll number in node head by making HashTable**************/
struct hashtable
{
	struct node *head;
	int count;
};

	
			/************** FUNCTIONS**************/

/************** function to create hashtable**************/
//void read(char *fn1,char *fn2);
void createHashtable();

		/************** insertion **************/

	/************** For Student **************/

/************** add student **************/

struct node * insertStudent(int roll,char *name,float CGPA,int number_of_subjects);

struct node * createNode(int roll,char *name,float CGPA,int number_of_subjects);

	/************** for course **************/

/************** add course **************/

void addCourse(int roll ,int coursecode,int marks);

		/************** subject details **************/
void  subjectDetails(struct node *,int coursecode,int marks);

struct subjects_details * createNode1(int coursecode,int marks);


		/************** modification**************/

	/************** for student **************/	

/************** modify student **************/
void modifyStudent(int roll,float CGPA);


	/************** for course **************/

/************** modify course **************/

void modifyCourse(int roll ,int coursecode,int marks);

		/************** deletion **************/

	/************** for student **************/	

/************** delete student **************/
void deleteStudent(int roll);


	/************** for course **************/

/************** delete course **************/
void deleteCourse(int roll,int coursecode);


		/************** display function**************/	
void display(char *fn);

		


void recieve(int master_socket,int client_socket[],int max_clients,struct sockaddr_in address,char *fn2);


















