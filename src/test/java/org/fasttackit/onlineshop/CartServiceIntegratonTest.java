package org.fasttackit.onlineshop;

import org.fasttackit.onlineshop.domain.Customer;
import org.fasttackit.onlineshop.service.CartService;
import org.fasttackit.onlineshop.steps.CustomerSteps;
import org.fasttackit.onlineshop.transfer.AddProductToCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegratonTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerSteps customerSteps;
    @Test
    public void testAddProductToCart_whenNewCartForExistingCustomer_thenCartIsSaved(){
        Customer customer = customerSteps.createCustomer();

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setCustomerId(customer.getId());

        cartService.addProductToCart(request);


    }
}
