package com.senla.bank.ui.navigators;

import com.senla.bank.ui.menu.ConsoleMenu;
import com.senla.bank.ui.menu.MenuItem;
import com.senla.bank.utils.log.ConsoleLog;
import lombok.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Data
public class ConsoleMenuNavigator {

    private ConsoleMenu menu;

    public ConsoleMenuNavigator(ConsoleMenu menu) {
        this.menu = menu;
    }

    public void printMenu() {
        cls();
        ConsoleLog.log(menu.getName() + "\n");
        List<MenuItem> menuItems = menu.getItems();
        for (int i = 0; i < menuItems.size(); i++) {
            ConsoleLog.log("[%d] << %s", i + 1, menuItems.get(i).getName());
        }
    }

    public void navigate(Integer index) {
        --index;
        cls();
        if (index < 0 || menu.getItems().size() <= index) {
            ConsoleLog.log("There is no such menu item! Press any key to continue...");
            readKey();
            cls();
            return;
        }

        MenuItem selectedMenuItem = menu.getItems().get(index);
        selectedMenuItem.execute();

        if (selectedMenuItem.getNext() != null) {
            menu = (selectedMenuItem.getNext());
        } else {
            ConsoleLog.log("\nPress any key to continue...");
            readKey();
        }
        cls();
    }

    private void readKey() {
        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader theInput = new BufferedReader(in);
            ConsoleLog.log(theInput.readLine()+"");
        }
        catch (Exception e){}
    }

    private void cls() {
        ConsoleLog.log("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }


}
