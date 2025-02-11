package com.inventary.inventary.repository;


import com.inventary.inventary.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductId(String productId);





    void deleteByProductId(String productId);


}