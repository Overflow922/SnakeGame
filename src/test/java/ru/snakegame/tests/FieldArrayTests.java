package ru.snakegame.tests;

import ru.snakegame.core.CellState;
import ru.snakegame.core.FieldArray;
import ru.snakegame.core.SnakeCell;
import ru.snakegame.core.math.Vector2;


/**
 * Created by Юрий on 14.05.2016.
 */

public class FieldArrayTests {
    public static void main(String[] args) {
        FieldArray fa = new FieldArray(new Vector2<Integer>(4, 4));
    //    System.out.println(fa.getFieldSize());
        fa.changeCellState(new Vector2<Integer>(1,1), CellState.CELL_SNAKE_HEAD);
        fa.changeCellState(new Vector2<Integer>(2,1), CellState.CELL_SNAKE_HEAD);
        fa.changeCellState(new Vector2<Integer>(0,0), CellState.CELL_SNAKE_HEAD);
        System.out.println(fa);

    }
}
