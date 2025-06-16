package homework4;

import java.io.IOException;

import static homework4.RayCast.*;
import static homework4.RayCast1.*;

public class Render {

    public static void main(String[] args) throws IOException {

        final int imageWidth = 512;
        final int imageHeight = 512;

        // Render start
        // ------------
        Scene scene = new Scene();

        int[] c1 = {255, 0, 0};
        int[] c2 = {0, 255, 0};
        int[] c3 = {0, 0, 255};

        Triangle t1 = new Triangle(
                new Point(-10.0f, 10.0f, -20.0f, c1),
                new Point(-10.0f, -10.0f, -20.0f, c2),
                new Point(10.0f, -10.0f, -20.0f, c3)
        );
        t1.setPixel(c1);

        Triangle t2 = new Triangle(
                new Point(10.0f, 10.0f, -30.0f, c2),
                new Point(10.0f, -10.0f, -30.0f, c2),
                new Point(30.0f, -10.0f, -30.0f, c2)
        );
        t2.setPixel(c2);

        Triangle t3 = new Triangle(
                new Point(0.0f, 40.0f, -60.0f),
                new Point(-40.0f, -40.0f, -60.0f),
                new Point(40.0f, -40.0f, -60.0f)
        );
        t3.setPixel(c3);

        scene.add(t1);
        scene.add(t2);
        scene.add(t3);

        Camera camera = new Camera(90, imageWidth, imageHeight);

        //rayCast(camera, scene, imageWidth, imageHeight);
        rayCast1(camera, scene, imageWidth, imageHeight);
        // ----------
        // Render end


    }

}
