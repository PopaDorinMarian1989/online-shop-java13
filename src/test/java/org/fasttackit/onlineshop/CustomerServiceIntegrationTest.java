package org.fasttackit.onlineshop;

import org.fasttackit.onlineshop.domain.Customer;
import org.fasttackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttackit.onlineshop.service.CustomerService;
import org.fasttackit.onlineshop.steps.CustomerSteps;
import org.fasttackit.onlineshop.transfer.SaveCustomerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerSteps customerSteps;

    @Test
    public void testCreateCustomer_whenValidRequest_thenCustomerIsSaved() {
        customerSteps.createCustomer();
    }


    @Test(expected = TransactionSystemException.class)
    public void testCreateCustomer_whenInvalidRequest_thenThrowException() {
        SaveCustomerRequest request = new SaveCustomerRequest();
        // leaving rquest propertioes with default null value to validate the negative flow
        customerService.createCustomer(request);

    }

    @Test
    public void testGetCustomer_whenExistingCustomer_thenReturnCustomer() {
        Customer createCustomer = customerSteps.createCustomer();

        Customer customer = customerService.getCustomer(createCustomer.getId());
        assertThat(customer, notNullValue());
        assertThat(customer.getId(), is(createCustomer.getId()));
        assertThat(customer.getFisrtName(), is(createCustomer.getFisrtName()));
        assertThat(customer.getLastName(), is(createCustomer.getLastName()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetCustomer_whenNonExistingCustomer_thenThrowResouurceNotFoundException() {
        customerService.getCustomer(999999L);
    }

    @Test
    public void testUpdateCustomer_whenValidRequest_thenReturnUpadatedCustomer() {
        Customer createdCustomer = customerSteps.createCustomer();

        SaveCustomerRequest request = new SaveCustomerRequest();
        request.setFisrtName(createdCustomer.getFisrtName() + "updated");
        request.setLastName(createdCustomer.getLastName() + 10);
        Customer updatedCustomer = customerService.updateCustomer(createdCustomer.getId(), request);

        assertThat(updatedCustomer, notNullValue());
        assertThat(updatedCustomer.getId(), is(createdCustomer.getId()));
        assertThat(updatedCustomer.getFisrtName(), is(request.getFisrtName()));
        assertThat(updatedCustomer.getLastName(), is(request.getLastName()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteCustomer_whenExistingCustomer_thenCustomerIsDeleted() {
        Customer customer = customerSteps.createCustomer();

        customerService.deleteCustomer(customer.getId());

        customerService.getCustomer(customer.getId());
    }



}
