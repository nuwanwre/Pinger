//Mystore3.java
//This Program sells various fruits
//Nuwan Werellagama

import java.util.Scanner;

public class MyStore3 {
	
	private static int check = 0;	//checks whether checkout has been performed
	private static StringBuilder checkOut = new StringBuilder(); // collects the data for final output
	
	private static int quantity;
	private static int total = 0;

	private static String[] fruitNames = {"Apples","Bananas","Pears"};
	private static char[] fruitLetters = {'A','B','P'};
	private static int[] fruitPrices = {2000,3000,2500};
	
	private static int numFruits = 3;
	
	private static int fruitType = -1;
	
	//This function prints the menu
	public static void printMenu()
	{
		System.out.println("In this store we sell:");
		
		for(int count = 0; count < numFruits; count++)
		{
			System.out.printf("%c) %s for %d won\n",fruitLetters[count],fruitNames[count],fruitPrices[count]);
		}
		System.out.println("C) Checkout");
	}
	
	public static void getQuantity()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("How many do you want? ");
		quantity = input.nextInt();
		
		//Format the string for final output
		checkOut.append(" ");
		checkOut.append(quantity);
		checkOut.append(" ");
		checkOut.append(fruitNames[fruitType]);		
	}
	
	public static void calculateTotal()
	{
		total = total + fruitPrices[fruitType]*quantity;
	}
	
	
	//This function asks for which type of fruit to buy
	public static void getFruitType()
	{
		Scanner input = new Scanner(System.in);
		
		String chosenFruitString;
		char chosenFruitChar;
		
		System.out.print("What kind of fruit do you want :");
		chosenFruitString = input.nextLine();
		chosenFruitChar = chosenFruitString.charAt(0);
		
		
		if (chosenFruitChar == 'C' ){
			check = 1;
			return;
		}
		else{
			for (int  i = 0; i < numFruits ; i++)
			{
				if(fruitLetters[i] == chosenFruitChar)
				fruitType = i;
			}
			checkOut.append("\nYou purchased");
			
		}
	
	}
	
	//This method prints an error message
	public static void printError()
	{
		System.out.println("Error : no such fruit");
	}
	
	//This function prints the total
	public static void printPurchaseDetails()
	{	
			System.out.print(checkOut);
			System.out.printf("\nThe total is: %d\n",total);
	}	
	public static void main(String[] args) {
		
		while(true){
			fruitType = -1;
			printMenu();
			getFruitType();
		
			if(check == 1) break;
				
			else if (fruitType == -1) printError();
			
			else
			{
				getQuantity();
				calculateTotal();
			}
					
		}
	printPurchaseDetails();
	}
}