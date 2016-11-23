package org.vaadin.artur.demo;

import org.vaadin.artur.DisableOnShortcutAction;
import org.vaadin.artur.KeyAction.KeyActionEvent;
import org.vaadin.artur.KeyAction.KeyActionListener;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DisableOnShortcutTest extends Panel {

    private VerticalLayout content;

    public DisableOnShortcutTest() {
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        final Button button = new Button("Click or press enter",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        execute(name);
                    }
                });
        button.setDisableOnClick(true);
        DisableOnShortcutAction disableOnShortcutExtension = new DisableOnShortcutAction(
                this, button, KeyCode.ENTER);
        disableOnShortcutExtension.addKeypressListener(new KeyActionListener() {
            @Override
            public void keyPressed(KeyActionEvent keyPressEvent) {
                execute(name);
            }
        });

        Button button2 = new Button("Click to re-enable first button",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        button.setEnabled(true);
                        focus(); // move focus to prevent next attempt both
                                 // enabling and disabling
                    }
                });

        content = new VerticalLayout(name, button, button2);
        content.setMargin(true);
        content.setSpacing(true);
        setContent(content);
    }

    private void execute(final TextField name) {
        try {
            // simulate long operation
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        content.addComponent(new Label("Long operation finished, "
                + name.getValue()));
    }
}
