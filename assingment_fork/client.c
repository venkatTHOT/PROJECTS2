#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<string.h>

#include<arpa/inet.h>

int main(int argc, char **argv)
{
	
	//creating client_socket
	
	int socket_address=socket(AF_INET,SOCK_STREAM,0);
	
	//defining  socket address
	
	char *address;
	address =argv[2];

	struct sockaddr_in server_address;
	server_address.sin_family=AF_INET;
	server_address.sin_port=htons(atoi(argv[3]));
	inet_aton(address,&server_address.sin_addr.s_addr);	

	connect(socket_address,(struct sockaddr *) &server_address,sizeof(server_address) ); 
	
	read(argv[1],socket_address);
	
	char request[]="GET HTTP";
	char response[4096];
	
	
	return 0;


}



