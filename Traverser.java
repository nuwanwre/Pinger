//Traverser.java
//This program an array of integers from an external file does some stuff
//Nuwan Werellagama

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Traverser {

	static Scanner input;
	static String filename;
	static File file;
	static StringBuilder temp = new StringBuilder();
	static int count = 0;
	
	public static void main(String[] args) {
		
		System.out.println("======== Traverser 1.02=======");
		System.out.println("If the element is 0, only 1 step is taken forward");
		System.out.println("If the element is negative, one step is taken forward");
		System.out.printf("\n");
		
		System.out.print("Enter file name : ");
		input = new Scanner(System.in);
		filename = input.nextLine();
		try{
			file = new File(filename);
			input = new Scanner(file);
		
			while(input.hasNext())
			{
				temp.append(input.nextLine());
				count ++;
			}
		}
		catch(FileNotFoundException exception)
		{
			System.out.println("Error : File not found.");
		}
		
		int hold[] = new int[count+1];
		
		if (count != 0){		
			for(int i =0; i<count;i++){
				hold[i] = temp.charAt(i)- 48;	
			}
			int j = 0,k = 0;
			
			while(j<=count)
			{
				System.out.printf("%d,",j);
				k = hold[j];
				if (k == 0)  j++;
				if (k < 0) j++;
				else j =  j + k;
				
				
			}
			System.out.printf("out.\n");
		}		
		System.out.println("===== Traverser now quitting ======");
	}

}