/*
https://www.youtube.com/watch?v=FNc_nWbSMM8
Sierra College CSCI 46 "System Programming in C"
*/

#include <sys/socket.h>

const int kPortNum = 1234;

int main(int argc, char const *argv[])
{

	// Create a socket 
	int sockFD = socket(AF_INET, SOCK_STREAM, 0);

	serverAddress.sin_family = AF_INET;
	serverAddress.sin_port = htons(port);

	// Bind a socket 

	// Listen for connection
	return 0;
}
