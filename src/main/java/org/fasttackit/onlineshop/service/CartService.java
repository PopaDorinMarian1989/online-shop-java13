package org.fasttackit.onlineshop.service;

import org.fasttackit.onlineshop.domain.Cart;
import org.fasttackit.onlineshop.domain.Customer;
import org.fasttackit.onlineshop.persistance.CartRepository;
import org.fasttackit.onlineshop.transfer.AddProductToCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;

    @Autowired

    public CartService(CartRepository cartRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
    }

    @Transactional
    public void addProductToCart(AddProductToCartRequest request) {
        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());

        if (cart.getCustomer() == null) {
            LOGGER.info("New cart will be created. " + "Retrieving customer {} to map the relationship",
                    request.getCustomerId());

            Customer customer = customerService.getCustomer(request.getCustomerId());

            cart.setCustomer(customer);

        }

        cartRepository.save(cart);


    }
}
