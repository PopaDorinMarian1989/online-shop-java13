package org.fasttackit.onlineshop;

import org.fasttackit.onlineshop.domain.Cart;
import org.fasttackit.onlineshop.domain.Customer;
import org.fasttackit.onlineshop.domain.Product;
import org.fasttackit.onlineshop.service.CartService;
import org.fasttackit.onlineshop.steps.CustomerSteps;
import org.fasttackit.onlineshop.steps.ProductSteps;
import org.fasttackit.onlineshop.transfer.AddProductToCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceIntegratonTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerSteps customerSteps;
    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testAddProductToCart_whenNewCartForExistingCustomer_thenCartIsSaved() {
        Customer customer = customerSteps.createCustomer();
        Product product = productSteps.createProduct();

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setCustomerId(customer.getId());
        request.setProductId(product.getId());

        cartService.addProductToCart(request);

        Cart cart = cartService.getCart(customer.getId());

        assertThat(cart.getId(), is(customer.getId()));



    }
}
