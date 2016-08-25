/********************************************************
 * File: wiki-graph.cpp
 *
 * Code to play around with the Wikipedia graph.
 */
#include <iostream>
#include <string>
#include <fstream>
#include "hashmap.h"
#include "set.h"
#include "stack.h"
#include "queue.h"
#include "vector.h"
#include "simpio.h"
using namespace std;

/* Reads in the Wikipedia data. */
void readData(Vector<string>& articles, Map<string, int>& articleToIndex, Map<int, Vector<int> >& links) {
	/* Read article names into the first two parameters. */
	ifstream articlesFile("articles.txt");
	while (true) {
		string article;
		getline(articlesFile, article);
		
		if (articlesFile.fail()) break;
		
		articles += article;
		articleToIndex[article] = articles.size() - 1;
	}
	cout << "Read " << articles.size() << " articles." << endl;
	
	/* Read all of the links into the map. */
	ifstream linksFile("links.txt");
	int numLinks = 0;
	while (true) {
		int start;
		int end;
		
		linksFile >> start >> end;
		if (linksFile.fail()) break;
		
		links[start] += end;
		numLinks ++;
	}
	
	cout << "Read " << numLinks << " links." << endl;
}

/* Runs a depth-first search recursively over the graph to try to find
 * a path from curr to end.  If one is found, the path is updated to
 * hold the complete path.
 *
 * I made a slight error when coding this up in lecture.  The logic to
 * reconstruct the path has been updated.
 */
bool recDFS(int curr, int end,
            Map<int, Vector<int>>& links,
            Set<int>& visited,
            Stack<int>& path) {
	/* If we've already visited this node, then stop searching.
	 * We're going in circles.
	 */
	if (visited.contains(curr)) return false; 
	visited += curr;
	
	/* If we are at the end, then the path is very simple - just
	 * don't move anywhere!
	 */
	if (curr == end) {
		path.push(curr);
		return true;
	}
	
	/* Otherwise, see if we can take a step that leads us to the
	 * destination.
	 */
	foreach (int linkedArticle in links[curr]) {
		if (recDFS(linkedArticle, end, links, visited, path)) {
			path.push(curr);
			return true;
		}
	}
	
	return false;
}

void dfs(int start, int end,
         Vector<string>& articleTitles,
         Map<int, Vector<int>>& links) {
    Set<int> visited;
    Stack<int> path;
    
    if (recDFS(start, end, links, visited, path)) {
    	while (!path.isEmpty()) {
    		cout << articleTitles[path.pop()] << endl;
    	}
    } else {
    	cout << "No luck this time. >_<" << endl;
    }
}

int main() {
	/* Pull the Wikipedia data into the program. */
	Vector<string> articleTitles;
	Map<string, int> articleToIndex;
	Map<int, Vector<int> > links;
	readData(articleTitles, articleToIndex, links);
	
	/* Get two article names, then find a path between them. */
	while (true) {
		string first = getLine("Enter article title: ");
		if (!articleToIndex.containsKey(first)) {
			cout << "Sorry, I don't know that article." << endl;
			continue;
		}
		
		string second = getLine("Enter article title: ");
		if (!articleToIndex.containsKey(second)) {
			cout << "Sorry, I don't know that article." << endl;
			continue;
		}
		
		/* Run a depth-first search between the articles. */
		dfs(articleToIndex[first], articleToIndex[second], articleTitles, links);
	}
}
