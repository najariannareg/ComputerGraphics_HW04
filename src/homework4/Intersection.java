package homework4;

public class Intersection {

    private float t;
    private Triangle triangle;
    private int pixel;

    public Intersection(float minT, Triangle primitive){
        this.t = minT;
        this.triangle = primitive;
        int[] color = {0, 0, 0};
        setPixel(color);
    }

    public Intersection(float minT, Triangle primitive, int[] color){
        this.t = minT;
        this.triangle = primitive;
        setPixel(color);
    }

    public float getT(){
        return t;
    }

    public Triangle getTriangle(){
        return triangle;
    }


    public int getTrianglePixel(){
        return triangle.getPixel();
    }

    public int getPixel(){
        return pixel;
    }

    public void setPixel(int[] color){
        pixel = (color[0] << 16) | (color[1] << 8) | color[2];
    }

}
