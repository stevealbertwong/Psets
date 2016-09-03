#include "ArrayList.h"

int main(int argc, char const *argv[])
{
	ArrayList list;
	list.add(34);
	list.add(78);
	list.insert(23, 2);
	int index = list.get(2);

	return 0;
}