package com.project.gameHubBackend.business;

import com.project.gameHubBackend.business.dao.CustomerDao;
import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDao customerDao;
    public Optional<Customer> getCustomerByEmail(String customerEmail) {
        return customerDao.getByEmail(customerEmail);
    }

    public void issuePurchase(Customer customer) {
        customerDao.issuePurchase(customer);
    }


}
