/*
25th Feb
see: youtube DRfraser  

threads 4 arguments
-> tid
-> attribute
-> function
-> arg to function

pass in and return data

multiple thread


16th May

multiple args
see: https://computing.llnl.gov/tutorials/pthreads/#PassingArguments
see: http://stackoverflow.com/questions/1352749/multiple-arguments-to-function-called-by-pthread-create

*/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>


struct arg_struct {
  int arg1;
  int arg2;
};

int sum = 0;

// pthread requires function of void* type + take void* arg
void* f_sum(void* arg){
  /*   int sum=0; */  
  
  // int limit = *(int *)arg; // typecast void* to int* => then dereference  
  
  // struct arg_struct* args = arg;
  printf("BEFORE IT STARTS : limit %d form thread %d \n", ((struct arg_struct*)arg)->arg1, ((struct arg_struct*)arg)->arg2 +1);
  struct arg_struct* args = (struct arg_struct*)arg;
  int thread_no = args->arg2 +1;
  int limit = args->arg1;

  // every spawned off thread has its own copy of stack variable
  // struct arg_struct args = *(struct arg_struct*)arg;
  // int thread_no = args.arg2 +1;
  // int limit = args.arg1;

  
  for(int i = 0; i <= limit; i++){
    sum += i;
    printf("sum %d form thread %d with addr of limit: %x \n", sum, thread_no, &limit);
    
  }
  printf("Total Sum is %d after thread %d finishes\n", sum, thread_no);
  pthread_exit(0); // free pthread data structure on heap that stored f_sum ?
}



int main(int argc, char** argv){
  // int f_sum_arg = atoi(argv[1]);
  int num_args = argc - 1;
  /*   int f_sum_arg[num_args]; */

  /*   for(int i = 0; i < num_args; i++){ */
  /*     int f_sum_arg[i] = atoi(argv[i+1]); */
  /*   } */
  pthread_t tids[num_args];
  
  // default attribute => same for every thread
  pthread_attr_t attr;
  pthread_attr_init(&attr);


  

  // spawn threads
  // this loop finishes before threads_runnable runs
  for(int i = 0; i < num_args; i++){
    
    // why crazily large? => seems you yourself playing with pointer arithmetic
    // printf("arg input after type cast + pointer arithmetic %d \n", *(int *)argv[i+1]);
        
    int f_sum_arg = atoi(argv[i+1]);

    // dynamic allocated => creates copies for threads to access
    struct arg_struct* args = malloc(sizeof(struct arg_struct));
    args->arg1 = f_sum_arg;
    args->arg2 = i;

    printf("create thread %d passing in arg %d with args stored at addr %x\n", i+1, f_sum_arg, args);    
    // pthread_create(&tids[i], &attr, f_sum, &f_sum_arg);
    pthread_create(&tids[i], &attr, f_sum, (void *)args);
    // sleep(1);
    printf("\n");
  }

  for(int i = 0; i < num_args; i++){
    pthread_join(tids[i], NULL); // other threads wait for tid finish, but usually spawn threads in parallel
  }
  printf("total sum : %d", sum);
}
