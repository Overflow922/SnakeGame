package ru.snakegame.core;

import ru.snakegame.core.math.Vector2;
import ru.snakegame.core.math.VectorCalculation;
import ru.snakegame.ui.WinConsts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Юрий on 26.05.2016.
 */
public class Snake {

    private int len = SnakeParams.START_LEN;
    private LinkedList<Vector2<Integer>> cells;
    private Vector2<Integer> moveDirection = SnakeParams.START_DIRECTION;

    public Snake() {
        cells = new LinkedList<>();
        createSnake();
    }

    public void grow() {
        //TODO: сделать задержку увеличения хвоста 1 ход
        len++;
        assert (cells.size() >2) : "too small len";
        Vector2<Integer> grad = VectorCalculation.sub(cells.getLast(), cells.get(cells.size()-2));
        cells.add(VectorCalculation.add(cells.getLast(), grad));
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

    public Vector2<Integer> getMoveDirection() {
        return this.moveDirection;
    }

    public void updateMoveDirection() {
        // TODO: кнопки управления задавать где то во внешнем файле (не здесь)
        // TODO: сделать изменения направления по таймеру
        Vector2<Integer> newDir = this.moveDirection;

        if (InputSystem.getInstance().contains("LEFT")) { newDir = SnakeParams.Directions.MOVE_LEFT; }
        if (InputSystem.getInstance().contains("RIGHT")) { newDir = SnakeParams.Directions.MOVE_RIGHT; }
        if (InputSystem.getInstance().contains("UP")) { newDir = SnakeParams.Directions.MOVE_UP; }
        if (InputSystem.getInstance().contains("DOWN")) { newDir = SnakeParams.Directions.MOVE_DOWN; }

        Vector2<Integer> t = VectorCalculation.add(newDir, this.moveDirection);
        if (!(t.getX() == 0 && t.getY() == 0))
            this.moveDirection = newDir;
    }

    public void move() {
        Vector2<Integer> newHeadPos = VectorCalculation.add(cells.getFirst(), moveDirection);

        if (CollisionSystem.getInstance().isBorderCollision(newHeadPos) != CollisionResult.BORDER_COLLISION) {
            cells.addFirst(newHeadPos);
            cells.removeLast();
        }
    }

    public Vector2<Integer> getHead() {
        return cells.getFirst();
    }

    @Override
    public String toString() {
        return "Snake len("+len+"), direction("+moveDirection.toString()+").";
    }
}
