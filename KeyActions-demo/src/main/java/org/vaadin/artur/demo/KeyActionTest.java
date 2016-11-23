package org.vaadin.artur.demo;

import org.vaadin.artur.KeyAction;
import org.vaadin.artur.KeyAction.KeyActionEvent;
import org.vaadin.artur.KeyAction.KeyActionListener;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.tests.util.Log;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class KeyActionTest extends Panel {

    private Log log = new Log(5);

    public KeyActionTest() {
        log.log("All TextAreas, the Panel and the layout within TabSheet listen to enter, shift-enter and alt-f");

        VerticalLayout content = new VerticalLayout();
        setContent(content);

        DemoPanel p = new DemoPanel();
        content.addComponent(log);
        content.addComponent(p);

        setId("layout");
        addListener(this, "Enter", KeyCode.ENTER);
        addListener(this, "Shift enter", KeyCode.ENTER, ModifierKey.SHIFT);
        addListener(this, "ALT-f", KeyCode.F, ModifierKey.ALT);

        addListener(p, "Enter", KeyCode.ENTER);
        addListener(p, "Shift enter", KeyCode.ENTER, ModifierKey.SHIFT);
        addListener(p, "ALT-f", KeyCode.F, ModifierKey.ALT);

        addListener(p.TA1, "Enter", KeyCode.ENTER);
        addListener(p.TA1, "Shift enter", KeyCode.ENTER, ModifierKey.SHIFT);
        addListener(p.TA1, "ALT-f", KeyCode.F, ModifierKey.ALT);

        KeyAction action = addListener(p.TA2, "Enter", KeyCode.ENTER);
        action.setStopPropagation(true);
        action.setPreventDefault(true);

        action = addListener(p.TA2, "Shift enter", KeyCode.ENTER,
                ModifierKey.SHIFT);
        action.setStopPropagation(true);
        action.setPreventDefault(true);

        action = addListener(p.TA2, "ALT-f", KeyCode.F, ModifierKey.ALT);
        action.setStopPropagation(true);
        action.setPreventDefault(true);

        action = addListener(p.TA3, "Enter", KeyCode.ENTER);
        action.setStopPropagation(true);
        action.setPreventDefault(false);

        action = addListener(p.TA3, "Shift enter", KeyCode.ENTER,
                ModifierKey.SHIFT);
        action.setStopPropagation(true);
        action.setPreventDefault(false);

        action = addListener(p.TA3, "ALT-f", KeyCode.F, ModifierKey.ALT);
        action.setStopPropagation(true);
        action.setPreventDefault(false);
    }

    private KeyAction addListener(AbstractComponent component,
            final String keyname, int keycode, int... modifierKey) {
        KeyAction action = new KeyAction(keycode, modifierKey);
        action.addKeypressListener(new KeyActionListener() {
            @Override
            public void keyPressed(KeyActionEvent keyPressEvent) {
                log.log(keyname + " pressed on "
                        + keyPressEvent.getComponent().getId());
            }
        });
        action.extend(component);
        return action;
    }

    private KeyAction addPropagatorAction(AbstractComponent component,
            int keycode, int... modifierKey) {
        KeyAction action = new KeyAction(keycode, modifierKey);
        action.extend(component);
        return action;
    }
}
