/*

1. multi-threading dining philosophers

*/

#include <iostream>
#include <thread>


using namespace std;
const unsigned int kNumPhilosophers = 2;


void think(){
	cout << " i am thinking " << endl;
}

int main(int argc, char const *argv[])
{
	thread philosophers[kNumPhilosophers];
	for (unsigned int i = 0; i < kNumPhilosophers; i++){
		philosophers[i] = thread(think);
	}
	
	for (thread& p: philosophers) {
		p.join();
	}
    
	return 0;
}