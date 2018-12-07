package com.stripe.customers.list.domain;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private final String id;
    private final String name;
    private final Long amountSpent;
    private final Boolean isDelinquent;
    private final String source;

    private Customer(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.amountSpent = builder.amountSpent;
        this.isDelinquent = builder.isDelinquent;
        this.source = builder.source;
    }

    public static class Builder {
        private String id;
        private String name;
        private Long amountSpent;
        private Boolean isDelinquent;
        private String source;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder amountSpent(Long amountSpent) {
            this.amountSpent = amountSpent;
            return this;
        }

        public Builder isDelinquent(Boolean isDelinquent) {
            this.isDelinquent = isDelinquent;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getAmountSpent() {
        return amountSpent;
    }

    public Boolean isDelinquent() {
        return isDelinquent;
    }

    public String getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
            Objects.equals(name, customer.name) &&
            Objects.equals(amountSpent, customer.amountSpent) &&
            Objects.equals(isDelinquent, customer.isDelinquent) &&
            Objects.equals(source, customer.source);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, amountSpent, isDelinquent, source);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", amountSpent=" + amountSpent +
            ", isDelinquent='" + isDelinquent + '\'' +
            ", source='" + source + '\'' +
            '}';
    }
}
