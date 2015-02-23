import java.util.Scanner;
public class Calc {
	
	public static void main(String[] args) {
		for(int x = 20; x>=-2; x--){
			for(int y=-2;y<=20;y++){
				if(x>=0 && y==-1) System.out.printf("%d", x%10);
				else if(x>=0 && y==-2)System.out.printf("%d", x/10);
				else if(y>=0 && x==-2) System.out.printf("%d", y%10);
				else if(y>=0 && x==-1)System.out.printf("%d", y/10);
				else System.out.printf(" ");
			}
		System.out.printf("\n");
		}
	}

	}


