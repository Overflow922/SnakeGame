package ru.snakegame.tests;

import ru.snakegame.core.math.Vector2;
import ru.snakegame.core.math.VectorCalculation;

/**
 * Created by Юрий on 14.05.2016.
 */
public class Vector2Tests {
    public static void main(String[] args) {
        Vector2<Integer> b = new Vector2<>(10,10);
        Vector2<Integer> a = new Vector2<>(2,2);
        a.setPos(1,1);
        a = VectorCalculation.add(a, b);

        System.out.println(a.toString());
    }
}
