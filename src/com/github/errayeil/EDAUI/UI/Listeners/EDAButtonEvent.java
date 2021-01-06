package com.github.errayeil.EDAUI.UI.Listeners;

import com.github.errayeil.EDAUI.UI.EDAButton;

/**
 * EDAButtonEvent is created and passed to the listening class when an EDAButton has been clicked.
 *
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDAButtonEvent {

    /**
     * The source of the event.
     */
    private EDAButton sourceButton;


    /**
     * Constructs a new EDAButtonEvent instance from the specified source.
     * @param source The source of the event.
     */
    public EDAButtonEvent(EDAButton source ) {
        this.sourceButton = source;
    }

    /**
     * Returns the source of the event.
     * @return
     */
    public EDAButton getSourceButton() {
        return sourceButton;
    }
}
