#include "BinaryTree.h"

using namespace std;

int main(){

	TreeNode* node = new TreeNode(59);
	node->right = new TreeNode(39);
	node->left = new TreeNode(123);

	BinaryTree tree = new BinaryTree(node);
	
}