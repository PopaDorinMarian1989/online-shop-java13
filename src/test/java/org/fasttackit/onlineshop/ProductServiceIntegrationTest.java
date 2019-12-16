package org.fasttackit.onlineshop;

import org.fasttackit.onlineshop.domain.Product;
import org.fasttackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttackit.onlineshop.service.ProductService;
import org.fasttackit.onlineshop.steps.ProductSteps;
import org.fasttackit.onlineshop.transfer.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.transaction.TransactionalException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSteps productSteps;

    @Test
    public void testCreateProduct_whenValidRequest_thenProductIsSaved() {
        productSteps.createProduct();
    }


    @Test(expected = TransactionSystemException.class)
    public void testCreateProduct_whenInvalidRequest_thenThrowException() {
        SaveProductRequest request = new SaveProductRequest();
        // leaving rquest propertioes with default null value to validate the negative flow
        productService.createProduct(request);

    }

    @Test
    public void testGetProduct_whenExistingProduct_thenReturnProduct() {
        Product createProduct =productSteps.createProduct();

        Product product = productService.getProduct(createProduct.getId());
        assertThat(product, notNullValue());
        assertThat(product.getId(), is(createProduct.getId()));
        assertThat(product.getName(), is(createProduct.getName()));
        assertThat(product.getPrice(), is(createProduct.getPrice()));
        assertThat(product.getQuantity(), is(createProduct.getQuantity()));
        assertThat(product.getDescription(), is(createProduct.getDescription()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenNonExistingProduct_thenThrowResouurceNotFoundException() {
        productService.getProduct(999999L);
    }

    public void testUpdateProduct_whenValidRequest_thenReturnUpadatedProduct() {
        Product createdProduct = productSteps.createProduct();

        SaveProductRequest request = new SaveProductRequest();
        request.setName(createdProduct.getName() + "updated");
        request.setPrice(createdProduct.getPrice() + 10);
        request.setQuantity(createdProduct.getQuantity() + 10);
        request.setDescription(createdProduct.getDescription() + "updated");
        Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(createdProduct.getId()));
        assertThat(updatedProduct.getName(), is(request.getName()));
        assertThat(updatedProduct.getPrice(), is(request.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));
        assertThat(updatedProduct.getDescription(), is(request.getDescription()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteProduct_whenExistingProduct_thenProductIsDeleted() {
        Product product = productSteps.createProduct();

        productService.deleteProduct(product.getId());

        productService.getProduct(product.getId());
    }


}
