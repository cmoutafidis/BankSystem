package banksystem.model;

import java.util.concurrent.TimeUnit;

/**
 * @author Charis
 */
public class Employee extends Thread {

    private Customer currentCustomer;
    private Bank bankWorkingTo = null;
    private final int id;
    private int counter;

    public Employee(Bank bank, int id, int counter) {
        this.bankWorkingTo = bank;
        this.id = id;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(this.currentCustomer.getWaitTimeInSeconds());
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        this.removeCustomer();
    }

    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public void removeCustomer() {
        System.out.println("                                                            Emp: " + this.id + " #" + this.counter++ + ", T:" + this.currentCustomer.getTicket());
        this.currentCustomer = null;
        this.bankWorkingTo.transactionIsOver(this);
    }

    public int getEmpId() {
        return this.id;
    }

    public int getCounter() {
        return this.counter;
    }

}
