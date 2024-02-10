package cn.caryyu.entity;

public class PositionObject {

    private int x;

    private int y;

    private double loss;

    public  PositionObject(){

    }

    public PositionObject(int x,int y, double loss){
        this.x = x;
        this.y = y;
        this.loss = loss;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }
}
