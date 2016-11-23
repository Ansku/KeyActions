package org.vaadin.artur.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 * @author anna@vaadin.com
 */
public interface DisableOnShortcutRpc extends ServerRpc {

    /**
     * Alert the server that the client has disabled the component as a result
     * of a shortcut.
     */
    public void disableOnShortcut();
}
