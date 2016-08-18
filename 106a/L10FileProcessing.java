/* L10 File Processing */

/*
File is a class
File object contains metadata of that file on hard drive

Absolute path i.e. specifies a drive or "/" top level folders
not prefered since program not portable 

Relative path i.e. does not specify top level folder e.g. "input/kinglear.txt" 
e.g. "res/readme.md"
Preferred way -> put the file in the same directory 

Scanner allows you to actually read a file, not just talk to its metadata

Token -> a unit of user input, separated by white space

Throw an exception -> runtime error
Catch an exception -> fix it 
Java wants to know what should we do if the file is not there
i.e. to execute code that might throw and exception, enclose it in a try/catch statement


*/


// create a file object/variable you could talk to that file
File f = new File("abc.txt"); 
Scanner sc = new Scanner(f);


// reading line by line
// moves forward until the scanner see a linebreak \n
while (input.hasNextLine()){
	String line = input.nextLine();
};

// reading token/word by token/word i.e. unit separated by whitespace
while (input.hasNext()){
	String token = input.next();
}
// reading integers

while (input.hasNextInt()){
	Int integer = input.nextInt();
}

//try catch java bad design
// have to wrap the file processing code with try catch 
try {
	Scanner input = new Scanner (new File("res/weather.txt"));
} catch (FileNotFoundException ex) {
	println ("Error reading the file:" + ex);
}



/* Token Exercises */

while (input.hasNextDouble()){
	double d1 = input.nextDouble();
	double d2 = d1;
	double difference = d1 - d2;
	int i = 0;
	i++;
	if i > 0{
		println(d1 + d2 + difference)
	}
}

while (input.hasNextDouble()){
	int i = 0;
	Array arr[i] = input.nextDouble();
	i ++ ;
	int j = i + 1;
	if (i > 1){
		Double difference = Array[j] - Array[i];
	}
}

/* Reading junk exercise */






