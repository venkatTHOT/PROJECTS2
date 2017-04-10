

#define HASH_TABLE_SIZE 20
#define MAX 1024
#define ZERO 0
#define ONE 1
#define TWO 2
#define THREE 3
union data Data;

enum optcode {opt_addStu=1,opt_addCor,opt_modStu,opt_modCor,opt_delStu,opt_delCor,opt_idata};

union data
{
	int key;
	
	
	
	struct initialData
	{
		
		int roll;
		float CGPA;
		char name[MAX];
		int number_of_subjects;
		
		struct subjects_details
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

/************** function to create hashtable**************/
void read(char *fn1,int );




















