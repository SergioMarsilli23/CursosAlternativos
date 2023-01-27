package mx.com.tutum.shared.infrastructure.config.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class MessageSourceConfig {

    @Value("${i18n.basename}")
    private String MESSAGE_BUNDLE;

    @Value("${i18n.locale.default-value}")
    private String DEFAULT_LOCALE;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultLocale(Locale.forLanguageTag(DEFAULT_LOCALE));
        messageSource.setBasename("classpath:" + MESSAGE_BUNDLE);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

}
