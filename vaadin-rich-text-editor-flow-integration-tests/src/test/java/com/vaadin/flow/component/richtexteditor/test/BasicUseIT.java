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
        $(RichTextEditorElement.class).waitForFirst().getEditor().setProperty("innerHTML", "<p>Bar</p>");
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
    public void setAndGetI18nCorrect() {
        ButtonElement setI18n = getTestButton("setI18n");
        ButtonElement getI18n = getTestButton("getI18n");
        setI18n.click();
        getI18n.click();

        Assert.assertEquals("{\"alignCenter\":\"15\",\"alignLeft\":\"14\",\"alignRight\":\"16\"," +
                "\"blockquote\":\"18\",\"bold\":\"3\",\"clean\":\"20\"," +
                "\"codeBlock\":\"19\",\"h1\":\"7\",\"h2\":\"8\",\"h3\":\"9\"," +
                "\"image\":\"17\",\"italic\":\"4\",\"listBullet\":\"13\"," +
                "\"listOrdered\":\"12\",\"redo\":\"2\",\"strike\":\"6\"," +
                "\"subscript\":\"10\",\"superscript\":\"11\",\"underline\":\"5\"," +
                "\"undo\":\"1\"}", getLastI18nValue());
    }

    private ButtonElement getTestButton(String id) {
        return $(ButtonElement.class).onPage().id(id);
    }
}
