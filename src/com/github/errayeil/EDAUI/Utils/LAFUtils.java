package com.github.errayeil.EDAUI.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class LAFUtils {

    private LAFUtils() {}

    /**
     * Registers Eurostile font for use with the application.
     *
     * @return True of the font was successfully registered.
     */
    public static boolean registerEurostileFont () {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        boolean success = false;

        try {
            success = ge.registerFont( Font.createFont( Font.TRUETYPE_FONT ,
                    new File( LAFUtils.class.getResource( "/com/github/errayeil/EDAUI/Resources/eurostile.TTF" ).toURI() ) ) );
        } catch ( FontFormatException | URISyntaxException | IOException e ) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     *
     * @return
     */
    public static String getEurostilePathInPackage() {
        return "/com/github/errayeil/EDAUI/Resources/eurostile.TTF";
    }

    /**
     * Converts an icon to an image for use when painting images on components.
     * @param icon The icon to convert.
     * @return The newly converted icon.
     */
    public static Image iconToImage( Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon)icon).getImage();
        }
        else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    /**
     * Returns the x coordinate within the specified rectangle accommodating font metrics.
     * This helps us center the text of the button.
     * <br><br>
     * @param visibleRect The visible part of the buttons rectangle.
     * @param metrics The metrics of the buttons font.
     * @param text The text we are positioning/painting.
     * @return
     */
    public static int getCenteredStringX( Rectangle visibleRect, FontMetrics metrics, String text) {
        return visibleRect.x + (visibleRect.width - metrics.stringWidth(text)) / 2;
    }

    /**
     * Returns the y coordinate within the specified rectangle accommodating font metrics.
     * This helps us center the text of the button.
     * <br><br>
     * @param visibleRect The visible part of the buttons rectangle.
     * @param metrics The metrics of the buttons font.
     * @param text The text we are positioning/painting;
     * @return
     */
    public static int getCenteredStringY(Rectangle visibleRect, FontMetrics metrics, String text) {
        return visibleRect.y + ((visibleRect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    }
}
