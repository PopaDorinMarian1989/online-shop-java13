package org.fasttackit.onlineshop.persistance;

import org.fasttackit.onlineshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
