package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.business.dao.CustomerDao;
import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.infrastructure.database.entity.CustomerEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.CustomerJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.GamePurchaseJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.PurchaseJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.CustomerEntityMapper;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.PurchaseEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDao {

    private CustomerJpaRepository customerJpaRepository;
    private PurchaseJpaRepository purchaseJpaRepository;
    private GamePurchaseJpaRepository gamePurchaseJpaRepository;
    private CustomerEntityMapper customerEntityMapper;
    private PurchaseEntityMapper purchaseEntityMapper;

    @Override
    public Optional<Customer> getByEmail(String customerEmail) {
        CustomerEntity customer = customerJpaRepository.findByEmail(customerEmail);
        return Objects.nonNull(customer) ?
                Optional.of(customerEntityMapper.mapFromEntity(customer)) :
                Optional.empty();
    }


    @Override
    public void issuePurchase(Customer customer) {
        CustomerEntity customerEntity=customerEntityMapper.mapToEntity(customer);
        customerJpaRepository.save(customerEntity);

        customer.getPurchases().stream()
                .filter(purchase -> Objects.isNull(purchase.getPurchaseId()))
                .map(purchase->purchaseEntityMapper.mapToEntity(purchase))
                .forEach(purchaseEntity->{
                    purchaseEntity.setCustomer(customerEntity);
                    purchaseJpaRepository.save(purchaseEntity);
                    purchaseEntity.getGamePurchases().forEach(gamePurchase -> {
                        gamePurchase.setPurchase(purchaseEntity);
                        gamePurchaseJpaRepository.save(gamePurchase);
                    });
                });
    }


}
