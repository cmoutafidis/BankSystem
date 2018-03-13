
package askisi1.model;

/**
 * @author Charis
 */
public class Customer {
    private long waitTimeInSeconds = 0;
    private int ticket;
    
    public Customer(long waitTimeInSeconds){
        this.waitTimeInSeconds = waitTimeInSeconds;
    }

    public long getWaitTimeInSeconds() {
        return waitTimeInSeconds;
    }
    
    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
}
