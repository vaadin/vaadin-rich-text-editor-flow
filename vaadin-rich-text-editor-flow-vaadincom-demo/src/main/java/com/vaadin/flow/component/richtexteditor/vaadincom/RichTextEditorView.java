package com.vaadin.flow.component.richtexteditor.vaadincom;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * View for {@link RichTextEditor} demo.
 */
@Route("vaadin-rich-text-editor")
public class RichTextEditorView extends DemoView {

    @Override
    protected void initView() {
        createDefaultEditor();
        createGetValue();
        createGetHtmlValue();
    }

    String savedValue;

    private void createDefaultEditor() {
        // begin-source-example
        // source-example-heading: Basic Rich Text Editor
        RichTextEditor rte = new RichTextEditor();
        // end-source-example

        addCard("Basic Rich Text Editor", rte);
    }

    private void createGetValue() {
        // begin-source-example
        // source-example-heading: Save Rich Text Editor value
        RichTextEditor rte = new RichTextEditor();
        Button saveBtn = new Button("Save value", e -> savedValue = rte.getValue());
        Button setBtn = new Button("Set value", e ->  rte.setValue(savedValue));
        // end-source-example

        addCard("Save Rich Text Editor value", rte, saveBtn, setBtn);
    }

    private void createGetHtmlValue() {
        // begin-source-example
        // source-example-heading: Save Rich Text Editor htmlValue
        Div htmlBlock = new Div();
        RichTextEditor rte = new RichTextEditor();
        Button showHtmlValue = new Button("Show html value", e -> {
            String exsValue = htmlBlock.getElement().getProperty("innerHTML");
            if (exsValue == null || !exsValue.equals(rte.getHtmlValue())) {
                htmlBlock.getElement().setProperty("innerHTML", rte.getHtmlValue());
            }
        });
        // end-source-example

        addCard("Save Rich Text Editor htmlValue", rte, showHtmlValue, htmlBlock);
    }

}
