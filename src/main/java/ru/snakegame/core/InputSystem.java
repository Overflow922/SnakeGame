package ru.snakegame.core;

import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юрий on 26.05.2016.
 */
public class InputSystem {
    private static InputSystem ourInstance = new InputSystem();

    private List<String> input = new ArrayList<String>();

    public static InputSystem getInstance() {
        return ourInstance;
    }

    private InputSystem() {
    }

    public void put(KeyEvent e) {
        String code = e.getCode().toString();

        // only add once... prevent duplicates
        if ( !input.contains(code) )
            input.add( code );
    }

    public void remove(KeyEvent e) {
        String code = e.getCode().toString();
        input.remove( code );
    }

    public boolean contains(final String btn) {
        return this.input.contains(btn);
    }
}
