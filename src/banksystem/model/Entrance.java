package banksystem.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Charis
 */
public class Entrance extends Thread {

    private Random random = null;
    private int customers = 0;
    private Bank bank = null;
    private final int id;
    private final int minWait = 0;
    private final int maxWait = 1;
    private int counter = 1;

    public Entrance(Bank bank, int b, int id) {
        this.random = new Random();
        this.customers = b;
        this.bank = bank;
        this.id = id;
    }

    @Override
    public void run() {
        while (this.customers != 0) {
            long time = (long) random.nextInt(this.maxWait - this.minWait + 1) + this.minWait;
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            bank.addCustomerToQueue(new Customer((long) random.nextInt(this.maxWait - this.minWait + 1) + this.minWait), this.id, (this.maxWait - this.minWait), (this.counter++));
            this.customers--;
        }

    }

    public int getEntranceId() {
        return this.id;
    }

    public int getCounter() {
        return this.counter;
    }

}
