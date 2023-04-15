/**
 * Runs all threads
 */

public class BridgeRunner {

	public static void main(String[] args) {

		int arg0 = 0;//char to int
		int arg1 = 0;//char to int


			if(args[0] == null || args[1] == null){
				System.out.println("Please enter a valid number.");
			}
		
			arg0 = Integer.parseInt(args[0]);
			arg1 = Integer.parseInt(args[1]);
			if(arg0 <= 0 || arg1 <= 1){
				System.out.println("Please enter an integer greater than 0");
			}
		try{	
			System.out.println(arg0 + ", " + arg1);
			OneLaneBridge b = new OneLaneBridge(arg0, arg1);
			
			Thread[] listOfThreads = new Thread[arg1];
			for(int i = 0; i < arg1; i++){
				listOfThreads[i] = new Thread(new Car(i, b));
				listOfThreads[i].start();//starting up the threads
			}
			for(int i = 0; i < arg1; i++){
				listOfThreads[i].join();//joining threads.
			}
			System.out.println("All cars have crossed!");


		}
		catch (Exception e){
			System.err.println("error");
		}
	

	}

}