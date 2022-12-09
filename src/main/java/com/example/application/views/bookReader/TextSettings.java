package com.example.application.views.bookReader;

import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static com.example.application.views.bookReader.TextSettings.TextSettingsState.ThemeColor.*;
import static com.example.application.views.bookReader.TextSettings.TextSettingsState.ThemeFont.*;

interface TextSettingsDelegate {

    default void onFontWillChange(TextSettings.TextSettingsState.ThemeFont newFont,
                                  TextSettings.TextSettingsState.ThemeFont oldFont) {

    }

    default void onFontSizeWillChange(int size, int oldSize) {
    }

    ;


    default void onThemeWillChange(TextSettings.TextSettingsState.ThemeColor newColor,
                                   TextSettings.TextSettingsState.ThemeColor oldColor) {
    }

    ;
}

public class TextSettings extends Div {

    public final static int MIN_FONT_SIZE = 8;
    public final static int MAX_FONT_SIZE = 28;

    public final static TextSettingsState.ThemeFont[] fonts = new TextSettingsState.ThemeFont[]{
            GIGI,
            ROBOTO,
            VERDANA,
            ARIAL
    };

    public final static TextSettingsState.ThemeColor[] colors = new TextSettingsState.ThemeColor[]{
            LIGHT,
            GREY,
            BEIGE,
            NIGHT
    };

    private final Button minFontButton;
    private final Button maxFontButton;
    private final Label currentFontSize;
    private final Select<TextSettingsState.ThemeFont> fontSelect;
    private final Select<TextSettingsState.ThemeColor> colorSelect;

    private final TranslationProvider translationProvider = new TranslationProvider();

    private final TextSettingsState state;
    private final Set<TextSettingsDelegate> textSettingsDelegates = new HashSet<>();

    public TextSettings() {
        this(TextSettingsState.DEFAULT_FONT_SIZE);
    }

    public TextSettings(int fontSize) {
        this(fontSize, TextSettingsState.DEFAULT_COLOR);
    }

    public TextSettings(int fontSize, TextSettingsState.ThemeColor color) {
        this(fontSize, color, TextSettingsState.DEFAULT_FONT_NAME);
    }

    public TextSettings(int fontSize, TextSettingsState.ThemeColor color, TextSettingsState.ThemeFont fontName) {
        state = new TextSettingsState(fontSize, color, fontName);

        currentFontSize = new Label();
        currentFontSize.setText(String.valueOf(TextSettingsState.DEFAULT_FONT_SIZE));
        currentFontSize.addClassNames("current-font-size");

        this.minFontButton = new Button(this.translationProvider.getTranslation("decrease",
                UI.getCurrent().getLocale()));
        minFontButton.addClickListener(this::minFontButtonClick);

        this.maxFontButton = new Button(this.translationProvider.getTranslation("increase",
                UI.getCurrent().getLocale()));
        maxFontButton.addClickListener(this::maxFontButtonClick);

        this.fontSelect = new Select<>();
        this.fontSelect.setItems(fonts);
        this.fontSelect.setEmptySelectionAllowed(false);
        this.fontSelect.setValue(fonts != null && fonts.length > 0 ? fonts[0] : TextSettingsState.DEFAULT_FONT_NAME);
        this.fontSelect.addValueChangeListener(this::onFontSelected);
        this.fontSelect.setWidth("120px");

        this.colorSelect = new Select<>();
        this.colorSelect.setItems(colors);
        this.colorSelect.setEmptySelectionAllowed(false);
        this.colorSelect.setValue(colors != null && colors.length > 0 ? colors[0] : TextSettingsState.DEFAULT_COLOR);
        this.colorSelect.addValueChangeListener(this::onColorSelected);
        this.colorSelect.setWidth("100px");


        this.addClassNames("text-settings");

        HorizontalLayout upButton = new HorizontalLayout(minFontButton, currentFontSize, maxFontButton,
                fontSelect, colorSelect);

        add(upButton);
    }

    private void onFontSelected(AbstractField.ComponentValueChangeEvent<Select<TextSettingsState.ThemeFont>,
            TextSettingsState.ThemeFont> event) {
        this.state.setFontName(event.getValue());
        System.out.println("Current font is " + this.state.getFontName());

        if (textSettingsDelegates != null && textSettingsDelegates.size() > 0) {
            textSettingsDelegates.forEach(delegate -> {
                delegate.onFontWillChange(event.getValue(), event.getOldValue());
            });
        }
    }

    private void onColorSelected(AbstractField.ComponentValueChangeEvent<Select<TextSettingsState.ThemeColor>,
            TextSettingsState.ThemeColor> event) {
        this.state.setColor(event.getValue());
        System.out.println("Current font is" + this.state.getColor());

        if (textSettingsDelegates != null && textSettingsDelegates.size() > 0) {
            textSettingsDelegates.forEach(delegate -> {
                delegate.onThemeWillChange(event.getValue(), event.getOldValue());
            });
        }
    }

    public void addDelegate(TextSettingsDelegate textSettingsDelegate) {
        this.textSettingsDelegates.add(textSettingsDelegate);
    }

    private void minFontButtonClick(ClickEvent<Button> buttonClickEvent) {
        final int previousSize = state.size;
        final int currentSize = state.size - 1;
        if (currentSize <= MAX_FONT_SIZE) {
            maxFontButton.setEnabled(true);
        }
        if (currentSize < MIN_FONT_SIZE) {
            minFontButton.setEnabled(false);
        }

        state.size = currentSize;
        this.currentFontSize.setText(String.valueOf(currentSize));
        if (textSettingsDelegates != null && textSettingsDelegates.size() > 0) {
            textSettingsDelegates.forEach(delegate -> {
                delegate.onFontSizeWillChange(currentSize, previousSize);
            });
        }

    }

    private void maxFontButtonClick(ClickEvent<Button> buttonClickEvent) {
        final int previousSize = state.size;
        final int currentSize = state.size + 1;
        if (currentSize >= MIN_FONT_SIZE) {
            minFontButton.setEnabled(true);
        }
        if (currentSize >= MAX_FONT_SIZE) {
            maxFontButton.setEnabled(false);
        } else if (currentSize <= MIN_FONT_SIZE) {
            maxFontButton.setEnabled(true);
        }
        state.size = currentSize;
        this.currentFontSize.setText(String.valueOf(currentSize));

        if (textSettingsDelegates != null && textSettingsDelegates.size() > 0) {
            textSettingsDelegates.forEach(delegate -> {
                delegate.onFontSizeWillChange(currentSize, previousSize);
            });
        }
    }

    @Getter
    @Setter
    static class TextSettingsState {
        private final static int DEFAULT_FONT_SIZE = 14;
        private final static ThemeColor DEFAULT_COLOR = LIGHT;
        private final static ThemeFont DEFAULT_FONT_NAME = GIGI;
        private int size;
        private ThemeColor color;
        private ThemeFont fontName;

        public TextSettingsState() {
            this(DEFAULT_FONT_SIZE);
        }

        public TextSettingsState(int size) {
            this(size, DEFAULT_COLOR);
        }

        public TextSettingsState(int size, ThemeColor color) {
            this(size, color, DEFAULT_FONT_NAME);
        }

        public TextSettingsState(int size, ThemeColor color, ThemeFont fontName) {
            this.size = size;
            this.color = color;
            this.fontName = fontName;
        }

        enum ThemeColor {
            LIGHT,
            GREY,
            BEIGE,
            NIGHT
        }

        enum ThemeFont {
            GIGI,
            ROBOTO,
            VERDANA,
            ARIAL
        }

    }
}
