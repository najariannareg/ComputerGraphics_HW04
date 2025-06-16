package homework4;

import java.util.ArrayList;

public class Scene {

    private ArrayList<Triangle> scene = new ArrayList<>();

    public Scene(){}

    public void add(Triangle t){
        scene.add(t);
    }

    public ArrayList<Triangle> getScene(){
        return scene;
    }

}
