import java.util.Random;

public class SortProgram {

	static long[] list;
	static int size;

	public static void printList()
	{
		int i;
		for (i = 0; i< size; i ++)
		{
			System.out.printf("%d",list[i]);
		}
		System.out.printf("\n");
	}

	public static void generateSortedList()
	{
		list = new long[size];
		for(int i =0; i<size;i++)
		{
			list[i]=i;
		}
	}	
	
	public static void generateReverseSortedList()
	{
		list = new long[size];
		for(int i =0; i<size;i++)
		{
			list[i]=4-i;
		}
	}		
	
	public static void generateRandomList()
	{
		list = new long[size];
		Random randomNumbers = new Random();
		
		for(int i=0;i<size;i++)
		{
			list[i] = randomNumbers.nextInt(size);
		}
	}


	public static void main(String [] args)
	{
		size = 5;
		generateRandomList();
		printList();
	}
}
