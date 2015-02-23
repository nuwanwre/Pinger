
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double total = 10;
		
		while(true){
			if(total<0) break;
			
			else {
				System.out.printf("%f ",total);
				for(int i =0;i<3;i++){
					total = total+(total*0.1);
					System.out.printf("%f ",total);
				}
			}
		}
	}

}
