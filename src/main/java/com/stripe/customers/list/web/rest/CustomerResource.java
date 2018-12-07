package com.stripe.customers.list.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.stripe.Stripe;
import com.stripe.customers.list.domain.Customer;
import com.stripe.customers.list.repository.CustomerRepository;
import com.stripe.customers.list.web.rest.errors.BadRequestAlertException;
import com.stripe.customers.list.web.rest.util.HeaderUtil;
import com.stripe.customers.list.web.rest.util.PaginationUtil;
import com.stripe.exception.StripeException;
import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {
    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);
    private final CustomerRepository customerRepository;

    public CustomerResource(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * GET  /customers : get all the customers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     */
    @GetMapping("/customers")
    @Timed
    public ResponseEntity<List<Customer>> getAllCustomers(final Pageable pageable) throws Exception {
        log.debug("REST request to get a page of Customers");
        final List<Customer> customers = customerRepository.getAllTopCustomers();
        final Page<Customer> newPage = new PageImpl<>(customers, pageable, customers.size());

        final HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(newPage, "/api/customers");
        return ResponseEntity.ok().headers(headers).body(newPage.getContent());
    }
}
