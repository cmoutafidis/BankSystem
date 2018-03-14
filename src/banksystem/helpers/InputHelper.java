package banksystem.helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Charis
 */
public final class InputHelper {
    public static int[] getInput(){
        Scanner in = new Scanner(System.in);
        int a, b, c;
        
        while(true){
            System.out.print("Enter the number of the entrances of the bank (A): ");
            try {
                a = in.nextInt();
                break;
            }
            catch(InputMismatchException exception){
                System.out.println("Please enter an integer.");
                in.nextLine();
            }
        }
        while(true){
            System.out.print("Enter the number of customers that will enter from each entrance (B): ");
            try {
                b = in.nextInt();
                break;
            }
            catch(InputMismatchException exception){
                System.out.println("Please enter an integer.");
                in.nextLine();
            }
        }
        while(true){
            System.out.print("Enter the number of bank employees (C): ");
            try {
                c = in.nextInt();
                break;
            }
            catch(InputMismatchException exception){
                System.out.println("Please enter an integer.");
                in.nextLine();
            }
        }
        
        int[] output = new int[3];
        output[0] = a;
        output[1] = b;
        output[2] = c;
        
        return output;
    }
}
