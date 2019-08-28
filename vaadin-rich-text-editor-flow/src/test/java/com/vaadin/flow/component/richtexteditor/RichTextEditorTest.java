package com.vaadin.flow.component.richtexteditor;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;

/**
 * Tests for the {@link RichTextEditor}.
 */
public class RichTextEditorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void setValueNull() {
        RichTextEditor rte = new RichTextEditor();
        assertEquals("Value should be an empty string", "",
                rte.getValue());

        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Null value is not supported");

        rte.setValue(null);
    }

    @Test
    public void initialValuePropertyValue() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals(rte.getEmptyValue(),
                rte.getElement().getProperty("value"));
    }

    @Test
    public void initialHtmlValueNull() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertNull("Initial htmlValue should not through NPE",
                rte.getHtmlValue());
    }

    // Decoration group sanitization

    @Test
    public void sanitizeStrongTag_StrongTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<strong>Foo</strong>", rte.sanitize("<strong>Foo</strong>"));
    }

    @Test
    public void sanitizeEmTag_EmTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<em>Foo</em>", rte.sanitize("<em>Foo</em>"));
    }

    @Test
    public void sanitizeUTag_UTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<u>Foo</u>", rte.sanitize("<u>Foo</u>"));
    }

    @Test
    public void sanitizeSTag_STagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<s>Foo</s>", rte.sanitize("<s>Foo</s>"));
    }

    @Test
    public void sanitizeCombinedDecorationTags_AllTagsPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<strong><em><s><u>123123</u></s></em></strong>", rte.sanitize("<strong><em><s><u>123123</u></s></em></strong>"));
    }

    // Headers group sanitization

    @Test
    public void sanitizeH1Tag_H1TagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<h1>Foo</h1>", rte.sanitize("<h1>Foo</h1>"));
    }

    @Test
    public void sanitizeH2Tag_H2TagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<h2>Foo</h2>", rte.sanitize("<h2>Foo</h2>"));
    }

    @Test
    public void sanitizeH3Tag_H3TagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<h3>Foo</h3>", rte.sanitize("<h3>Foo</h3>"));
    }

    // Super - / Sub - scripts group sanitization

    @Test
    public void sanitizeSupTag_SupTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<sup>Foo</sup>", rte.sanitize("<sup>Foo</sup>"));
    }

    @Test
    public void sanitizeSubTag_SubTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<sub>Foo</sub>", rte.sanitize("<sub>Foo</sub>"));
    }

    // Lists group sanitization

    @Test
    public void sanitizeOrderedListTag_OrderedListTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<ol>\n Foo\n</ol>", rte.sanitize("<ol>Foo</ol>"));
    }

    @Test
    public void sanitizeBulletListTag_BulletListTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<ul>\n Foo\n</ul>", rte.sanitize("<ul>Foo</ul>"));
    }

    @Test
    public void sanitizeListElementTag_listElementTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<li>Foo</li>", rte.sanitize("<li>Foo</li>"));
    }

    // Alignment group sanitization

    @Test
    public void sanitizeStyleTextAlign_StyleTextAlignPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<p style=\"text-align: center\">Foo</p>", rte.sanitize("<p style=\"text-align: center\">Foo</p>"));
    }

    // Script sanitization

    @Test
    public void sanitizeScriptTag_scriptTagRemoved() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("", rte.sanitize("<script>alert('Foo')</script>"));
    }

    // Image sanitization

    @Test
    public void sanitizeImgTagWithHttpSource_srcAttributeRemoved() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<img>", rte.sanitize("<img src='http://vaadin.com'>"));
    }

    @Test
    public void sanitizeImgTagWithHttpsSource_srcAttributeRemoved() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<img>", rte.sanitize("<img src='https://vaadin.com'>"));
    }

    @Test
    public void sanitizeImgTagWithDataSource_srcAttributePersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<img src=\"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==\">",
                rte.sanitize("<img src=\"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==\">"));
    }

    // Blockquote sanitization

    @Test
    public void sanitizeBlockquoteTag_blockquoteTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<blockquote>\n Foo\n</blockquote>", rte.sanitize("<blockquote>Foo</blockquote>"));
    }

    // Code block sanitization

    @Test
    public void sanitizePreTag_preTagPersist() {
        RichTextEditor rte = new RichTextEditor();
        Assert.assertEquals("<pre>Foo</pre>", rte.sanitize("<pre>Foo</pre>"));
    }
}
