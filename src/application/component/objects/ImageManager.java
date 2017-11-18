package application.component.objects;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageManager {
    private HashMap<Enum, Image> images;
    private ImageView imageView;
    public enum ObjectStatuses {
        WAIT,
        IMG_LEFT,
        IMG_RIGHT,
        JUMP
    }
    
    public ImageManager() {
        
    }
    
    public void showImage(objectStatus key) {
        Image image = images.get(key);
        imageView.setImage(image);
    }
    
    public void addImage(objectStatus key, Image image) {
        images.put(key, image);
    }
    
    public void transfer(int x, int y) {
        imageView.setX(x);
        imageView.setY(y);
    }
}