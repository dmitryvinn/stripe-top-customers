package com.stripe.customers.list.service.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import io.github.jhipster.config.JHipsterConstants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Utility to interact with Stripe API
 * TODO: Move to dependency injection
 */
public class StripeApiUtility {
    private static final int MAX_PAGINATION = 100; // TODO: Move to config
    private static final Set<String> SUPPORTED_CURRENCIES = ImmutableSet.of("usd"); // TODO: Move to config

    static {
    }

    private StripeApiUtility() {}

    public static List<Customer> getAllStripeCustomers() throws Exception {
        final List<Customer> initialList = Customer.list(ImmutableMap.of("limit", MAX_PAGINATION)).getData();

       //TODO: Enable in Production
       // getAllStripeCustomers(initialList, Iterables.getLast(initialList).getId());

        return initialList;
    }

    /**
     * Recursive operation to find all Stripe customers available to current account
     */
    private static void getAllStripeCustomers(final List<Customer> combinedList,
                                              final String lastCustomerId) throws Exception {
        final List<Customer> customers = Customer.list(ImmutableMap.of("limit", MAX_PAGINATION,
            "starting_after", lastCustomerId)).getData();
        if (customers == null) {
            return;
        }
        combinedList.addAll(customers);
        if (customers.size() < MAX_PAGINATION) {
            return;
        }
        getAllStripeCustomers(combinedList, Iterables.getLast(customers).getId());
    }



    /**
     * Searches for largest amount spent by the customer
     */
    public static Long findTotalAmountSpent(final Customer customer) {
        try {
            // TODO: Add recursive pagination to count all charges
            return Charge.list(
                ImmutableMap.of("limit", MAX_PAGINATION,
                    "customer", customer.getId())).getData().stream()
                .filter(charge -> SUPPORTED_CURRENCIES.contains(charge.getCurrency()))
                .mapToLong(Charge::getAmount)
                .sum();
        } catch (final Exception e) {
            //TODO: Add proper logging; Fail Fast right now
            throw new RuntimeException(e);
        }
    }

    public static void initializeApi(final String apiKey) {
        Stripe.apiKey = apiKey;
    }
}
