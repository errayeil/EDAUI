package com.github.errayeil.EDAUI.UI.Listeners;

/**
 * Event interface to help communicate EDAButton events.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public interface EDAButtonListener {

    /**
     * Invoked when a button, momentary or not, was clicked.
     */
    void buttonClicked(EDAButtonEvent e);

}
