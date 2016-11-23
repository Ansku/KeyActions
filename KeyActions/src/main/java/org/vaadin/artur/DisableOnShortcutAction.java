package org.vaadin.artur;

import org.vaadin.artur.client.DisableOnShortcutRpc;
import org.vaadin.artur.client.DisableOnShortcutState;

import com.vaadin.server.ClientConnector;
import com.vaadin.ui.AbstractComponent;

/**
 * @author anna@vaadin.com
 */
public class DisableOnShortcutAction extends KeyAction {

    private DisableOnShortcutRpc rpc = new DisableOnShortcutRpc() {

        @Override
        public void disableOnShortcut() throws RuntimeException {
            ((AbstractComponent) getState().componentToDisable)
                    .setEnabled(false);
            // Makes sure the enabled=false state is noticed at once - otherwise
            // a following setEnabled(true) call might have no effect.
            getUI().getConnectorTracker()
                    .getDiffState(
                            (ClientConnector) getState().componentToDisable)
                    .put("enabled", false);
        }
    };

    public DisableOnShortcutAction(AbstractComponent shortcutTarget,
            AbstractComponent componentToDisable, int keyCode, int... modifiers) {
        super(keyCode, modifiers);
        getState().componentToDisable = componentToDisable;
        registerRpc(rpc);
        extend(shortcutTarget);
    }

    @Override
    protected DisableOnShortcutState getState() {
        return (DisableOnShortcutState) super.getState();
    }
}
