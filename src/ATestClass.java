public class ATestClass
{
	public static void main(String[] args)
	{
		
	}

	public static void reverse(int[] data)
	{
		int temp = 0;

		for (int i = 0; i < data.length / 2; i++)
		{
			temp = data[i];
			data[i] = data[data.length - i - 1];
			data[data.length - i - 1] = temp;
		}
		
		System.out.print("DONE");
	}
}
