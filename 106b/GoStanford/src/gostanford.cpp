/**************************************************
 * gostanford.cpp
 *
 * Cynthia Lee (c) 2013
 *
 * This program simulates the sports fans' cheer
 * of "Go Stanford!", in which sections of the
 * bleachers take turns yelling "Go!" and "Stanford!"
 *
 * It illustrates the principle of mutual recursion,
 * or two functions that call each other.
 ***************************************************/

#include<iostream>
#include "console.h"
#include "simpio.h"
using namespace std;

static void go(int nrepetitions);
static void stanford(int nrepetitions);

/* main()
 *
 * Asks the user how many times to repeat the cheer,
 * then performs the cheer.
 */
int main(){
    setConsoleFont("Arial-20");
    go(10);
    return 0;
}

/* go()
 *
 * Prints "Go!" and then calls stanford() for
 * the second half of the cheer.
 *
 * repetitions - the (remaining) number of times
 *               to repeat the cheer
 */
static void go(int nrepetitions) {
    if (nrepetitions <= 0) return;
    cout << "Go!" << endl;
    stanford(nrepetitions - 1);
}

/* stanford()
 *
 * Prints "Stanford!" and then calls go() to
 * continue repeating the cheer.
 *
 * repetitions - the (remaining) number of times
 *               to repeat the cheer
 */
static void stanford(int nrepetitions) {
    cout << "Stanford!" << endl;
    go(nrepetitions);
}
