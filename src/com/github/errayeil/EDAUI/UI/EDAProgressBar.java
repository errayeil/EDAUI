package com.github.errayeil.EDAUI.UI;

import com.github.errayeil.EDAUI.UI.EDAThemeColors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAProgressBar extends JComponent {

    /**
     * Boolean determining if the progress bar is determinate or not.
     */
    private boolean isIndeterminate = false;

    /**
     * Boolean determining if the progress bar will paint the string.
     */
    private boolean isPaintingString = false;

    /**
     * The color of the built in order.
     */
    private Color borderColor = EDAThemeColors.EG_7;

    /**
     * The color of the rectangle painted to represent progress.
     * This is used when the progress bar is indeterminate or determinate.
     */
    private Color progressColor = EDAThemeColors.EG_8;

    /**
     * A string that will be painted on top of the progress bar
     * if the progress bar is indeterminate or if isStringPainted
     * specified to true.
     */
    private String progressString;

    /**
     *
     */
    public EDAProgressBar() {

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
     * @see ComponentUI
     */
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
    }

    /**
     *
     * @param g
     */
    protected void paintBackground(Graphics g) {

    }

    /**
     *
     * @param g
     */
    protected void paintBorder(Graphics g) {

    }

    /**
     *
     * @param g
     */
    protected void paintDeterminate(Graphics g) {

    }

    /**
     *
     * @param g
     */
    protected void paintIndeterminate(Graphics g) {

    }

    /**
     *
     * @param g
     */
    protected void paintText(Graphics g) {

    }
}
