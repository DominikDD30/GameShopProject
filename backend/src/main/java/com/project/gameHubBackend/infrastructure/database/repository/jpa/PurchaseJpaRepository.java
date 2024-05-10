package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<PurchaseEntity, Integer> {


    PurchaseEntity findByPurchaseNumber(String purchaseNumber);

    @Query(value = """
    SELECT  * FROM purchase  pur
    ORDER BY pur.date_started desc
    limit 20
""",nativeQuery = true)
    List<PurchaseEntity> getRecentPurchase();
    @Query("""
    SELECT SUM(gamePurchases.price) FROM PurchaseEntity purchase
    JOIN  purchase.gamePurchases gamePurchases
    WHERE purchase.dateCompleted > :from AND purchase.dateCompleted < :to
""")
    BigDecimal getIncomeFromPeriod(@Param("from") OffsetDateTime from, @Param("to")OffsetDateTime to);


    @Query("""
SELECT purchase FROM PurchaseEntity purchase
LEFT JOIN FETCH purchase.address address
JOIN FETCH purchase.gamePurchases gamePurchases
WHERE purchase.customer.email = :customerEmail
ORDER BY purchase.dateStarted DESC
""")
    List<PurchaseEntity> findAllByCustomerCustomerEmail(@Param("customerEmail") String customerEmail);


    @Modifying
    @Query(value = """
           UPDATE purchase  
           SET status = :purchaseStatus
           where purchase_number = :purchaseNumber
           and customer_id = (SELECT customer_id FROM customer WHERE email= :customerEmail) 
            """
    ,nativeQuery = true)
    void changePurchaseStatusForCustomer(
           final @Param("customerEmail") String customerEmail,
           final @Param("purchaseNumber") String purchaseNumber,
           final @Param("purchaseStatus") String purchaseStatus);


    List<PurchaseEntity> getPurchaseEntityByDateCompletedBetween(OffsetDateTime dateFrom, OffsetDateTime dateTo);

    @Query("""
    SELECT purchase FROM PurchaseEntity purchase where purchase.dateCompleted is null 
    """)
    List<PurchaseEntity>  getPurchasesWithoutDateCompleted();

    @Modifying
    @Query("""
    UPDATE PurchaseEntity purchase 
        set purchase.dateCompleted = :dateCompleted,
        purchase.status = 'DELIVERED'
        where purchase.purchaseId = :purchaseId
    """)
    void updatePurchaseStatusAndDateCompleted(
            final @Param("purchaseId") Integer purchaseId,
            final @Param("dateCompleted") OffsetDateTime dateCompleted);
}
