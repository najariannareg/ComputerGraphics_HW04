package homework4;

import static library.Matrix.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class RayCast {

    public static Intersection findIntersection(Ray ray, Scene scene){
        float minT = Float.MAX_VALUE;
        Triangle minPrimitive = null;
        for(Triangle primitive: scene.getScene()){
            float t = intersect(ray, primitive);
            if(t < minT){
                minPrimitive = primitive;
                minT = t;
            }
        }
        return new Intersection(minT, minPrimitive);
    }

    public static float intersect(Ray ray, Triangle primitive){
        // ray source
        float[][] P0 = ray.getSource();
        // ray direction
        float[][] V = ray.getDirection();
        // 1) calculate plane normal
        float[][] vAB = getVector(primitive.getP0(), primitive.getP1());
        float[][] vAC = getVector(primitive.getP0(), primitive.getP2());
        float[][] cABC = crossProduct(vAB, vAC);
        float[][] N = normalize(cABC);
        // plane distance from origin
        float d = dotProduct(primitive.getP0(), N);
        // 2) check if ray and plane are parallel
        float P0N = dotProduct(P0, N);
        float VN = dotProduct(V, N);
        if(VN < 0.000001 && VN > -0.000001) return Float.MAX_VALUE;
        // 3) compute t from the intersection equation
        float t = ((d - P0N) / VN);
        // 4) check if plane is behind the ray
        if(t < 0) return Float.MAX_VALUE;
        // 5) compute intersection point by substituting t in ray equation
        float[][] P = addition(P0, scalarMultiplication(t, V));
        // Barycentric
        float[][] vAP = getVector(primitive.getP0(), P);
        float[][] cABP = crossProduct(vAB, vAP);
        float[][] cACP = crossProduct(vAC, vAP);
        // calculate triangle areas
        float areaABC = magnitude(cABC) / 2;
        float areaABP = magnitude(cABP) / 2;
        float areaACP = magnitude(cACP) / 2;
        // calculate proportions
        float u = areaABP / areaABC;
        float v = areaACP / areaABC;
        // check if P is inside the triangle ABC
        if(!(u >= 0 && v >= 0 && u+v < 1)) return Float.MAX_VALUE;
        // check if P is on the right side
        if(dotProduct(cABC, cABP) < 0) return Float.MAX_VALUE;
        // check if P is on the left side
        if(dotProduct(cABC, cACP) > 0) return Float.MAX_VALUE;
        // return computed value of t
        return t;
    }

    public static Ray constructRayThroughPixel(Camera camera, int i, int j){
        float theta = camera.getTheta();
        float width = camera.getWidth();
        float height = camera.getHeight();
        float aspectRation = width / height;
        // Raster Space
        float pixelX = (i + 0.5f) / width;
        float pixelY = (j + 0.5f) / height;
        // Raster Space -> NDC Space -> Screen Space -> Camera Space
        pixelX = (2 * pixelX - 1) * (float)Math.tan(Math.toRadians(theta/2)) * aspectRation;
        pixelY = (1 - 2 * pixelY) * (float)Math.tan(Math.toRadians(theta/2));
        // Camera Space -> World Space
        float[][] source = camera.getPosition();
        float[][] targetCS = {{pixelX}, {pixelY}, {-1}, {1}};
        float[][] targetWS = matrixMultiplication(camera.getLookAtMatrix(), targetCS);
        targetWS = vec4To3(targetWS);
        // return new ray
        return new Ray(source, targetWS);
    }

    public static void rayCast(Camera camera, Scene scene, int width, int height) throws IOException {
        // window setup
        int[] color = {0, 0, 0}; //RGB
        int pixel = (color[0] << 16) | (color[1] << 8) | color[2];
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // ray cast start
        // --------------
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Ray ray = constructRayThroughPixel(camera, i, j);
                Intersection hit = findIntersection(ray, scene);
                if(hit.getTriangle() == null)
                    image.setRGB(i, j, pixel);
                else{
                    image.setRGB(i, j, hit.getTrianglePixel());
                }
            }
        }
        // ------------
        // ray cast end
        // image output
        File file = new File("RayCast.jpg");
        ImageIO.write(image, "jpg", file);
    }


}
