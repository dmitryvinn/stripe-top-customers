package com.stripe.customers.list.repository;

import com.stripe.Stripe;
import com.stripe.customers.list.domain.Customer;
import com.stripe.customers.list.service.util.StripeApiUtility;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StripeCustomerRepository implements CustomerRepository {
    /**
     * Currently, only support USD in order to find top spenders among all customers
     */


    @Override
    public List<Customer> getAllTopCustomers() throws Exception {
        return StripeApiUtility.getAllStripeCustomers().stream()
            .map(StripeCustomerRepository::convertStripeCustomer)
            .sorted(Comparator.comparingLong(Customer::getAmountSpent)) // Finds Top Spender between two Customers
            .collect(Collectors.toList());
    }

    /**
     * Converts Stripe customer into Customer format
     */
    private static Customer convertStripeCustomer(com.stripe.model.Customer stripeCustomer) {
        return new Customer.Builder()
            .id(stripeCustomer.getId())
            .name(stripeCustomer.getEmail())
            .amountSpent(StripeApiUtility.findTotalAmountSpent(stripeCustomer))
            .isDelinquent(stripeCustomer.getDelinquent())
            // TODO: Add validation due to metadata flexibility
            .source(stripeCustomer.getMetadata().get("customer_source"))
            .build();
    }


}
