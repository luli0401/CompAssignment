
public class BankClient
{
	String firstName;
	String lastName;
	float id;

	public BankClient(String firstName, String lastName, float id)
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

	public float getId()
	{
		return id;
	}

	public String toString()
	{
		return firstName + " " + lastName + " (" + id + ")";
	}
}
