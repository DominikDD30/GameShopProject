package com.project.gameHubBackend.infrastructure.database.entity;

import com.project.gameHubBackend.domain.DeliveryType;
import com.project.gameHubBackend.domain.OrderStatus;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "purchaseId")
@ToString(of = {"purchaseId", "purchaseNumber", "dateStarted", "dateCompleted", "status","deliveryType"})
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseId;

    @Column(name = "purchase_number", unique = true)
    private String purchaseNumber;

    @Column(name = "date_started")
    @Basic
    private OffsetDateTime dateStarted;

    @Column(name = "date_completed")
    @Basic
    private OffsetDateTime dateCompleted;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column(name = "delivery_type")
    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(name = "email_to_send")
    private String emailToSend;

    @Column(name = "pickup_point")
    private  String pickupPointName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "purchase",fetch = FetchType.LAZY)
    private List<GamePurchaseEntity> gamePurchases;

}
