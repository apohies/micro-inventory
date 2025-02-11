package com.inventary.inventary.dto;

import lombok.Data;

@Data
public class UpdateQuantityRequest {
    private String productId;
    private Integer quantity;
}