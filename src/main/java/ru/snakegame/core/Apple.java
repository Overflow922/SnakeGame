package ru.snakegame.core;

import ru.snakegame.core.math.Vector2;

import java.util.Random;

/**
 * Created by Юрий on 26.05.2016.
 */
public class Apple {

    private double timeToLive = SnakeParams.APPLE_TTL; // seconds
    private Vector2<Integer> pos;

    Apple() {

    }

    public void setPos(final Vector2<Integer> pos) {
        this.pos = pos;
    }

    public Vector2<Integer> getPos() {
        return this.pos;
    }

    static public Vector2<Integer> getRandomPos(final Vector2<Integer> borders) {
        Random rnd = new Random();
        return (new Vector2<Integer>(rnd.nextInt(borders.getX()), rnd.nextInt(borders.getY())));
    }

    static public Vector2<Integer> getRandomPos(final int maxX, final int maxY) {
        Random rnd = new Random();
         return (new Vector2<Integer>(rnd.nextInt(maxX), rnd.nextInt(maxY)));
    }

    public void setTimeToLive(final double ttl) {
        this.timeToLive = ttl;
    }

    public void updateTimeToLive(final double delta) {
        if (this.timeToLive != -1.0) {
            this.timeToLive = this.timeToLive - delta;
        }
    }

    public boolean checkTimeToLive() {
        return (this.timeToLive <= 0.0 && this.timeToLive != -1.0);
    }
}

