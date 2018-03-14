package banksystem;

import banksystem.helpers.InputHelper;
import banksystem.model.Bank;
import banksystem.model.Entrance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Charis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] variables = InputHelper.getInput();

        Bank bank = new Bank(variables[2]);
        bank.start();

        ArrayList<Entrance> entrances = new ArrayList();
        for (int i = 0; i < variables[0]; i++) {
            Entrance entrance = new Entrance(bank, variables[1]);
            entrance.start();
            entrances.add(entrance);
        }

        while (!entrances.isEmpty()) {
            int size = entrances.size();
            for (int i = 0; i < size; i++) {
                if (!entrances.get(i).isAlive()) {
                    entrances.remove(i);
                    i--;
                    size--;
                }
            }
        }

        bank.closeBank();

    }

}
