package com.inventary.inventary.controller;

import com.inventary.inventary.dto.ServiceResponse;
import com.inventary.inventary.dto.UpdateQuantityRequest;
import com.inventary.inventary.model.Inventory;
import com.inventary.inventary.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InventoryController
{

    @Autowired
    private InventoryService service;

    @PostMapping
    public ResponseEntity<ServiceResponse<Inventory>> createInventory(@RequestBody Inventory inventory) {
        try {
            Inventory savedInventory = service.save(inventory).getData();
            return ResponseEntity.ok(ServiceResponse.success(savedInventory));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(ServiceResponse.error("Error al crear inventario",
                    Collections.singletonList(e.getMessage())));
        }
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<Inventory>>> getAllInventory() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/update-quantity")
    public ResponseEntity<ServiceResponse<Inventory>> updateQuantity(@RequestBody UpdateQuantityRequest request) {
        try {
            Inventory updatedInventory = service.updateQuantity(request.getProductId(), request.getQuantity()).getData();
            return ResponseEntity.ok(ServiceResponse.success(updatedInventory));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(ServiceResponse.error("Error al actualizar cantidad",
                    Collections.singletonList(e.getMessage())));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteInventoryByProductId(@PathVariable String productId) {

             service.deleteInventoryByProductId(productId);


        return ResponseEntity.noContent().build();

    }


}












