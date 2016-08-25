#include "BinaryTree.h"
#include <iostream>
#include <string>


BinaryTree::BinaryTree(TreeNode* initialRoot){
	root = initialRoot;
}

void BinaryTree::print(){
	TreeNode* node = new TreeNode();
	while(node != NULL){
		cout << node->data << eml;
		print(node->left);
		print(node->right);
	}
};


// your implmentation, however wrong recursion, instead
// you were just walking down the tree like linked list
// linked list vs binary tree ??
// the difference is node cannot store 2 address at the same time
// so we recursively call the same function to traverse the list
bool BinaryTree::contains(int value){
	
	while(node != NULL){
		if (node->data == value) {
			return true
		}else{
			node = node->left;
			node = node->right;
		}
	};
	return false;
};	


// ?? why same name, why root
bool BinaryTree::contains(int value){
	return contains(root, value);
};

bool BinaryTree::contains(TreeNode* node, int value){
	if (node = NULL){
		return false;
	} else if (node->data ==value){
		return true;

	}else{
		return contains(node->left) || contains(node->right);
	}
}



// Constructing a binary tree
// Construct subtree first since they dont have subtrees then parent tree
TreeNode* lr = new TreeNode(4);
TreeNode* ll = new TreeNode(5);
TreeNode* l = new TreeNode(1, ll, lr);

TreeNode* r = new TreeNode(6);
TreeNode* Root = new TreeNode(10, l, r);

void BinaryTree::appendRight(int layer, int value){
	map.append(layer, value);
	node = node->right
}

void BinaryTree::printSideways(TreeNode* node){
	int i = 1; // layer of map
	while(node !=NULL){
		append(i, value);
		i++;
		appendRight(node->right);
		appendLeft(node->left);
	}
	// 
	// store a map with layer and value
	// a 3 layer tree -> 3231323

}

