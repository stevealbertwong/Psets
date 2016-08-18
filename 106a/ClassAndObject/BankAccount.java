/* Class and Object Example Lecture 18*/



/* Class = field + method + constructor 

A Class is a template/factory and data type that is created by you

An object is an instance of a class

This code does not run on itself but to help WellsFargoClient.java

A class's state and behavior depends a lot on 
which object you call the method on and refers to such object's data field

You could put different kinds of data into a class and methods 
to use such data
*/
public class BankAccount {
	
	// usually private so that only deposit(double amount){ balance += amount}
	// could amend balance but not martyAccount.balance(1000.0);
	// but when you set it private you could not init martyAccount.balance = 12345;
	// that's why you build a constructor

	// fields
	private String name;
	private double balance;


	// constructor
	// code that runs when the object is being created
	// BankAccount martyAccount = new BankAccount(); + martyAccount.balance = 1000.00; vs 
	// BankAccount martyAccount = new BankAccount("Marty", 1000.00)
	public BankAccount(String initialName, double initialBalance){
		name = initialName;
		balance = initialBalance;
	}


	// instance/object method
	// this is how you cout in java println(martyAccount.printBalance()) in WellsFargoClient.java
	public string printBalanace(){
		return "My name is" + name + "and my balance is" + balance;

	}

	
}


// ?? printf vs println
// ?? why String type is capitalized
