#include <iostream>
#include "console.h"
#include "simpio.h"
using namespace std;

static const int DAT_FREQ = 3;
static const int YA_FREQ  = 8;

int main() {
    setConsoleFont("Arial-20");
//    int daAmount;               // we replace this with the simpler getInteger()
//    cout << "How much Da? ";
//    cin >> daAmount;
    int daAmount = getInteger("How much Da? ");
    cout << "I will send a fully armed battalion to remind you of my love! ";
    for (int i = 0; i < daAmount; i++) {
        if (i % DAT_FREQ == 0) cout << "Dat Da ";
        else if (i % YA_FREQ == 0) cout << "Ya Da ";
        else cout << "Da ";
    }
    cout << endl;

    string favoriteLyric = getLine("What is your favorite Hamilton lyric? ");
    favoriteLyric += " [APPLAUSE]";
    cout << favoriteLyric << endl;

    return 0;
}

