package banksystem.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Charis
 */
public class Bank extends Thread {

    private int ticketCounter = 1;
    private volatile List<Customer> queue = null;
    private List<Employee> employeesWorking = null;
    private List<Employee> employeesWaiting = null;
    private volatile int bankLimit = 0;
    private boolean isBankOpen;

    public Bank(int c, int d) {
        this.queue = Collections.synchronizedList(new ArrayList());
        this.employeesWorking = Collections.synchronizedList(new ArrayList());
        this.employeesWaiting = Collections.synchronizedList(new ArrayList());
        this.isBankOpen = true;
        this.bankLimit = d;
        for (int i = 0; i < c; i++) {
            this.employeesWaiting.add(new Employee(this));
        }
    }
    
    @Override
    public void run() {
        while(true){
            if(!this.queue.isEmpty() && !this.employeesWaiting.isEmpty()){
                Customer curCustomer = null;
                Employee curEmployee = null;
                curEmployee = this.employeesWaiting.remove(0);
                this.employeesWorking.add(curEmployee);
                curCustomer = this.queue.remove(0);
                curEmployee.setCustomer(curCustomer);
                curEmployee.start();
                System.out.println("The customer with ticket number " + curCustomer.getTicket() + " will be occupied for " + curCustomer.getWaitTimeInSeconds() + " seconds.");
            }
            if(!(this.isBankOpen || !this.queue.isEmpty())){
                break;
            }
        }
    }
    
    public boolean isBankFull(){
        return this.queue.size() + this.employeesWorking.size() >= this.bankLimit;
    }
    
    public void openBank(){
        this.isBankOpen = true;
    }
    
    public void closeBank(){
        this.isBankOpen = false;
    }
    
    public synchronized void addCustomerToQueue(Customer customer) {
        while(this.isBankFull()) {}
        System.out.println("A customer entered and got the ticket " + (this.ticketCounter) + ".");
        customer.setTicket(this.ticketCounter++);
        this.queue.add(customer);
    }

    public Customer getNextCustomer() {
        return queue.remove(0);
    }
    
    public void transactionIsOver(Employee employee){
        this.employeesWorking.remove(employee);
        employee = new Employee(this);
        this.employeesWaiting.add(employee);
        if(!this.isBankOpen && this.queue.isEmpty()){
            System.out.println("The Bank closed!");
        }
                }
    
    }
