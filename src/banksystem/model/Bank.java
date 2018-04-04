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
            this.employeesWaiting.add(new Employee(this, i, 1));
        }
    }

    @Override
    public void run() {
        while (true) {
            if (!this.queue.isEmpty() && !this.employeesWaiting.isEmpty()) {
                Customer curCustomer = null;
                Employee curEmployee = null;
                curEmployee = this.employeesWaiting.remove(0);
                this.employeesWorking.add(curEmployee);
                curCustomer = this.queue.remove(0);
                curEmployee.setCustomer(curCustomer);
                System.out.println("                                        Emp: " + curEmployee.getEmpId() + ", T: " + curCustomer.getTicket());
                curEmployee.start();
            }
            if (!(this.isBankOpen || !this.queue.isEmpty())) {
                break;
            }
        }
    }

    public boolean isBankFull() {
        return this.queue.size() + this.employeesWorking.size() >= this.bankLimit;
    }

    public void openBank() {
        this.isBankOpen = true;
    }

    public void closeBank() {
        this.isBankOpen = false;
    }

    public synchronized void addCustomerToQueue(Customer customer, int entranceId, int average, int counter) {
        boolean flag = true;
        while (this.isBankFull()) {
            if(flag){
                System.out.println("Bank is full!");
                flag = false;
            }
        }
        System.out.println("Ent: " + entranceId + " #" + counter);
        customer.setTicket(this.ticketCounter++);
        System.out.println("               E:" + entranceId + ", T: " + (this.ticketCounter - 1) + ", Av: " + this.queue.size() * average);
        this.queue.add(customer);
    }

    public Customer getNextCustomer() {
        return queue.remove(0);
    }

    public void transactionIsOver(Employee employee) {
        this.employeesWorking.remove(employee);
        employee = new Employee(this, employee.getEmpId(), employee.getCounter());
        this.employeesWaiting.add(employee);
        if (!this.isBankOpen && this.queue.isEmpty() && this.employeesWorking.isEmpty()) {
            System.out.println("The Bank closed!");
        }
    }

}
