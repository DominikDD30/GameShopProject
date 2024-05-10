package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.domain.OrderStatus;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.infrastructure.database.entity.PurchaseEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.PurchaseJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.PurchaseEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Repository
@AllArgsConstructor
public class PurchaseRepository {

    private PurchaseJpaRepository purchaseJpaRepository;
    private PurchaseEntityMapper purchaseEntityMapper;

    public List<Purchase> getPurchasesForCustomer(String customerEmail) {
        List<PurchaseEntity> purchaseEntities = purchaseJpaRepository.findAllByCustomerCustomerEmail(customerEmail);
        return purchaseEntities.stream().map(purchaseEntityMapper::mapFromEntity).toList();
    }


    public void changePurchaseStatus(String customerEmail, String purchaseNumber, OrderStatus orderStatus) {
        purchaseJpaRepository.changePurchaseStatusForCustomer(customerEmail,purchaseNumber,orderStatus.name());
    }

    public BigDecimal getTotalIncome(LocalDate dateFrom, LocalDate dateTo) {
        OffsetDateTime from = OffsetDateTime.of(dateFrom, LocalTime.now(), ZoneOffset.UTC);
        OffsetDateTime to = OffsetDateTime.of(dateTo, LocalTime.now(), ZoneOffset.UTC);
        return purchaseJpaRepository.getIncomeFromPeriod(from,to);
    }

    public List<Purchase> getPurchasesFromPeriod(LocalDate dateFrom, LocalDate dateTo) {
        OffsetDateTime from = OffsetDateTime.of(dateFrom, LocalTime.now(), ZoneOffset.UTC);
        OffsetDateTime to = OffsetDateTime.of(dateTo, LocalTime.now(), ZoneOffset.UTC);
        return purchaseJpaRepository.getPurchaseEntityByDateCompletedBetween(from,to)
                .stream().map(purchaseEntity->purchaseEntityMapper.mapFromEntity(purchaseEntity)).toList();
    }

    public List<Purchase> getPurchasesSortDESC() {
        return purchaseJpaRepository.getRecentPurchase()
                .stream().map(purchaseEntityMapper::mapFromEntity).toList();
    }

    public List<Purchase> getPurchasesWithoutDateCompleted() {
       return purchaseJpaRepository.getPurchasesWithoutDateCompleted().
               stream().map(purchaseEntityMapper::mapFromEntityWithoutExtras).toList();
    }

    @Transactional
    public void setDateCompleted(Purchase purchase, OffsetDateTime dateCompleted) {
        purchaseJpaRepository.updatePurchaseStatusAndDateCompleted(purchase.getPurchaseId(),dateCompleted);
    }
}
