package library;

import homework4.Point;

public class Transformation3D {

    public static float[][] getTranslationMatrix(float tx, float ty, float tz){
        return new float[][]{
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        };
    }

    public static float[][] getRotationMatrixX(float angle){
        return new float[][] {
                {1, 0, 0, 0},
                {0, (float)Math.cos(Math.toRadians(angle)), (float)-Math.sin(Math.toRadians(angle)), 0},
                {0, (float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
    }

    public static float[][] getRotationMatrixY(float angle){
        return new float[][] {
                {(float)Math.cos(Math.toRadians(angle)), 0, (float)Math.sin(Math.toRadians(angle)), 0},
                {0, 1, 0, 0},
                {(float)-Math.sin(Math.toRadians(angle)), 0, (float)Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 0, 1}
        };
    }

    public static float[][] getRotationMatrixZ(float angle){
        return new float[][] {
                {(float)Math.cos(Math.toRadians(angle)), (float)-Math.sin(Math.toRadians(angle)), 0, 0},
                {(float)Math.sin(Math.toRadians(angle)), (float)Math.cos(Math.toRadians(angle)), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }

    public static float[][] getScalingMatrix(float sx, float sy, float sz){
        return new float[][] {
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        };
    }

    public static float[][] getRotationMatrixT(Point p1, Point p2, float angle) {
        float cx = p2.getX() - p1.getX();
        float cy = p2.getY() - p1.getY();
        float cz = p2.getZ() - p1.getZ();
        float L = (float)Math.sqrt(cx*cx + cy*cy + cz*cz);
        float alpha = (float)Math.asin(cy/L);
        float beta = (float)Math.asin(cx/L);

        float[][] translationMatrix1 = getTranslationMatrix(-p1.getX(), -p1.getY(), -p1.getZ());
        float[][] rotationMatrixX1 = getRotationMatrixX(alpha);
        float[][] rotationMatrixY1 = getRotationMatrixY(beta);
        float[][] rotationMatrixZ = getRotationMatrixZ(angle);
        float[][] rotationMatrixY = getRotationMatrixY(-beta);
        float[][] rotationMatrixX = getRotationMatrixX(-alpha);
        float[][] translationMatrix = getTranslationMatrix(p1.getX(), p1.getY(), p1.getZ());

        float[][] t1 = Matrix.matrixMultiplication(translationMatrix, rotationMatrixX);
        float[][] t2 = Matrix.matrixMultiplication(t1, rotationMatrixY);
        float[][] t3 = Matrix.matrixMultiplication(t2, rotationMatrixZ);
        float[][] t4 = Matrix.matrixMultiplication(t3, rotationMatrixY1);
        float[][] t5 = Matrix.matrixMultiplication(t4, rotationMatrixX1);
        float[][] T = Matrix.matrixMultiplication(t5, translationMatrix1);

        return T;
    }

    public static float[][] getScalingMatrixT(Point p, float sx, float sy, float sz) {
        float[][] translationMatrix1 = getTranslationMatrix(-p.getX(), -p.getY(), -p.getZ());
        float[][] scalingMatrix = getScalingMatrix(sx, sy, sz);
        float[][] translationMatrix2 = getTranslationMatrix(p.getX(), p.getY(), p.getZ());

        float[][] t1 = Matrix.matrixMultiplication(translationMatrix2, scalingMatrix);
        float[][] T = Matrix.matrixMultiplication(t1, translationMatrix1);

        return T;
    }



//    public static void main(String[] args){
//
//        Point p = new Point(10, 10, 10);
//        Point q = new Point(20, 30, 40);
//        float[][] rm = getRotationMatrixT(p, q, 30);
//
//
//        float[][] m1 = getTranslationMatrix(-17, -23, -11);
//        float[][] mi = matrixInverse(m1);
//        float[][] i = matrixMultiplication(m1, mi);
//
//        printMatrix(m1);
//        printMatrix(mi);
//        printMatrix(i);
//
//    }


}
