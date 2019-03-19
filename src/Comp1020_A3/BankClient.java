package Comp1020_A3;

public class BankClient
{
	String firstName;
	String lastName;
	long id;

	public BankClient(String firstName, String lastName, long id)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public long getId()
	{
		return id;
	}

	public String toString()
	{
		return firstName + " " + lastName + " (" + id + ")";
	}
}
