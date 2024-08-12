package BoardElements;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageManager {

    public ImageIcon scaleImage(String url, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(url));
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public ImageIcon scaleImageForDisplay(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
