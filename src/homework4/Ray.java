package homework4;

import library.Matrix;

public class Ray {

    private float[][] source;
    private float[][] direction;

    public Ray(float[][] source, float[][] target){
        this.source = source;
        direction = Matrix.normalize(Matrix.getVector(source, target));
    }

    public float[][] getSource() { return source; }
    public float[][] getDirection() { return direction; }

}
