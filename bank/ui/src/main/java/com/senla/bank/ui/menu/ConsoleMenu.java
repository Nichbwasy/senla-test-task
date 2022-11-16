package com.senla.bank.ui.menu;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ConsoleMenu {

    private String name;
    private List<MenuItem> items;

    public ConsoleMenu() {
        this.name = "";
        this.items = new ArrayList<>();
    }

    public void addMenuItem(MenuItem menuItem) {
        items.add(menuItem);
    }

}
