
package askisi1.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Charis
 */
public class Entrance extends Thread{
    
    private Random random = null;
    private int customers = 0;
    private Bank bank = null;
    
    public Entrance(Bank bank, int b){
        this.random = new Random();
        this.customers = b;
        this.bank = bank;
    }

    @Override
    public void run() {
        while(this.customers != 0){
            long time = (long)random.nextInt(1 - 0 + 1) + 0;
            try {
            TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            bank.addCustomerToQueue(new Customer((long)random.nextInt(1 - 0 + 1) + 0));
            this.customers--;
        }
        
    }

}
