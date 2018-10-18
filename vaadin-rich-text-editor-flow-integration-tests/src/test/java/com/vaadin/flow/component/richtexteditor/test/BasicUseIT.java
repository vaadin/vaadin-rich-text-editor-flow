package com.vaadin.flow.component.richtexteditor.test;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.richtexteditor.testbench.RichTextEditorElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicUseIT extends AbstractParallelTest {

    @Before
    public void init() {
        getDriver().get(getBaseURL());
        ButtonElement setValue = getTestButton("setValue");
        setValue.click();
    }

    @Test
    public void rteIsPresent() {
        Assert.assertTrue($(RichTextEditorElement.class).exists());
    }

    @Test
    public void setValueCorrectly() {
        setHTML("<p>Bar</p>");
        ButtonElement getValue = getTestButton("getValue");
        ButtonElement getHtmlValue = getTestButton("getHtmlValue");

        waitUntil(driver -> {
            getValue.click();
            getHtmlValue.click();
            return getLastValue().equals("[{\"insert\":\"Bar\\n\"}]") &&
                   getLastHtmlValue().equals("<p>Bar</p>");
        });

        Assert.assertEquals("[{\"insert\":\"Bar\\n\"}]", getLastValue());
        Assert.assertEquals("<p>Bar</p>", getLastHtmlValue());
    }

    @Test
    public void getValueCorrect() {
        ButtonElement getValue = getTestButton("getValue");
        getValue.click();

        Assert.assertEquals("[{\"insert\":\"Foo\"}]", getLastValue());
    }

    @Test
    public void getHtmlValueCorrect() {
        ButtonElement getHtmlValue = getTestButton("getHtmlValue");
        getHtmlValue.click();

        Assert.assertEquals("<p>Foo</p>", getLastHtmlValue());
    }

    @Test
    public void getHtmlValueChangeEventCorrect() {
        setHTML("<p>Bar</p>");
        waitUntil(driver -> Integer.parseInt(getLastEventCount()) == 2);
        setHTML("<p>Baz</p>");
        waitUntil(driver -> Integer.parseInt(getLastEventCount()) == 3);
    }

    private ButtonElement getTestButton(String id) {
        return $(ButtonElement.class).onPage().id(id);
    }

    private void setHTML(String s) {
        $(RichTextEditorElement.class).waitForFirst().getEditor().setProperty("innerHTML", s);
    }
}
