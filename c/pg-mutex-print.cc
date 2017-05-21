/*
see: http://stackoverflow.com/questions/18277304/using-stdcout-in-multiple-threads


*/

#include <iostream>
#include <thread>
#include <mutex>
using namespace std;

class Printer
{

public:
	void exec(){
		// mutex m;
		// m.lock();
		std::lock_guard<std::mutex> lock(m); 
		cout << " hello " << endl;
		// m.unlock();
	}

private:
	static mutex m;

};

mutex Printer::m;

int main(int argc, char const *argv[])
{
	Printer printer;
	// mutex Printer::m; // ==> error: definition or redeclaration of 'm' not allowed inside a function	
	
	thread t1([&printer](){
		while(1)
			printer.exec();
	});

	t1.join();


	return 0;
}


// class Printer
// {
// public:
//     void exec()
//     {
//         mutex m;
//         m.lock();
//         cout<<"Hello  "<<this_thread::get_id()<<endl;
//         chrono::milliseconds duration( 100 );
//         this_thread::sleep_for( duration );
//         m.unlock();

//     }
// };

// int main()
// {
//     Printer printer;

//     thread firstThread([&printer](){
//         while(1)
//             printer.exec();

//     });
//     thread secondThread([&printer](){
//         while(1)
//             printer.exec();
//     });

//     firstThread.join();
//     secondThread.join();

//     return 0;     
// }

// class bar {
// public:
//   void foo() {
//     std::cout << "hello from member function" << std::endl;
//   }
// };

// int main()
// {
//   thread t(&bar::foo, bar());
//   t.join();
// }