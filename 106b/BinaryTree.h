#ifndef BINARYTREE_H
#define BINARYTREE_H

#include <string>

using namespace std;



struct TreeNode {
	int data;
	TreeNode* left;
	TreeNode* right;

	TreeNode(int d = 0, TreeNode* l = NULL, TreeNode* r = NULL ){
		this->data = d;
		this->left = l;
		this->right = r;
	}

	bool isLeaf(){
		return left = NULL && right = NULL;
	}
};



class BinaryTree {

// members of the class 
private:
	TreeNode* root;

public:
	BinaryTree(TreeNode*);

	void print();
	bool contains(int);
	void printSideways();
};