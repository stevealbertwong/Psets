/*
 * File: ParkingRandomly.cpp
 * ---------------
 * A program that attempts to determine how many cars can park on a curb
 * of a certain length, based on certain assumptions on how cars can park.
 */

#include <iostream>
#include "random.h"
#include "gparking.h" // Routines for displaying the cars
using namespace std;

/* Constant controlling the size of the road. */
const double kRoadLength = 10;

/* Simulates one iteration of parking randomly, returning
 * how many cars could be placed.
 */
int parkRandomly(double start, double end);

int main() {
	initParkingDisplay(kRoadLength);

	/* Count up how many trials we've done and how many total cars have been
	 * placed.
	 */
	int numTrials = 0;
	int numTotalCars = 0;
	
	while (true) {
		/* Clear the display. */
		removeAllCars();
		numTrials++;
		
		/* Simulate one iteration of parking randomly. */
		numTotalCars += parkRandomly(0, kRoadLength);
		
		/* Update the display to show the average number of cars
		 * based on the trials we've done so far.
		 */
		displayAverage(double(numTotalCars) / numTrials);
	}
}

int parkRandomly(double start, double end) {
	/* Base case: If there is no room for a car, then no
	 * cars can park here.
	 */
	if (end - start < 1.0) {
		return 0;
	}
	/* Recursive step: Place a car randomly, then recursively
	 * park cars in the two halves.
	 */
	else {
		double pos = randomReal(start, end - 1.0);
		placeCar(pos);
		return 1 + parkRandomly(start, pos)
		         + parkRandomly(pos + 1.0, end);
	}
}







