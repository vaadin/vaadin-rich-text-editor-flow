package com.vaadin.flow.component.richtexteditor.vaadincom;

import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * View for {@link Rich Text Editor} demo.
 */
@Route("vaadin-rich-text-editor")
public class RichTextEditorView extends DemoView {

    @Override
    protected void initView() {
        createDefaultEditor();
    }

    private void createDefaultEditor() {
        // begin-source-example
        // source-example-heading: Basic Rich Text Editor
        RichTextEditor rte = new RichTextEditor();
        // end-source-example

        addCard("Basic Rich Text Editor", rte);
    }

}
