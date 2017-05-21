#include <stdio.h>

int main(int argc, char *argv[])
{

    char *names[] = {
        "Alan", "Frank",
        "Mary", "John", "Lisa"
    };



    // first way using indexing
    for (int i = 0; i < 5; i++) {
        printf("%s \n", names[i]);
    }


    return 0;
}
