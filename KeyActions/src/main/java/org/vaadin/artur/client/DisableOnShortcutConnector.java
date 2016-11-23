package org.vaadin.artur.client;

import org.vaadin.artur.DisableOnShortcutAction;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * @author anna@vaadin.com
 */
@Connect(DisableOnShortcutAction.class)
public class DisableOnShortcutConnector extends KeyActionConnector {

    @Override
    public DisableOnShortcutState getState() {
        return (DisableOnShortcutState) super.getState();
    }

    @Override
    protected void triggerEvent(KeyDownEvent event) {
        ((AbstractComponentConnector) getState().componentToDisable).getState().enabled = false;
        ((AbstractComponentConnector) getState().componentToDisable)
                .updateEnabledState(false);
        getRpcProxy(DisableOnShortcutRpc.class).disableOnShortcut();
        super.triggerEvent(event);
    }
}
