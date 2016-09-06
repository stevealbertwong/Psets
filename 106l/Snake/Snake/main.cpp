//
//  main.cpp
//  Snake
//
//  Created by Steven Wong on 9/4/16.
//  Copyright © 2016 Steven Andrew Wong. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <deque>
#include <cstdlib>
#include <ctime>
using namespace std;

const size_t kNumFood = 20;
const char kEmptyTile = ' '; // Empty space
const char kWallTile  = '#'; // Wall
const char kFoodTile  = '$'; // Food
const char kSnakeTile = '*'; // Snake start location/current position

// in seconds, between frames
const double kWaitTime = 200;

// data structure that represents a point
struct pointT {
    size_t row, col;
    pointT(size_t row, size_t col){
        this->row = row;
        this->col = col;
    }
    
    // default constructor if not pass in parameters
    pointT(){
        this->row = 0; // or just row = col = 0;
        this->col = 0;
    }
};



// bundle all game data so we could easily pass in as 1 parameter
struct gameT {
    vector<string> board; // same as vector<vector<char> >
    deque<pointT> snake; // snake is used to update board
    int snakeLength = 1;
    int boardHeight;
    int boardWidth;
    int dx;
    int dy;
};


// if hit " " -> clear + update deque snake + board
void goSnake(pointT node, gameT& game){
    
    game.snake.push_front(node);
    game.board[node.row][node.col] = kSnakeTile;
    game.board[game.snake.back().row][game.snake.back().col] = kEmptyTile; // clear back
    game.snake.pop_back();
    
}

// if hit $
void growSnake(pointT node, gameT& game){
    
    game.snake.push_front(node);
    game.board[node.row][node.col] = kSnakeTile;
    ++game.snakeLength;

}

// introduce probabiliity element here
pointT getNextPosition(gameT& game){
    
    pointT firstNode = game.snake.front();
    // RAND_MAX macro expands to an integral constant expression whose value
    // is the maximum value returned by the rand function
    // rand()/RAND_MAX > 0.5 -> gives you any number between 0-1 -> standard expression to use prob
    size_t randNum= rand() % 4;
    
    // 1-4: up down left right
    // go down
    if (randNum == 0) {
        firstNode.row += game.dx;
        firstNode.col += game.dy;
    }
    
    // go up
    if (randNum == 1){
        firstNode.row -= game.dx;
        firstNode.col += game.dy;
    }
    // turn left
    if (randNum == 2){
        firstNode.row += game.dy;
        firstNode.col -= game.dx;
    }
    // turn right
    if (randNum == 3){
        firstNode.row += game.dy;
        firstNode.col += game.dx;
    }
    
    return firstNode;
    
}



void printBoard(gameT& game){
    // loop through snake to get (row, col) -> board[row][[col] = kSnakeTile
    for (int i = 0; i < game.board.size(); ++i) {
        cout << game.board[i] << endl;
    }
}



// calculate the new points + check if crashed + grow the snake -> UPDATES SNAKE -> UPDATES BOARD
void performAI(gameT& game){
    
    // normal case -> keep going
    pointT nextNode = getNextPosition(game);
    
    size_t r = nextNode.row;
    size_t c = nextNode.col;
    
    // if hit " " -> use nextNode to update game.snake
    if (game.board[r][c] == kEmptyTile){
        goSnake(nextNode, game);
    }
    
    // if hit $
    if (game.board[r][c] == kFoodTile){
        growSnake(nextNode, game);
    }
    
    // if hit wall# or yourself* -> do nothing
    
    
    // print the updated Snake into Board
    printBoard(game);

}

// this function runs for kWaitTime seconds
void pauseFor2Seconds(){
    clock_t start = clock();
    // marco -> number of clock tics per second
    // Clock ticks are units of time of a constant but system-specific length
    // Dividing a count of clock ticks by CLOCKS_PER_SEC yields num seconds
    while ((start - clock())/CLOCKS_PER_SEC < kWaitTime);
}


void runSimulation(gameT& game){
    
    // while (length < kMaxFood)
    while (game.snakeLength < kNumFood){
        printBoard(game);
        performAI(game);
        pauseFor2Seconds();
    }

}


// level.txt -> ifstream input -> getline/ifstream >> int var -> struct board
void initializeBoardFromFile(gameT& game){
    // seed randomizer, for rand()
    srand(static_cast<unsigned int>(time(NULL)));
    
    
    ifstream input;
    input.open("level.txt");
    if (!input.is_open()){
        cerr << "failed to open file" << endl;
    };
    
    
    // from stream to program's data structure
    input >> game.boardHeight >> game.boardWidth; // initialize struct fields from level.txt
    input >> game.dx >> game.dy;
    game.board.resize(game.boardHeight);
    //    string dummy;
    //    getline(input, dummy);
    
    for (size_t row =0; row < game.boardHeight; ++row) {
        getline(input, game.board[row]);
        // cout << row << game.board[row] << endl;
        size_t col = game.board[row].find('*'); // return first character of first match
        // if not found -> return -1 or 1xxxxxxxxxxxxx in size_t
        if(col != string::npos){
            // cout << col << "row:" << row << endl;
            game.snake.push_back(pointT(row, col));
            // cout << game.snake[0].row << endl;
        }

    }
//    cout << game.boardWidth << game.boardHeight << game.dx << game.dy << endl;
    
    
}

int main() {
    gameT game;
    initializeBoardFromFile(game); // open txt + getline/ifstream >> int var
    runSimulation(game); // keep looping while we run out of food
    

    return 0;
}
