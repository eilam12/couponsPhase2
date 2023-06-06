package com.example.Project2.repositories;

import com.example.Project2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query(value = "delete from customers_coupons where customers_id=?1", nativeQuery = true)
    void deletePurchaseByCustomerId(int customerId);
}
