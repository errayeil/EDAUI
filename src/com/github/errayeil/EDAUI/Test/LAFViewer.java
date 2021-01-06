package com.github.errayeil.EDAUI.Test;

import com.github.errayeil.EDAUI.UI.EDAButton;
import com.github.errayeil.EDAUI.UI.EDAPane;
import com.github.errayeil.EDAUI.UI.EDATextField;
import com.github.errayeil.EDAUI.Utils.LAFUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Testing class that displays all EDAUI customized components in one
 * Window.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class LAFViewer {

    /**
     *
     */
    private JPanel viewerPane;

    /**
     *
     */
    private LAFViewer() {
        System.out.println( LAFUtils.registerEurostileFont());
        createWindow();
        createComponents();
    }

    /**
     *
     * @param args
     */
    public static void main (String[] args) {
        new LAFViewer();
    }

    /**
     *
     */
    private void createWindow() {
        JFrame frame = new JFrame( "Testing" );
        viewerPane = new JPanel(  );

        EDAButton skinnedButton = new EDAButton("EDAButtonTest", "Testing");
        EDAPane pane = new EDAPane();
        EDATextField field = new EDATextField();

        field.setMinimumSize( new Dimension(300, 40) );
        field.setSize( new Dimension(300, 40) );

        skinnedButton.setFocusable( true );
        skinnedButton.requestFocus();
        skinnedButton.setFont( new Font("Eurostile", Font.PLAIN, 13) );
        skinnedButton.setMinimumSize( new Dimension(250, 40) );
        skinnedButton.setSize( new Dimension(250, 40) );

        pane.setLayout( null );
        pane.add( skinnedButton );
        pane.add( field );

        skinnedButton.setLocation( 10, 20 );
        field.setLocation( 10, 100 );

        frame.setUndecorated( true );
        frame.setOpacity( 0.9f );
        frame.setBackground( new Color(0,0,0, 255) );
        frame.setContentPane( pane );
        frame.setMinimumSize( new Dimension(1200, 900) );
        frame.setResizable( false );
        frame.setLocationRelativeTo( null );

        SwingUtilities.invokeLater( () -> {
            frame.setVisible( true );
        } );

    }

    /**
     *
     */
    private void createComponents() {

    }
}
