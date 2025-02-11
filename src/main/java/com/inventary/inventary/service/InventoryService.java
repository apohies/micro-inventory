package com.inventary.inventary.service;

import com.inventary.inventary.dto.ServiceResponse;
import com.inventary.inventary.model.Inventory;
import com.inventary.inventary.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import java.util.Collections;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public ServiceResponse<List<Inventory>> findAll() {
        try {
            List<Inventory> inventories = repository.findAll();
            return ServiceResponse.success(inventories);
        } catch (Exception e) {
            return ServiceResponse.error("Error al obtener inventarios",
                    Collections.singletonList(e.getMessage()));
        }
    }

    public ServiceResponse<Inventory> findById(Long id) {
        try {
            return repository.findById(id)
                    .map(ServiceResponse::success)
                    .orElse(ServiceResponse.error("Inventario no encontrado",
                            Collections.singletonList("No existe inventario con ID: " + id)));
        } catch (Exception e) {
            return ServiceResponse.error("Error al buscar inventario",
                    Collections.singletonList(e.getMessage()));
        }
    }


    public ServiceResponse<Inventory> save(Inventory inventory) {
        try {
            // Validar si ya existe un inventario con ese productId
            Inventory existingInventory = repository.findByProductId(inventory.getProductId());

            if (existingInventory != null) {
                return ServiceResponse.error("Error al crear inventario",
                        Collections.singletonList("Ya existe un inventario para el producto con ID: " + inventory.getProductId()));
            }

            if (inventory.getQuantity() == null || inventory.getQuantity() < 0) {
                return ServiceResponse.error("Error de validación",
                        Collections.singletonList("La cantidad debe ser mayor o igual a 0"));
            }

            Inventory savedInventory = repository.save(inventory);
            return ServiceResponse.success(savedInventory);

        } catch (Exception e) {
            return ServiceResponse.error("Error al guardar inventario",
                    Collections.singletonList(e.getMessage()));
        }
    }

    public ServiceResponse<Inventory> updateQuantity(String productId, Integer quantity) {
        try {
            Inventory inventory = repository.findByProductId(productId);


            if (inventory == null) {

                return ServiceResponse.error("Error al actualizar inventario",
                        Collections.singletonList("No existe inventario para el producto con ID: " + productId));
            }

            inventory.setQuantity(quantity);
            Inventory updatedInventory = repository.save(inventory);
            return ServiceResponse.success(updatedInventory);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Log para debug
            return ServiceResponse.error("Error al actualizar cantidad",
                    Collections.singletonList(e.getMessage()));
        }
    }
    @Transactional
    public void deleteInventoryByProductId(String productId) {
        repository.deleteByProductId(productId);
    }
}
