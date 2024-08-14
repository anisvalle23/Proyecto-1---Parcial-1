
package BoardElements;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class OverlayIcon implements Icon {
    private final Icon baseIcon;
    private final Icon overlayIcon;

    public OverlayIcon(Icon baseIcon, Icon overlayIcon) {
        this.baseIcon = baseIcon;
        this.overlayIcon = overlayIcon;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        baseIcon.paintIcon(c, g, x, y);
        overlayIcon.paintIcon(c, g, x, y);
    }

    @Override
    public int getIconWidth() {
        return baseIcon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return baseIcon.getIconHeight();
    }
}
