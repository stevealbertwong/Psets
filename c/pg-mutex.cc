/*

Deadlock means thread is waiting for another thread to finish 
before it can acquires its mutex

see: http://www.technical-recipes.com/2015/creating-and-avoiding-deadlock-conditions-in-c/http://www.bogotobogo.com/cplusplus/multithreaded4_cplusplus11B.php

If thread A is executing and isn’t holding mutex lock 1 yet and 
thread B acquires mutex lock 2, neither of the threads can 
continue past the second lock acquisition

*/

#include <iostream>
#include <thread>


using namespace std;


 
static mutex mutex1, mutex2;
const unsigned int kNumThreads = 2;
 

/* 

mutex2.lock() => threadA is holding that lock, no other thread
could get the mutex2 (i.e. call mutex2.lock()) before threadA 
release the lock (i.e. mutex2.unlock()) so threadB could proceed
with mutex2.lock();

 */
void ThreadA()
{
    
    mutex2.lock();
    std::cout << "Thread A" << std::endl;
    // thread A is waiting for thread B to unlock mutex1 before it can call mutex1.lock();
    mutex1.lock(); // wait until mutex1 is unlocked
    std::cout << "Thread A has acquired mutex1" << std::endl;
    mutex2.unlock();
    mutex1.unlock();
}
 
void ThreadB()
{
    
    mutex1.lock();
    std::cout << "Thread B" << std::endl;
    // thread A is waiting for thread B to unlock mutex1 before it can call mutex2.lock();
    mutex2.lock(); // wait until mutex2 is unlocked
    std::cout << "Thread B has acquired mutex1" << std::endl;
    mutex1.unlock();
    mutex2.unlock();
}
void ExecuteThreads()
{
    
	// std::thread threadHandler[kNumThreads];
 //    threadHandler[0] = thread(ThreadA);
 //    threadHandler[1] = thread(ThreadB);

 //    // for all the threads in the thread list
 //    for (thread& p: threadHandler) p.join();
    thread t1(ThreadA);
    thread t2(ThreadB);

    t1.join();
    t2.join();
 
    std::cout << "joined" << std::endl;
}
 
int main()
{
    ExecuteThreads();
 
    return 0;
}