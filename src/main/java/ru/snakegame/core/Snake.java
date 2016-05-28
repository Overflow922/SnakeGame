package ru.snakegame.core;

import ru.snakegame.core.math.Vector2;
import ru.snakegame.core.math.VectorCalculation;

import java.util.LinkedList;

/**
 * Author: Юрий
 * Created: 25.05.2016
 * Description:
 */

public class Snake {

    private int len = SnakeParams.START_LEN;
    private LinkedList<Vector2<Integer>> cells;
    private boolean isGrow = false;
    private Vector2<Integer> moveDirection = SnakeParams.START_DIRECTION;
    private Vector2<Integer> changeDirection = SnakeParams.START_DIRECTION;

    public Snake() {
        cells = new LinkedList<>();
        createSnake();
    }

    public void grow() {
        len++;
        assert (cells.size() >2) : "too small len";
        this.isGrow = true;
    }

    private void createSnake() {
        Vector2<Integer> curPos = VectorCalculation.add(SnakeParams.START_FROM, SnakeParams.START_DIRECTION);

        for (int i=0; i < this.len; i++) {
            curPos = VectorCalculation.sub(curPos, SnakeParams.START_DIRECTION);
            Vector2<Integer> res = curPos;
            cells.add(res);
        }
    }

    public void push(FieldArray field) {
        for (SnakeCell cell: field) {
            if (cell.getState() == CellState.CELL_SNAKE) {
                cell.setState(CellState.CELL_EMPTY);
            }
        }
        for (Vector2<Integer> i: cells) {
            field.changeCellState(i, CellState.CELL_SNAKE);
        }
    }

    public void setMoveDirection(final Vector2<Integer> new_dir) {
        this.moveDirection = new_dir;
    }

    public void updateMoveDirection() {
        // TODO: кнопки управления задавать где то во внешнем файле (не здесь)


        Vector2<Integer> newDir = this.changeDirection;

        if (InputSystem.getInstance().contains("LEFT")) { newDir = SnakeParams.Directions.MOVE_LEFT; }
        if (InputSystem.getInstance().contains("RIGHT")) { newDir = SnakeParams.Directions.MOVE_RIGHT; }
        if (InputSystem.getInstance().contains("UP")) { newDir = SnakeParams.Directions.MOVE_UP; }
        if (InputSystem.getInstance().contains("DOWN")) { newDir = SnakeParams.Directions.MOVE_DOWN; }

        Vector2<Integer> t = VectorCalculation.add(newDir, this.moveDirection);
        if (!(t.getX() == 0 && t.getY() == 0))
            this.changeDirection = newDir;
    }

    private void changeMoveDirection() {
        Vector2<Integer> t = VectorCalculation.add(this.changeDirection, this.moveDirection);
        if (!(t.getX() == 0 && t.getY() == 0))
            this.moveDirection = this.changeDirection;
    }

    // если змея растет, она не двигается, вырастает голова
    // если не растет, движется на одну клетку по направлению moveDirection
    public void move() {
        // TODO: поменять на getNextHead
        this.changeMoveDirection();
        if (this.isGrow) {
            cells.addFirst(this.getNextHead());
            this.isGrow = false;
        }
        else {
            Vector2<Integer> newHead = this.getNextHead();
            if (CollisionSystem.getInstance().isBorderCollision(newHead) != CollisionResult.BORDER_COLLISION &&
                    CollisionSystem.getInstance().isSnakeCollision(this) != CollisionResult.SNAKE_COLLISION) {
                cells.addFirst(newHead);
                cells.removeLast();
            }
        }
    }

    public Vector2<Integer> getHead() {
        return cells.getFirst();
    }

    public Vector2<Integer> getNextHead() { return VectorCalculation.add(this.getHead(), this.moveDirection);}

    public boolean isBelong(final Vector2<Integer> coords) {
        for (Vector2<Integer> i: cells) {
            if (VectorCalculation.compare(coords, i))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Snake len("+len+"), direction("+moveDirection.toString()+").";
    }
}
