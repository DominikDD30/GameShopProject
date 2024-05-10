package com.project.gameHubBackend.business.dao;

import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Purchase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomerDao {
    Optional<Customer> getByEmail(String customerEmail);

    void issuePurchase(Customer customer);

}
