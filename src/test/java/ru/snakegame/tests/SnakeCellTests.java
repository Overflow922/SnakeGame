package ru.snakegame.tests;

import ru.snakegame.core.CellState;
import ru.snakegame.core.math.Vector2;
import ru.snakegame.core.SnakeCell;

/**
 * Created by Юрий on 14.05.2016.
 */
public class SnakeCellTests {
    public static void main(String[] args) {
        SnakeCell a = new SnakeCell();
        System.out.println(a.toString());

        SnakeCell b = new SnakeCell(new Vector2<Integer>(1,1), CellState.CELL_APPLE);
        b.setPos(2,2);
        System.out.println(b.toString());

        Vector2<Integer> c =  b.getPos();
        System.out.println(c.toString());
    }
}
