package com.stripe.customers.list.repository;

import org.springframework.stereotype.Repository;
import com.stripe.customers.list.domain.Customer;
import java.util.List;


/**
 * Spring Data  repository for the Customer entity.
 */
@Repository
public interface CustomerRepository {

    /**
     * Finds Top Customers (i.e. those who spent the most amount of money)
     */
    List<Customer> getAllTopCustomers() throws Exception;
}
