package BoardElements;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.net.URL;

public class ImageManager {

    public ImageIcon scaleImage(String url, int width, int height) {
        URL imgURL = getClass().getClassLoader().getResource(url);
        if (imgURL == null) {
            System.err.println("Couldn't find file: " + url);
            return null; // Manejo seguro si la imagen no se encuentra
        }
        ImageIcon imageIcon = new ImageIcon(imgURL);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public ImageIcon scaleImageForDisplay(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public ImageIcon getImageIcon(String path) {
        URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL == null) {
            System.err.println("Couldn't find file: " + path);
            return null; // Manejo seguro si la imagen no se encuentra
        }
        return new ImageIcon(imgURL);
    }
}
