package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.InvoiceDTO;
import com.project.gameHubBackend.api.dto.PurchaseDTO;
import com.project.gameHubBackend.api.dto.PurchaseRequestDTO;
import com.project.gameHubBackend.api.dto.mapper.PurchaseMapper;
import com.project.gameHubBackend.api.dto.mapper.PurchaseRequestMapper;
import com.project.gameHubBackend.business.OpinionService;
import com.project.gameHubBackend.business.purchaseService.PurchaseService;
import com.project.gameHubBackend.domain.OrderStatus;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.domain.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.gameHubBackend.api.controller.PurchaseController.PURCHASES;

@RestController
@AllArgsConstructor
@RequestMapping(PURCHASES)
public class PurchaseController {

    public static final String PURCHASES = "/purchases";

    private PurchaseService purchaseService;
    private PurchaseRequestMapper purchaseRequestMapper;

    private OpinionService opinionService;
    private PurchaseMapper purchaseMapper;

    @PostMapping
    public ResponseEntity<InvoiceDTO> makePurchase(@Valid @RequestBody PurchaseRequestDTO purchaseRequestDTO) {
        PurchaseRequest purchaseRequest = purchaseRequestMapper.map(purchaseRequestDTO);
        try{
            Purchase purchase = purchaseService.makePurchase(purchaseRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(purchaseMapper.mapGamePurchase(purchase));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
    }


    @PatchMapping("/{purchaseNumber}/customers/{customerEmail}")
    public void changePurchaseStatus(
            @PathVariable String purchaseNumber,@PathVariable String customerEmail,@RequestParam OrderStatus orderStatus) {
        purchaseService.changePurchaseStatus(customerEmail,purchaseNumber,orderStatus);
    }

    @GetMapping
    public List<PurchaseDTO>getRecentPurchases(){
        List<Purchase> purchases = purchaseService.getRecentPurchase();
        return purchases.stream().map(purchaseMapper::map).toList();
    }

    @GetMapping("/customers/{customerEmail}")
    public List<PurchaseDTO> getPurchasesHistoryForCustomer(@PathVariable String customerEmail) {
        List<Purchase> purchases = purchaseService.getPurchasesForCustomer(customerEmail);
        List<PurchaseDTO> purchaseDTOList = purchases.stream().map(purchaseMapper::map).toList();


       return purchaseDTOList.stream().map(purchase->purchase.withGamePurchases(purchase.getGamePurchases().stream()
                .map(gamePurchase -> gamePurchase
                        .withOpinion(opinionService.findCustomerOpinionForPurchase(gamePurchase,customerEmail).orElse(null))).toList())).toList();
    }

}
