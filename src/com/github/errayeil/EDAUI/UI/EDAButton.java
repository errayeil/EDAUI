package com.github.errayeil.EDAUI.UI;

import com.github.errayeil.EDAUI.UI.Listeners.EDAButtonEvent;
import com.github.errayeil.EDAUI.UI.Listeners.EDAButtonListener;
import com.github.errayeil.EDAUI.Utils.LAFUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Custom JComponent used as buttons in EDA.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAButton extends JComponent  {

    /**
     * boolean determining if the button is momentary.
     * If true, it acts as a JButton, false it acts as a JToggleButton.
     * <p></p>
     */
    private boolean isMomentary = true;

    /**
     * boolean determining if the button is selected. <br>
     * This returns true if it is currently pressed as a momentary button
     * or is toggled on as a non momentary button.
     */
    private boolean isSelected = false;

    /**
     * The color of the built in border.
     * <p></p>
     * The default value is EG_7 from EDAThemeColors
     * @see EDAThemeColors
     */
    private Color borderColor = EDAThemeColors.EG_7;

    /**
     * The color of the button background, also used when the button
     * is not currently clicked or, if momentary, toggled off.
     * <p></p>
     * The default value is BUTTON_UNSELECTED from EDAThemeColors.
     * @see EDAThemeColors
     */
    private Color unselectedBackgroundColor = EDAThemeColors.BUTTON_UNSELECTED;

    /**
     * The color of the button background when it is selected or,
     * if momentary, toggled on.
     * <p></p>
     * The default value is BUTTON_SELECTED from EDAThemeColors.
     * @see EDAThemeColors
     */
    private Color selectedBackgroundColor = EDAThemeColors.BUTTON_SELECTED;

    /**
     * The color of the text when the button is unselected or,
     * if momentary, toggled off.<p></p>
     * This is shared between both the primary and secondary strings.
     */
    private Color unselectedTextColor = EDAThemeColors.UNSELECTED_TEXT_COLOR;

    /**
     * The color of the text when the button is selected or,
     * if momentary, toggled on. <p></p>
     * This is shared between both the primary and secondary strings.
     */
    private Color selectedTextColor = EDAThemeColors.SELECTED_TEXT_COLOR;

    /**
     * The font for the secondary text string.
     */
    private Font secondaryTextFont = new Font("Eurostile", Font.PLAIN, 10);

    /**
     * The string of text displayed at the center of the button.
     */
    private String primaryString;

    /**
     * The string of text displayed below the primaryString.
     */
    private String secondaryString;

    /**
     * A list of EDAButtonListeners listening for events.
     */
    private java.util.List< EDAButtonListener > buttonListenerList;
    /**
     * Constructs EDAButton with default values.
     */
    public EDAButton() {
        this("", "");
    }

    /**
     * Constructs EDAButton with the specified strings.
     * @param primaryString The text that appears at the center of the button.
     * @param secondaryString The text that appears below the center text.
     */
    public EDAButton(String primaryString, String secondaryString) {
        this.primaryString = primaryString;
        this.secondaryString = secondaryString;
        this.buttonListenerList = new ArrayList<>(  );

        createMouseListener();
    }

    /**
     * Adds the specified EDAButtonListener to the list.
     * @param newListener
     */
    public void addButtonListener(EDAButtonListener newListener) {
        buttonListenerList.add( newListener );
    }

    /**
     * Removes the specified EDAButtonListener from the list.
     * @param listener The listener to remove.
     */
    public void removeButtonListener(EDAButtonListener listener) {
        buttonListenerList.remove( listener );
    }

    /**
     * Returns if the button is selected or not.
     * @return True if selected, false otherwise.
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Returns if the button is momentary or not.
     * @return True if momentary, false otherwise.
     */
    public boolean isMomentary() {
        return isMomentary;
    }

    /**
     * Sets the isMomentary value to the new value
     * specified.
     * @param isMomentary The new isMomentary value.
     */
    public void setMomentary(boolean isMomentary) {
        this.isMomentary = isMomentary;
    }

    /**
     * Sets the isSelected value to the new value specified.
     * This will only have an affect if the button is momentary.
     * <br></br>
     * If the button is momentary and is selected, repaint() will
     * be invoked to apply changes immediately.
     * @param isSelected The new isSelected value.
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;

        if (isMomentary && this.isSelected) {
            repaint(  );
        }
    }

    /**
     * Sets the border color to the specified parameter.
     * <br></br>
     * This will invoke repaint() to apply the changes immediately.
     * @param newColor The new color to apply to the border.
     */
    public void setBorderColor(Color newColor) {
        borderColor = newColor;

        repaint(  );
    }

    /**
     * Sets the color of the background when the button is
     * not selected or toggled. <br></br>
     * If the button is not selected, repaint() will be invoked
     * so changes are applied immediately.
     *
     * @param newColor The new color to apply to the background when unselected.
     */
    public void setUnselectedBackgroundColor(Color newColor) {
       this.unselectedBackgroundColor = newColor;

       if (!isSelected)
           repaint(  );
    }

    /**
     * Sets the color of the background when the button is selected
     * or toggled. <br></br>
     * If the button is selected or toggled, repaint() will be invoked
     * to apply changes immediately.
     *
     * @param newColor The new color to apply to the background when selected,
     */
    public void setSelectedBackgroundColor(Color newColor) {
        this.selectedBackgroundColor = newColor;

        if (isSelected)
            repaint(  );
    }

    /**
     * Sets the color the text when the button is not selected or toggled.
     * <br></br>
     * This color applies to both the primary and secondary text displayed.
     * <br></br>
     * If the button is not selected or toggled, repaint() will be invoked
     * to apply changes immediately.
     * @param newColor The new color for the primary and secondary text when the
     *                 the button is not selected.
     */
    public void setUnselectedTextColor(Color newColor) {
        this.unselectedTextColor = newColor;

        if (!isSelected)
            repaint(  );
    }

    /**
     * Sets the color of the text when the button is selected or toggled.
     * <br></br>
     * This color applies to both the primary and secondary text displayed.
     * <br></br>
     * If the button is selected or toggled, repaint() will be invoked
     * to apply changes immediately.
     * @param newColor The new color for the text when the button is selected.
     */
    public void setSelectedTextColor(Color newColor) {
        this.selectedTextColor = newColor;

        if (isSelected)
            repaint(  );
    }

    /**
     * Sets the primary text to be displayed.
     * <br></br>
     * repaint() will be invoked to apply changes immediately.
     * @param newString The new text to be displayed.
     */
    public void setPrimaryString(String newString) {
        this.primaryString = newString;

        repaint(  );
    }

    /**
     * Sets the secondary text to be displayed.
     * <br></br>
     * repaint() will be invoked to apply changes immediately.
     * @param newString The new text to be displayed.
     */
    public void setSecondaryString(String newString) {
        this.secondaryString = newString;

        repaint(  );
    }

    /**
     * Sets the secondary text font.
     * <br></br>
     * repaint() will be invoked to apply changes immediately.
     * @param newFont The new font to be used.
     */
    public void setSecondaryTextFont(Font newFont) {
        this.secondaryTextFont = newFont;

        repaint(  );
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
     * @see javax.swing.plaf.ComponentUI
     */
    @Override
    protected void paintComponent( Graphics g ) {
        //We want to pain the fill first, based on if the button is selected or not.
        if (isSelected) {
            paintSelectedFill( g );
        } else {
            paintUnselectedFill( g );
        }

        //We paint the border next, which will be painted over the fill.
        paintBorder( g );
        paintPrimaryText( g );
        paintSecondaryText( g );

        super.paintComponent( g );
    }



    /**
     * Paints in the built in border of the EDAButton.
     * @param g The Graphics object of the EDAButton.
     */
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setStroke( new BasicStroke( 4 ) );
        g2d.setColor(borderColor);

        g2d.drawRect( 1, 1, getWidth() - 2, getHeight() - 2);

    }

    /**
     * Paints the fill of the button when it has not been selected
     * or toggled.
     * @param g The Graphics object of the EDAButton.
     */
    protected void paintUnselectedFill(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setColor(Color.BLACK);
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
    }

    /**
     * Paints the fill of the button when it has been selected or
     * toggled.
     * @param g The Graphics object of the EDAButton.
     */
    protected void paintSelectedFill(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setColor(selectedBackgroundColor);
        g2d.fillRect( 0, 0, getWidth(), getHeight() );
    }

    /**
     * Paints the primary text of the button if it has been specified,
     * and paints the correct color dependent on isSelected.
     * @param g The Graphics object of the EDAButton.
     */
    protected void paintPrimaryText(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font font = getFont();

        int x = LAFUtils.getCenteredStringX( getVisibleRect(), getFontMetrics( font ), primaryString );
        int y = LAFUtils.getCenteredStringY( getVisibleRect(), getFontMetrics( font ), primaryString ) - 8;

        g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

        if (isSelected) {
            g2d.setColor(selectedTextColor);
        } else {
            g2d.setColor(unselectedTextColor);
        }

        g2d.drawString( primaryString, x, y ); // When retrieving the y, we lowered by 5 to offset the string for the secondary
    }

    /**
     * Paints the secondary text of the button if it has been specified,
     * and paints the correct color dependent on isSelected.
     * @param g The Graphics object of the EDAButton.
     */
    protected void paintSecondaryText(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (isSelected) {
            g2d.setColor(selectedTextColor);
        } else {
            g2d.setColor( unselectedTextColor );
        }

        int x = LAFUtils.getCenteredStringX( getVisibleRect(), getFontMetrics( secondaryTextFont ), secondaryString );
        int y = LAFUtils.getCenteredStringY( getVisibleRect(), getFontMetrics( secondaryTextFont ), secondaryString ) + 8;

        g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

        g2d.drawString( secondaryString, x, y );
    }

    /**
     * Creates a mouse listener that processes mouse click events and paints
     * the component accordingly.
     *
     */
    private void createMouseListener() {
        addMouseListener( new MouseAdapter( ) {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed( MouseEvent e ) {
                super.mousePressed( e );

                if (isMomentary) {
                    isSelected = true;
                } else {
                    isSelected = ! isSelected;
                }

                repaint( );
            }

            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseReleased( MouseEvent e ) {
                super.mouseReleased( e );

                if (isMomentary) {
                    isSelected = false;
                }
                repaint( );

            }
        } );

        addMouseListener( new MouseAdapter( ) {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked( MouseEvent e ) {
                super.mouseClicked( e );
                requestFocus( FocusEvent.Cause.MOUSE_EVENT );

                for (EDAButtonListener l : buttonListenerList) {
                    l.buttonClicked( new EDAButtonEvent(EDAButton.this) );
                }
            }
        } );
    }
}
