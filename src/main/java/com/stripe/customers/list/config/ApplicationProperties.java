package com.stripe.customers.list.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Stripe Customers List.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private final ApplicationProperties.StripeApiKey stripeApiKey = new ApplicationProperties.StripeApiKey();

    public ApplicationProperties() {}

    public ApplicationProperties.StripeApiKey getStripeApiKey() {
        return this.stripeApiKey;
    }


    public static class StripeApiKey {
        private String value;

        public StripeApiKey() {
        }

        public String getValue() {
            return value;
        }

        public StripeApiKey setValue(String value) {
            this.value = value;
            return this;
        }
    }
}
