package com.project.gameHubBackend.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDTO {

    @Size(min = 3)
    private String customerName;
    @Size(min = 3)
    private String customerSurname;

    @Email
    @Size(min = 3)
    private String customerEmail;

    @Email
    @Size(min = 3)
    private String deliveryEmail;

    @NotBlank
    private String deliveryType;
    @NotNull
    private List<DesiredGameDTO> games;
    private String pickupPointName;
    private String customerAddressCity;
    private String customerAddressPostalCode;
    private String customerAddressStreet;
}
