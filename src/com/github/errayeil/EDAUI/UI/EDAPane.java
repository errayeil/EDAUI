package com.github.errayeil.EDAUI.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Custom painted JPanel used as the content pane for windows.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAPane extends JPanel {

    /**
     * If the top border of the panel should be drawn.
     */
    private boolean paintTopBorder = true;

    /**
     * If the right border of the panel should be drawn.
     */
    private boolean paintRightBorder = true;

    /**
     * If the bottom border of the panel should be drawn.
     */
    private boolean paintBottomBorder = true;

    /**
     * If the left border of the panel should be drawn.
     */
    private boolean paintLeftBorder = true;

    /**
     * If the semi-transparent rectangles in the background should be painted.
     */
    private boolean paintBackgroundRectangles = true;

    /**
     * The color of the background rectangles.
     */
    private Color backgroundRectanglesColor = EDAThemeColors.WINDOW_BACKGROUND_RECTANGLES;

    /**
     * The color of the top border line.
     */
    private Color topBorderColor = EDAThemeColors.EG_7;

    /**
     * The color of the right border line.
     */
    private Color rightBorderColor = EDAThemeColors.EG_7;

    /**
     * The color of the bottom border line.
     */
    private Color bottomBorderColor = EDAThemeColors.EG_7;

    /**
     * The color of the left border line.
     */
    private Color leftBorderColor = EDAThemeColors.EG_7;

    /**
     * The width of the border when drawn.
     */
    private int borderWidth = 4;

    /**
     *
     */
    private float backgroundOpacity = 0.8f;

    /**
     *
     */
    public EDAPane() {
        setOpaque( false );
        setBackground( EDAThemeColors.WINDOW_BACKGROUND );
    }

    /**
     * Sets the paintBackgroundRectangles flag.
     * repaint() will be invoked so changes happen immediately.
     * @param isPainting Whether or not the rectangles should be painted.
     */
    public void setPaintBackgroundRectangles(boolean isPainting) {
        this.paintBackgroundRectangles = isPainting;

        repaint();
    }

    /**
     * Sets the paintTopBorder flag. <br>
     * repaint() will be invoked so changes happen immediately.
     * @param isPainting Whether or not the top border should be painted.
     */
    public void setPaintTopBorder(boolean isPainting) {
        this.paintTopBorder = isPainting;

        repaint();
    }

    /**
     * Sets the paintRightBorder flag. <br>
     * repaint() will be invoked so changes happen immediately.
     * @param isPainting Whether or not the right border should be painted.
     */
    public void setPaintRightBorder(boolean isPainting) {
        this.paintRightBorder = isPainting;

        repaint();
    }

    /**
     * Sets the paintBottomBorder flag.
     * repaint() will be invoked so changes happen immediately.
     * @param isPainting Whether or not the bottom border should be painted.
     */
    public void setPaintBottomBorder(boolean isPainting) {
        this.paintBottomBorder = isPainting;

        repaint(  );
    }

    /**
     * Sets the paintLeftBorder flag.
     * repaint() will be invoked so changes happen immediately.
     * @param isPainting Whether or not the left border should be painted.
     */
    public void setPaintLeftBorder(boolean isPainting) {
        this.paintLeftBorder = isPainting;

        repaint();
    }

    /**
     * Sets the color of the background rectangles.
     * @param newColor The new background rectangles color.
     */
    public void setBackgroundRectanglesColor(Color newColor) {
        this.backgroundRectanglesColor = newColor;

        repaint();
    }

    /**
     * Sets the top border color.
     * @param newColor The new color.
     */
    public void setTopBorderColor(Color newColor) {
        this.topBorderColor = newColor;

        repaint();
    }

    /**
     * Sets the right border color.
     * @param newColor The new color.
     */
    public void setRightBorderColor(Color newColor) {
        this.rightBorderColor = newColor;

        repaint(  );
    }

    /**
     * Sets the bottom border color.
     * @param newColor The new color.
     */
    public void setBottomBorderColor(Color newColor) {
        this.bottomBorderColor = newColor;

        repaint();
    }

    /**
     * Sets the left border color.
     * @param newColor The new color.
     */
    public void setLeftBorderColor(Color newColor) {
        this.leftBorderColor = newColor;

        repaint();
    }

    /**
     * Sets the border stroke width. <br>
     * repaint() will be invoked so changes happen immediately.
     * @param newWidth The new stroke width of the border.
     */
    public void setBorderWidth(int newWidth) {
        this.borderWidth = newWidth;

        repaint();
    }

    /**
     * TODO
     * Opacity should be assigned to the top level container, such as a frame,
     * by calling setOpacity().
     * @param opacity
     */
    public void setBackgroundOpacity(float opacity) {
        this.backgroundOpacity = opacity;

        //repaint()
    }

    /**
     * Calls the UI delegate's paint method, if the UI delegate is non-<code>null</code>.  We pass the delegate a copy
     * of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes (for example, <code>Graphics.translate</code>).
     * <p>
     * If you override this in a subclass you should not make permanent changes to the passed in <code>Graphics</code>.
     * For example, you should not alter the clip <code>Rectangle</code> or modify the transform. If you need to do
     * these operations you may find it easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoke super's implementation you must honor the opaque property, that is if this component is opaque, you must
     * completely fill in the background in an opaque color. If you do not honor the opaque property you will likely see
     * visual artifacts.
     * <p>
     * The passed in <code>Graphics</code> object might have a transform other than the identify transform installed on
     * it.  In this case, you might get unexpected results if you cumulatively apply another transform.
     *
     * @param g the <code>Graphics</code> object to protect
     *
     * @see #paint
     */
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        paintBackground( g );
        paintBackgroundRectangles(g);
        paintBorders( g );
    }

    /**
     * Paints the background of the EDAPane.
     * @param g The graphics of the component.
     */
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( getBackground() );
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.fillRect( 0,0, getWidth(), getHeight() );
    }

    /**
     * Paints the background rectangles seen in the UI of ED.
     * @param g
     */
    protected void paintBackgroundRectangles(Graphics g) {
        if (paintBackgroundRectangles) {
            Graphics2D g2d = (Graphics2D ) g;

            g2d.setColor( backgroundRectanglesColor );
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );

            int rectangles = getHeight() / 6;
            int spacing = (getHeight() / rectangles) * 2;
            int x = 0, y = spacing;

            for (int i = 0; i < rectangles; i++) {
                g2d.fillRect( x, y, getWidth(), 4 );
                y = y + spacing;
            }
        }
    }

    /**
     * Paints the pane borders.
     * @param g
     */
    protected void paintBorders(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke( new BasicStroke( borderWidth ) );
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

        if (paintTopBorder) {
            g2d.setColor( topBorderColor);
            g2d.drawLine( 0, 0, getWidth(), 0 );
        }

        if (paintRightBorder) {
            g2d.setColor( rightBorderColor);
            g2d.drawLine(getWidth(), 0, getWidth(), getHeight());
        }

        if (paintBottomBorder) {
            g2d.setColor( bottomBorderColor);
            g2d.drawLine( 0, getHeight(), getWidth(), getHeight());
        }

        if (paintLeftBorder) {
            g2d.setColor( leftBorderColor );
            g2d.drawLine( 0, 0, 0, getHeight() );
        }
    }
}
