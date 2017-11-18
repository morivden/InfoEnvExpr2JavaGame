package application.component.objects;

import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageManager {
    private HashMap<String, Image> images;
    private ImageView imageView;
    
    public ImageManager() {
        
    }
    
    public void showImage(String key) {
        Image image = images.get(key);
        imageView.setImage(image);
    }
    
    public void addImage(String key, Image image) {
        images.put(key, image);
    }
    
    public void transfer(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }
}