package com.github.errayeil.EDAUI.UI;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDATextField extends JTextField implements FocusListener {

    /**
     *
     */
    private EDAResultsDialog resultsDialog;

    /**
     * The text hint that shows in the field before the user
     * types.
     */
    private String textHint = "Search";

    /**
     * Boolean determining if the text hint is visible.
     */
    private boolean showingTextHint = true;

    /**
     *
     */
    public EDATextField() {
        setBorder( BorderFactory.createCompoundBorder(
                new FieldBorder( 5 ), new EmptyBorder( new Insets(5, 5,5, 5  ) ) ) );
        setBackground( new Color(29, 29, 29) );
        setForeground( Color.WHITE );
        setFont( new Font("Eurostile", Font.BOLD, 16) );
        setText( textHint );
        setCaretColor( Color.WHITE );
        setFocusable( true );
        addFocusListener( this );

        resultsDialog = new EDAResultsDialog(this);

        addKeyListener( new KeyAdapter( ) {

            /**
             * Invoked when a key has been released.
             *
             * @param e
             */
            @Override
            public void keyReleased( KeyEvent e ) {
                super.keyReleased( e );

                int code = e.getKeyCode();
                System.out.println( KeyEvent.getKeyText( code ));

                if (code == KeyEvent.VK_ESCAPE) {
                    if (resultsDialog.isVisible()) {
                        resultsDialog.setVisible( false );

                        if (!isEditable()) {
                            setEditable( true );
                        }

                    }

                    selectAll();
                } else if (code == KeyEvent.VK_ENTER) {
                    resultsDialog.setVisible( !resultsDialog.isVisible( ) );
                } else {

                    //TODO do stuff
                }
            }
        } );
    }

    /**
     * Sets the showingTextHint flag.
     * @param isShowing Whether or not the text hint should show.
     */
    public void setShowTextHint(boolean isShowing) {
        this.showingTextHint = isShowing;

        if (!hasFocus() && !isShowing) {
            setText( "" );
        } else if (!hasFocus() && isShowing) {
            setText( textHint );
        }
    }

    /**
     * Sets the text hint to the new string.
     * @param newHint The text to be displayed when the field is out of focus.
     */
    public void setTextHint(String newHint) {
        this.textHint = newHint;

        if (!hasFocus()) {
            setText( newHint );
        }
    }

    /**
     * TODO
     * @param results
     */
    public void setSearchResults(java.util.List<?> results) {

    }

    /**
     * Invoked when a component gains the keyboard focus.
     * @param e the event to be processed
     */
    @Override
    public void focusGained( FocusEvent e ) {
        System.out.println( "Focus gained." );
        if(this.getText().isEmpty()) {
            setText("");
            showingTextHint = false;
        }
    }

    /**
     * Invoked when a component loses the keyboard focus.
     * @param e the event to be processed
     */
    @Override
    public void focusLost( FocusEvent e ) {
        System.out.println("Focus lost");
        if(this.getText().isEmpty()) {
            setText(textHint);
            showingTextHint = true;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String getText() {
        return showingTextHint ? "" : super.getText();
    }
}

/**
 * Custom border for our custom JTextField.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
class FieldBorder extends AbstractBorder {

    /**
     *
     */
    private int borderWidth;

    /**
     *
     */
    public FieldBorder(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * This default implementation does no painting.
     *
     * @param c      the component for which this border is being painted
     * @param g      the paint graphics
     * @param x      the x position of the painted border
     * @param y      the y position of the painted border
     * @param width  the width of the painted border
     * @param height the height of the painted border
     */
    @Override
    public void paintBorder( Component c, Graphics g, int x, int y, int width, int height ) {
        super.paintBorder( c, g, x, y, width, height );

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( new Color(65, 65, 65) );
        g2d.setStroke( new BasicStroke( borderWidth ) );

        g2d.drawRect( x, y, width, height );
    }
}

/**
 * Dialog used to show search results when typing into the
 * text box.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
class EDAResultsDialog {

    /**
     *
     */
    public enum DialogDirection { NORTH, EAST, SOUTH, WEST };

    /**
     * Direction the dialog should appear relative to the parent.
     * North - The dialog will appear north of the parent.
     * East - The dialog will appear east of the parent.
     * South - The dialog will appear south of the parent.
     * West - The dialog will appear west of the parent.
     */
    DialogDirection currentDirection = DialogDirection.SOUTH;

    /**
     * The parent of the dialog.
     */
    EDATextField parent;

    /**
     * The dialog used to display results.
     */
    JDialog dialog;

    /**
     * The background and content pane of the dialog.
     */
    EDAPane backgroundPane;

    /**
     * The default height of the dialog.
     */
    int defaultHeight = 400;

    /**
     *
     */
    public EDAResultsDialog(final EDATextField parent) {
        this.parent = parent;
        dialog = new JDialog();
        backgroundPane = new EDAPane();

        backgroundPane.setPaintBackgroundRectangles( false );
        backgroundPane.setPaintLeftBorder( false );
        backgroundPane.setPaintRightBorder( false );

        backgroundPane.setBackground( new Color(29, 29, 29) );
        backgroundPane.setTopBorderColor( new Color(65, 65, 65) );
        backgroundPane.setBottomBorderColor( new Color (65, 65, 65) );

        dialog.setUndecorated( true );
        dialog.setResizable( false );
        dialog.setModal( true );
        dialog.setAlwaysOnTop( true );
        dialog.setContentPane( backgroundPane );
        dialog.setMinimumSize( new Dimension(parent.getWidth(), defaultHeight) );
        dialog.setSize( new Dimension( parent.getWidth(), defaultHeight ) );
    }

    /**
     *
     * @return
     */
    public boolean isVisible() {
        return dialog.isVisible();
    }

    /**
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        if (dialog.getWidth() != parent.getWidth()) {
            dialog.setSize( new Dimension( parent.getWidth() , defaultHeight ));
        }

        Point screenLocation = parent.getLocationOnScreen();

        if (currentDirection == DialogDirection.SOUTH) {
            dialog.setLocation( screenLocation.x, screenLocation.y + parent.getHeight() + 15);
        }

        SwingUtilities.invokeLater( () -> {
            System.out.println( "Setting visible." );
            dialog.setVisible( visible );
        } );
    }
}
