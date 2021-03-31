package com.linagora.ossa.mock;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Endpoint;
import javax.xml.ws.Holder;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.customerservice.Customer;
import com.example.customerservice.CustomerService;
import com.example.customerservice.NoSuchCustomerException;

public class CustomerServiceMock extends ExternalResource implements CustomerService {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceMock.class);

    private final String mockServiceURL;
    private Endpoint mockServiceEdp;
    private Customer customer;

    public CustomerServiceMock(final String mockServiceUrl) {
        this.mockServiceURL = mockServiceUrl;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        this.mockServiceEdp = Endpoint.publish(this.mockServiceURL, this);
    }

    @Override
    protected void after() {
        this.mockServiceEdp.stop();
        super.after();
    }


    @Override
    public void updateCustomer(Customer customer) {
        
        LOGGER.info("Entering update customer");
        customer = this.customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public List<Customer> getCustomersByName(String name, Holder<Integer> result1, Holder<Integer> result2) throws NoSuchCustomerException {
        LOGGER.info("Entering customer by name");
        result1.value = 1;
        result2.value = 1;
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(this.customer);
        return customers;
    }

}
