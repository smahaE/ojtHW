package ojtAssi;

public class Vehicle {
	int id;
	int type; // 0-motor 1-private 2-trailer
//	int service; //1-fuel 2-wash 3-fuel and wash
	boolean fuel;
	boolean wash;
	
	
	public Vehicle(int vid, int vtype,boolean vfuel,boolean vwash) {
		id = vid;
		type = vtype;
		fuel= vfuel;
		wash = vwash;
	}
	
	public void setVecData(int id, int type,boolean fuel,boolean wash) {
		id = id;
		type = type;
		fuel= fuel;
		wash = wash;
		
	}

}

