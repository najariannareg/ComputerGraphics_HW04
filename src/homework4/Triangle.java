package homework4;

public class Triangle {

    private Point[] points = new Point[3];
    private int pixel = (255 << 16) | (255 << 8) | 255;


    public Triangle(Point p0, Point p1, Point p2){
        points[0] = p0;
        points[1] = p1;
        points[2] = p2;
    }


    public float[][] getP0(){ return points[0].getPoint(); }

    public float[][] getP1(){
        return points[1].getPoint();
    }

    public float[][] getP2(){
        return points[2].getPoint();
    }

    public int[] getC0(){ return points[0].getColor(); }

    public int[] getC1(){
        return points[1].getColor();
    }

    public int[] getC2(){
        return points[2].getColor();
    }

    public int getPixel() { return pixel; }

    public void setPixel(int[] color){
        pixel = (color[0] << 16) | (color[1] << 8) | color[2];
    }


    public void printPoints(){
        System.out.print("{");
        for(int i=0; i<points.length; i++) {
            points[i].printPoint();
            System.out.print(i<points.length-1? ", ": "}");
        }
        System.out.println();
    }


}
