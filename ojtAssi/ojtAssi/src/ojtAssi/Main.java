package ojtAssi;
import java.util.LinkedList;


public class Main {
	
	public static int incWashidx(int washidx,Vehicle [] vehicles, int curfueling,int remainingfueltime) {
		while(((!vehicles[washidx].wash) & washidx < vehicles.length-1)
				|| ( washidx==curfueling && washidx < vehicles.length-1)) {
			washidx++;
		}
		return washidx;
	}
	
	public static int incFuelidx(int fuelidx,Vehicle [] vehicles, int curwashing, int curfueling) {
		while((!vehicles[fuelidx].fuel & fuelidx < vehicles.length-1)
				| (curwashing==fuelidx&  fuelidx < vehicles.length-1)) {
			fuelidx++;
		}
		return fuelidx;
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		int [] washtime = {2,4,6};
		int [] fueltime = {1,3,5};
		String[] strType = {"motor", "private", "trailer"};
		int timer = 0;
		
		int fuelidx = 0, washidx = 0, remainingwashtime = 0, remainingfueltime = 0;
		Vehicle [] vehicles = new Vehicle[15];
		vehicles[0] = new Vehicle(0,0,true,true);
		vehicles[1] = new Vehicle(1,2,true,false);
		vehicles[2] = new Vehicle(2,0,true,true);
		vehicles[3] = new Vehicle(3,1,false,true);
		vehicles[4] = new Vehicle(4,1,false,true);
		vehicles[5] = new Vehicle(5,1,true,false);
		vehicles[6] = new Vehicle(6,1,true,false);
		vehicles[7] = new Vehicle(7,0,true,false);
		vehicles[8] = new Vehicle(8,0,true,false);
		vehicles[9] = new Vehicle(9,1,true,true);
		vehicles[10] = new Vehicle(10,2,false,true);
		vehicles[11] = new Vehicle(11,0,true,false);
		vehicles[12] = new Vehicle(12,1,true,false);
		vehicles[13] = new Vehicle(13,2,true,true);
		vehicles[14] = new Vehicle(14,2,true,false);
		
		LinkedList<Integer> fuelQueue = new LinkedList<Integer>();//fuel as soon as possible
		LinkedList<Integer> washQueue = new LinkedList<Integer>();//wash as soon as possible
		int washNow,fuelNow,//auxiliary variables to store head of queues
		curfueling = -1, curwashing = -1;//indexes of currently in service cars
		
		while(fuelidx < vehicles.length-1 ||  washidx < vehicles.length-1) {
			
			if(!washQueue.isEmpty() &&  remainingwashtime == 0) {
				//quick wash
				washNow= washQueue.pollFirst();
				remainingwashtime = washtime[vehicles[washNow].type];
				System.out.print("Vehicle "+vehicles[washNow].id +" with type "+strType[vehicles[washNow].type] +" starts cleaning in time "+ timer+".\r\n");	     
			}
			
			if(!fuelQueue.isEmpty() &&  remainingfueltime == 0) {
				//quick fuel
				fuelNow = fuelQueue.pollFirst();
				curfueling = fuelNow;
				remainingfueltime = fueltime[vehicles[fuelNow].type];
				System.out.print("Vehicle "+vehicles[fuelNow].id +" with type "+strType[vehicles[fuelNow].type] +" starts refueling in time "+ timer+".\r\n");
			}
			
			fuelidx = incFuelidx(fuelidx,vehicles, curwashing,curfueling);
			if(vehicles[fuelidx].fuel && remainingfueltime == 0) {
				//fuel
				remainingfueltime = fueltime[vehicles[fuelidx].type];
				curfueling = fuelidx;
				System.out.print("Vehicle "+vehicles[fuelidx].id +" with type "+strType[vehicles[fuelidx].type] +" starts refueling in time "+ timer+".\r\n");
			}
				
			
			washidx = incWashidx(washidx,vehicles,curfueling,remainingfueltime);
			if(vehicles[washidx].wash && remainingwashtime == 0 && washidx!= fuelidx) {
				//wash
				remainingwashtime = washtime[vehicles[washidx].type];
				curwashing = washidx;
				System.out.print("Vehicle "+vehicles[washidx].id +" with type "+strType[vehicles[washidx].type] +" starts cleaning in time "+ timer+".\r\n");	
			}
			
			
			if(remainingfueltime > 0) {
				remainingfueltime--;
				if(remainingfueltime == 0) {
					vehicles[curfueling].fuel = false;
					
					if(vehicles[curfueling].wash) {
						washQueue.add(curfueling);
						vehicles[curfueling].wash = false;
					}
				}
			}
			
			if(remainingwashtime > 0) {
				remainingwashtime--;
				if(remainingwashtime == 0) {
					vehicles[curwashing].wash = false;
					
					if(vehicles[curwashing].fuel) {
						fuelQueue.add(curwashing);
						vehicles[curwashing].fuel =false;
					}
				}
			}
			timer++;
		}
	}
	

}
