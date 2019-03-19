package Comp1020_A3;
// BankClient class
public class BankClient
{
	// instance variables
	String firstName;
	String lastName;
	long id;

	// Constructor
	public BankClient(String firstName, String lastName, long id)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	// Accessor methods
	public String getFirstName()
	{
		return firstName;
	}

	// Accessor methods
	public String getLastName()
	{
		return lastName;
	}

	// ToString methods
	public String toString()
	{
		return firstName + " " + lastName + " (" + id + ")";
	}
}
