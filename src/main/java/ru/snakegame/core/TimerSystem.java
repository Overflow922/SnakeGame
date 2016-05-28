package ru.snakegame.core;

/**
 * Created by Юрий on 26.05.2016.
 */
public class TimerSystem {
    private static TimerSystem ourInstance = new TimerSystem();
    private double start = 0.0;
    private double frameTime = 0.0;
    private double gameSpeed = SnakeParams.START_GAME_SPEED; // seconds
    public static TimerSystem getInstance() {
        return ourInstance;
    }

    private TimerSystem() {
    }

    public void start() {
        this.start = System.nanoTime();
        this.frameTime = System.nanoTime();

    }

    public double getLastFrameTime() {
        double curTime = System.nanoTime();
        double temp = (curTime - this.frameTime) / 1_000_000_000.0;
        this.frameTime = curTime;
        return  temp;
    }

    // Проверка пора ли двигать змейку
    public boolean check() {
        double delta = (System.nanoTime() - this.start ) / 1_000_000_000.0;

        if (delta > this.gameSpeed) {
            this.start();
            return true;
        }
        return false;
    }

    public void speedUp() {
        this.gameSpeed = this.gameSpeed / SnakeParams.GAME_SPEED_INCREASE;
    }
}
