package application.component.objects;

import java.awt.image.ImagingOpException;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageManager {
    private HashMap<ObjectStatus, Image> images;
    private ImageView imageView;

    public enum ObjectStatus {
        WAIT,
        IMG_LEFT,
        IMG_RIGHT,
        JUMP,
        DEAD
    }
    
    public ImageManager() {
        images = new HashMap<>();
        imageView = new ImageView();
    }
    
    public void showImage(ObjectStatus key) {
        Image image = images.get(key);
        imageView.setImage(image);
    }
    
    public void addImage(ObjectStatus key, Image image) {
        images.put(key, image);
    }
    
    public void transfer(int x, int y) {
        imageView.setX(x);
        imageView.setY(y);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
