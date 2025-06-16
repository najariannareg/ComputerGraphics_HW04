package homework4;

public class Point {

    private float[][] point = new float[3][1];
    private int[] color = {255, 255, 255};

    public Point(float x, float y, float z){
        setI(0, x);
        setI(1, y);
        setI(2, z);
    }

    public Point(float x, float y, float z, int[] color){
        setI(0, x);
        setI(1, y);
        setI(2, z);
        this.color = color;
    }


    public int[] getColor(){ return color; }

    public float[][] getPoint() { return point; }

    public void setPoint(float[][] point) { this.point = point; }

    public float getX() {
        return getI(0);
    }

    public void setX(float x) {
        setI(0, x);
    }

    public float getY() {
        return getI(1);
    }

    public void setY(float y) {
        setI(1, y);
    }

    public float getZ() {
        return getI(2);
    }

    public void setZ(float z){
        setI(2, z);
    }

    public float getI(int i){
        return point[i][0];
    }

    public void setI(int i, float num){
        point[i][0] = num;
    }


    public void printPoint(){
        System.out.print("{" + getX() + ", " + getY() + ", " + getZ() + "}");
    }


}