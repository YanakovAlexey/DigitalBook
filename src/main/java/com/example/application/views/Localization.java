package com.example.application.views;

import com.vaadin.flow.i18n.I18NProvider;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;


public class Localization implements I18NProvider {

    public static final String BUNDLE_PREFIX = "translate";

    public final Locale LOCALE_RU = new Locale("ru", "RU");
    public final Locale LOCALE_GE = new Locale("ge", "GE");

    private List<Locale> locales = Collections
            .unmodifiableList(Arrays.asList(LOCALE_RU, LOCALE_GE));


    public List<Locale> getProvidedLocales() {
        return locales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        if (key == null) {
            LoggerFactory.getLogger(Localization.class.getName())
                    .warn("Got lang request for key with null value!");
            return "";
        }

        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale);

        String value;
        try {
            value = bundle.getString(key);
        } catch (final MissingResourceException e) {
            LoggerFactory.getLogger(Localization.class.getName())
                    .warn("Missing resource", e);
            return "!" + locale.getLanguage() + ": " + key;
        }
        if (params.length > 0) {
            value = MessageFormat.format(value, params);
        }
        return value;
    }
}

