package com.vaadin.flow.component.richtexteditor;

/*
 * #%L
 * Vaadin Rich Text Editor for Vaadin 10
 * %%
 * Copyright (C) 2017 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file license.html distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <http://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.HasValueChangeMode;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.internal.JsonSerializer;
import elemental.json.JsonObject;

import java.util.Objects;

import java.io.Serializable;

/**
 * Server-side component for the {@code <vaadin-rich-text-editor>} component.
 *
 * @author Vaadin Ltd
 *
 */
@Tag("vaadin-rich-text-editor")
@HtmlImport("frontend://bower_components/vaadin-rich-text-editor/src/vaadin-rich-text-editor.html")
public class RichTextEditor extends GeneratedVaadinRichTextEditor<RichTextEditor, String>
    implements HasSize, HasValueChangeMode, InputNotifier, KeyNotifier, CompositionNotifier {

    private ValueChangeMode currentMode;
    private RTEI18n i18n;

    /**
     * Gets the internationalization object previously set for this component.
     * <p>
     * Note: updating the object content that is gotten from this method will
     * not update the lang on the component if not set back using
     * {@link RichTextEditor#setI18n(RTEI18n)}
     *
     * @return the i18n object. It will be <code>null</code>, If the i18n
     *         properties weren't set.
     */
    public RTEI18n getI18n() {
        return i18n;
    }

    /**
     * Sets the internationalization properties for this component.
     *
     * @param i18n
     *            the internationalized properties, not <code>null</code>
     */
    public void setI18n(RTEI18n i18n) {
        Objects.requireNonNull(i18n,
                "The I18N properties object should not be null");
        this.i18n = i18n;
        runBeforeClientResponse(ui -> {
            if (i18n == this.i18n) {
                JsonObject i18nObject = (JsonObject) JsonSerializer
                        .toJson(this.i18n);
                for (String key : i18nObject.keys()) {
                    ui.getPage().executeJavaScript(
                            "$0.set('i18n." + key + "', $1)", getElement(),
                            i18nObject.get(key));
                }
            }
        });
    }

    void runBeforeClientResponse(SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }

    /**
     * Constructs an empty {@code RichTextEditor}.
     */
    public RichTextEditor() {
        super("", "", false);
        setValueChangeMode(ValueChangeMode.ON_CHANGE);
    }

    /**
     * Constructs a {@code RichTextEditor} with the initial value
     *
     * @param initialValue
     *            the initial value
     *
     * @see #setValue(Object)
     */
    public RichTextEditor(String initialValue) {
        this();
        setValue(initialValue);
    }

    /**
     * Constructs an empty {@code TextField} with a value change listener.
     *
     * @param listener
     *            the value change listener
     *
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public RichTextEditor(
            ValueChangeListener<? super ComponentValueChangeEvent<RichTextEditor, String>> listener) {
        this();
        addValueChangeListener(listener);
    }

    /**
     * Constructs an empty {@code RichTextEditor} with a value change
     * listener and an initial value.
     *
     * @param initialValue
     *            the initial value
     * @param listener
     *            the value change listener
     *
     * @see #setValue(Object)
     * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
     */
    public RichTextEditor(String initialValue,
            ValueChangeListener<? super ComponentValueChangeEvent<RichTextEditor, String>> listener) {
        this();
        setValue(initialValue);
        addValueChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default value is {@link ValueChangeMode#ON_CHANGE}.
     */
    @Override
    public ValueChangeMode getValueChangeMode() {
        return currentMode;
    }

    @Override
    public void setValueChangeMode(ValueChangeMode valueChangeMode) {
        currentMode = valueChangeMode;
        setSynchronizedEvent(
                ValueChangeMode.eventForMode(valueChangeMode, "value-changed"));
    }

    /**
     * Sets the value of this editor. If the new value is not equal to
     * {@code getValue()}, fires a value change event. Throws
     * {@code NullPointerException}, if the value is null.
     * <p>
     * Note: {@link Binder} will take care of the {@code null} conversion when
     * integrates with the editor, as long as no new converter is defined.
     *
     * @param value
     *            the new value, not {@code null}
     */
    @Override
    public void setValue(String value) {
        super.setValue(value);
    }

    /**
     * Returns the current value of the text editor. By default, the empty
     * editor will return an empty string.
     *
     * @return the current value.
     */
    @Override
    public String getValue() {
        return super.getValue();
    }


    /**
     * Value of the editor presented as HTML string.
     *
     * @return the {@code htmlValue} property from the webcomponent
     */
    public String getHtmlValue() {
        return getHtmlValueString();
    }

    /**
     * The internationalization properties for {@link RichTextEditor}.
     */
    public static class RTEI18n implements Serializable {
        private String undo;
        private String redo;
        private String bold;
        private String italic;
        private String underline;
        private String strike;
        private String h1;
        private String h2;
        private String h3;
        private String subscript;
        private String superscript;
        private String listOrdered;
        private String listBullet;
        private String alignLeft;
        private String alignCenter;
        private String alignRight;
        private String image;
        private String blockquote;
        private String codeBlock;
        private String clean;

        public String getUndo() {
            return undo;
        }

        public RTEI18n setUndo(String undo) {
            this.undo = undo;
            return this;
        }

        public String getRedo() {
            return redo;
        }

        public RTEI18n setRedo(String redo) {
            this.redo = redo;
            return this;
        }

        public String getBold() {
            return bold;
        }

        public RTEI18n setBold(String bold) {
            this.bold = bold;
            return this;
        }

        public String getItalic() {
            return italic;
        }

        public RTEI18n setItalic(String italic) {
            this.italic = italic;
            return this;
        }

        public String getUnderline() {
            return underline;
        }

        public RTEI18n setUnderline(String underline) {
            this.underline = underline;
            return this;
        }

        public String getStrike() {
            return strike;
        }

        public RTEI18n setStrike(String strike) {
            this.strike = strike;
            return this;
        }

        public String getH1() {
            return h1;
        }

        public RTEI18n setH1(String h1) {
            this.h1 = h1;
            return this;
        }

        public String getH2() {
            return h2;
        }

        public RTEI18n setH2(String h2) {
            this.h2 = h2;
            return this;
        }

        public String getH3() {
            return h3;
        }

        public RTEI18n setH3(String h3) {
            this.h3 = h3;
            return this;
        }

        public String getSubscript() {
            return subscript;
        }

        public RTEI18n setSubscript(String subscript) {
            this.subscript = subscript;
            return this;
        }

        public String getSuperscript() {
            return superscript;
        }

        public RTEI18n setSuperscript(String superscript) {
            this.superscript = superscript;
            return this;
        }

        public String getListOrdered() {
            return listOrdered;
        }

        public RTEI18n setListOrdered(String listOrdered) {
            this.listOrdered = listOrdered;
            return this;
        }

        public String getListBullet() {
            return listBullet;
        }

        public RTEI18n setListBullet(String listBullet) {
            this.listBullet = listBullet;
            return this;
        }

        public String getAlignLeft() {
            return alignLeft;
        }

        public RTEI18n setAlignLeft(String alignLeft) {
            this.alignLeft = alignLeft;
            return this;
        }

        public String getAlignCenter() {
            return alignCenter;
        }

        public RTEI18n setAlignCenter(String alignCenter) {
            this.alignCenter = alignCenter;
            return this;
        }

        public String getAlignRight() {
            return alignRight;
        }

        public RTEI18n setAlignRight(String alignRight) {
            this.alignRight = alignRight;
            return this;
        }

        public String getImage() {
            return image;
        }

        public RTEI18n setImage(String image) {
            this.image = image;
            return this;
        }

        public String getBlockquote() {
            return blockquote;
        }

        public RTEI18n setBlockquote(String blockquote) {
            this.blockquote = blockquote;
            return this;
        }

        public String getCodeBlock() {
            return codeBlock;
        }

        public RTEI18n setCodeBlock(String codeBlock) {
            this.codeBlock = codeBlock;
            return this;
        }

        public String getClean() {
            return clean;
        }

        public RTEI18n setClean(String clean) {
            this.clean = clean;
            return this;
        }
    }
}
