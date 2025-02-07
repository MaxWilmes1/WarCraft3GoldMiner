package org.example;

public class Worker {
    private boolean isMine;
    private boolean isBase;

    private int position;
    private String direction;

    public Worker() {
    }

    public Worker(boolean isBase, boolean isMine, int position, String direction) {
        this.direction = direction;
        this.isBase = false;
        this.isMine = false;
        this.position = position;
    }

    public void move(String path){
        path.toCharArray();
        if (this.position == 0 || this.position == path.length() - 1){
            changeDirection();
        }
        if (this.direction.equals("left")){
            this.position --;
        } else {
            this.position ++;
        }
    }

    public void changeDirection(){
        if (this.direction.equals("left")){
            this.direction = "right";
        } else {
            this.direction = "left";
        }
    }
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


}