//Rectangle.java
//This is Rectangle class
//Nuwan Werellagama



public class Rectangle {
	
	double top;
	double bottom;
	double left;
	double right;
	
	//-------------------------------------Constructors
	public Rectangle(double newLeft,double newRight, double newTop, double newBottom){
		//if(newTop>=newBottom) { top = newTop; bottom= newBottom;}
		//else System.out.println("Sorry, The length of Top should be greater than or equal to Bottom length.");
		//if(newLeft<=newRight) { left= newLeft; right = newRight;}
		//else System.out.println("Sorry, The length of Left should be less than or equal to Right length.");
		setBottomTop(newBottom,newTop);
		setLeftRight(newLeft,newRight);
	}
	
	public Rectangle(){
		left = 1; right =2; bottom = 1; top = 2;
	}
	
	//-------------------------------------Set value methods
	public void setBottomTop(double newBottom,double newTop){
		
		if(newTop>=newBottom) {top = newTop; bottom = newBottom;}
		else System.out.println("Sorry, The length of Top should be greater than or equal to Bottom length.");
		
	}
		
	public void setLeftRight(double newLeft,double newRight){
		
		if(newLeft<=newRight) {left = newLeft; right = newRight;}
		else System.out.println("Sorry, The length of Left should be less than or equal to Right length.");
		
	}
	
	public void setTop(double newTop){
		
		if(newTop>=bottom) top = newTop;
		else System.out.println("Sorry, The length of Top should be greater than or equal to Bottom length.");
	}
	
	public void setBottom(double newBottom){
		
		if(newBottom<=top) bottom = newBottom;
		else System.out.println("Sorry, The length of Top should be greater than or equal to Bottom length.");		
	}
	
	public void setLeft(double newLeft){
		
		if(newLeft<=right) left = newLeft;
		else System.out.println("Sorry, The length of Left should be less than or equal to Right length.");
	}
	
	public void setRight(double newRight){
		
		if(newRight>=left) right = newRight;
		else System.out.println("Sorry, The length of Left should be less than or equal to Right length.");
	}
	
	//-------------------------------Return Value Methods
	public double getTop(){ return top;}
	public double getBottom(){ return bottom;}
	public double getLeft(){ return left;}
	public double getRight(){ return right;}
	public double getHeight(){ return (top-bottom);}
	public double getWidth() { return (right-left);}
	public double getArea() { return (((top+bottom)/2)*getHeight());}
	
	boolean insideRectangle(double x, double y){
		if (((x<right) && (x>left)) && ((y<top) && ( y>bottom))) return true;
		else return false;
	}
}
