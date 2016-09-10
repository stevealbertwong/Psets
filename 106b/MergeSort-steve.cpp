// since recusively calculating new leftHead and midDistance
void mergeSort(int* arr, int leftHead, int length){

	if(length == 1){
		break;
	}

	// get leftHead, midDistance -> rightHead i.e. another start of the array

	else{
		int lh = leftHead; // 0 
		int len = length/2; //4
		int rh = lh + len; // 4
		cout << "leftHead: " << lh << "length:" << len << "rightHead:" << rh << endl; 

		mergeSort(arr, lh, len)
		mergeSort(arr, rh, len)
	}
}

int main(int argc, char const *argv[])
{
	int* arr[] = {8,4,2,7,1,6,3,5};
	int leftHead = 0;
	int length = sizeof(arr);
	int midDistance = sizeof(arr)/2;
	int rightHead = leftHead + midDistance;

	mergeSort();
	return 0;
}