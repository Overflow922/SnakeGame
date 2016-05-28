package ru.snakegame.core.math;

/**
 * Created by Юрий on 14.05.2016.
 */
public class VectorCalculation {

    // TODO: Добавить сложение для других типов
    public static Vector2<Integer> add(Vector2<Integer> a, Vector2<Integer> b) {
        Vector2<Integer> res = new Vector2<Integer>();
        res.setPos(a.getX() + b.getX(), a.getY() + b.getY());
        return res;
    }

    public static Vector2<Integer> sub(Vector2<Integer> a, Vector2<Integer> b) {
        Vector2<Integer> res = new Vector2<Integer>();
        res.setPos(a.getX() - b.getX(), a.getY() - b.getY());
        return res;
    }

    public static boolean compare(final Vector2<Integer> a, final Vector2<Integer> b) {
        return (a.getX().equals(b.getX()) && a.getY().equals(b.getY()));
    }
}
