//RectangleProgram.java
//This class uses the Rectangle.java
//Nuwan Werellagama



import java.util.Scanner;

public class RectangleProgram {

	private static Rectangle myRectangle;
	static char commandLetter;
	static double newLocation;
	
	public static void main(String[] args) {
		
		myRectangle = new Rectangle();
		
		while(true)
		{
			printAxes();
			printRectangleDetails();
			printMenu();
			getCommand();
			if (commandLetter == 'Q') break;
			else {
			getNewLocation();
			updateRectangle();
			}
		}

	}
	
	public static void printRectangleDetails(){
		System.out.printf("Rectangle info:\n\t Left = %04f, Right = %04f, Bottom = %04f, Top = %04f\n\t Width = %04f, Height = %04f, Area = %04f\n",
				myRectangle.getLeft(),myRectangle.getRight(),myRectangle.getBottom(),myRectangle.getTop(),myRectangle.getWidth(),myRectangle.getHeight(),
				myRectangle.getArea());
	}
	
	public static void printMenu(){
		System.out.printf("Commands:\n\tT) Change Top\n\tB) Change Bottom\n\tL) Change Left\n\tR) Change Right\n\tQ) Quit\n");
			}
	
	public static void getCommand(){
		String temp;
		Scanner input = new Scanner(System.in);
		System.out.print("What do you want to do ?");
		temp = input.nextLine();
		commandLetter = temp.charAt(0);
	}

	public static void getNewLocation(){
		//Scanner input = new Scanner(System.in);
		//System.out.print(" : ");
		//newLocation = input.nextFloat();
		Scanner input = new Scanner(System.in);
		
		if (commandLetter =='T') { System.out.print("What should the top become ? "); newLocation = input.nextFloat();}
		if (commandLetter =='B') { System.out.print("What should the bottom become ? "); newLocation = input.nextFloat();}
		if (commandLetter =='L') { System.out.print("What should the left become ? "); newLocation = input.nextFloat();}
		if (commandLetter =='R') { System.out.print("What should the right become ? "); newLocation = input.nextFloat();}

	}
		
	public static void updateRectangle(){
		
		if (commandLetter =='T') myRectangle.setTop(newLocation);
		if (commandLetter =='B') myRectangle.setBottom(newLocation);
		if (commandLetter =='L') myRectangle.setLeft(newLocation);
		if (commandLetter =='R') myRectangle.setRight(newLocation);
	}
	
	public static void printAxes(){
		boolean start = false;
		
		for(int x = 20; x>=-2; x--){
			for(int y=-2;y<=20;y++){
				if(x>=0 && y==-1){System.out.printf("%d", x%10);
						if ((x==myRectangle.getTop())){ 
							start = true;
													}
						
						if (start== true && x>=myRectangle.getBottom()){
								for(int i = 0;i<myRectangle.getRight();i++){
									System.out.print("*");
								}
						}
						
				}
				else if(x>=0 && y==-2)System.out.printf("%d", x/10);				
				else if(y>=0 && x==-2)System.out.printf("%d", y%10);
				else if(y>=0 && x==-1)System.out.printf("%d", y/10);
				else System.out.printf(" ");
			}
		System.out.printf("\n");
		}
	}

}

	
