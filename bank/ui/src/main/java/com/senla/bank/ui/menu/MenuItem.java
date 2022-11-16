package com.senla.bank.ui.menu;

import com.senla.bank.ui.actions.UiAction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {

    private String name;
    private UiAction action;
    private ConsoleMenu next;

    public void execute() {
        if (action != null) {
            action.execute();
        }
    }

}
