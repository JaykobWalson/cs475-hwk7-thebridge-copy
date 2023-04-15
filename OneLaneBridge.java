public class OneLaneBridge extends Bridge{
    private Object conditional = new Object();
    private int amountOfCars;
    private int limit;
    
    public OneLaneBridge(int cars, int blimit){
        this.amountOfCars = cars;
        this.limit = blimit;
    }
    /**
     * Car arrives at the bridge.
     * @param car is the car that enters.
     */
    public void arrive(Car car) throws InterruptedException{
        synchronized(conditional){
            if(bridge.size() == 0){
                this.direction = car.getDirection();
            }
            try{
            car.setEntryTime(currentTime);
            this.direction = car.getDirection();
            while(this.direction != car.getDirection()||bridge.size() == limit){
                conditional.wait(); 
            }

            }
            catch(Exception e){
            System.out.println("ERR: Interrupted Exception.");
            }
            currentTime++;
            bridge.add(car);
        }

    }

/**
 * When a car exits the bridge.
 * @param car is the car leaving.
 */
    public void exit(Car car) throws InterruptedException{
        synchronized(conditional){
            if(bridge.size() < limit){
                conditional.notifyAll();
            }
            try{
            car.setEntryTime(currentTime);
            this.direction = car.getDirection();
            while(this.direction != car.getDirection()||bridge.size() == limit){
                conditional.wait(); 
            }

            }
            catch(Exception e){
            System.out.println("ERR: Interrupted Exception.");
            }
            currentTime++;
            bridge.remove(0);
            if(bridge.size() == 0){
                this.direction = !this.direction;//Way is not not false (true);
            }
        }
    }
       
}