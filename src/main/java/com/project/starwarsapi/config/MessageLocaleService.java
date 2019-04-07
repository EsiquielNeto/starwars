package com.project.starwarsapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageLocaleService {
    private final MessageSource messageSource;

    @Autowired
    public MessageLocaleService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    public String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

    public String getMessage(MessageSourceResolvable resolvable, Locale locale){
        return messageSource.getMessage(resolvable, locale);
    }
}
