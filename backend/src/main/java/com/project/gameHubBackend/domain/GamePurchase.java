package com.project.gameHubBackend.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "gamePurchaseId")
@ToString(of = {"gamePurchaseId","quantity","game","platform","price"})
public class GamePurchase {
   private Integer gamePurchaseId;
   private String game;
   private String gameImage;
   private Integer quantity;
   private String platform;
   private BigDecimal price;
   private Purchase purchase;
}
