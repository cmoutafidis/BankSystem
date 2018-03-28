
package banksystem.model;

import java.util.concurrent.TimeUnit;

/**
 * @author Charis
 */
public class Employee extends Thread{
    
    private Customer currentCustomer;
    private Bank bankWorkingTo = null;

    public Employee(Bank bank){
        this.bankWorkingTo = bank;
    }
    
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(this.currentCustomer.getWaitTimeInSeconds());
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("Done with customer with ticket " + this.currentCustomer.getTicket() + ".");
        this.removeCustomer();
    }
    
    public void setCustomer(Customer customer){
        this.currentCustomer = customer;
    }
    
    public void removeCustomer(){
        this.currentCustomer = null;
        this.bankWorkingTo.transactionIsOver(this);
    }

}
