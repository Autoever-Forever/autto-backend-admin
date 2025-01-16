package com.example.auttobackendadmin.event;

import com.example.auttobackendadmin.entity.Inventory;
import com.example.auttobackendadmin.exception.InventoryValidation.NotExistingSeatException;
import com.example.auttobackendadmin.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InventoryEventHandler {
    private static final Logger log = LoggerFactory.getLogger(InventoryEventHandler.class);
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void handle(InventoryEvent event) {
        try {
            log.info("로그 : Processing inventory event. ID: {}, Quantity: {}",
                     event.getInventoryId(), event.getQuantity());
            
            Inventory inventory = inventoryRepository.findByIdForUpdate(event.getInventoryId())
                    .orElseThrow(() -> {
                        log.error("로그 : Inventory not found for ID: {}", event.getInventoryId());
                        return new NotExistingSeatException();
                    });
                    
            log.info("로그 : Found inventory. ID: {}, Current reserved seats: {}",
                     inventory.getId(), inventory.getReservedSeats());
                     
            processCancellation(inventory, event);
            
            log.info("로그 : Successfully updated inventory. ID: {}, New reserved seats: {}",
                     inventory.getId(), inventory.getReservedSeats());
                     
        } catch (Exception e) {
            log.error("로그 : Error processing inventory event. ID: {}, Error: {}",
                      event.getInventoryId(), e.getMessage(), e);
            throw e;
        }
    }

    private void processCancellation(Inventory inventory, InventoryEvent event) {
        try {
            log.info("로그 : Attempting to cancel {} seats for inventory ID: {}",
                     event.getQuantity(), inventory.getId());
            inventory.cancelSeats(event.getQuantity());
        } catch (Exception e) {
            log.error("로그 : Failed to cancel seats. ID: {}, Quantity: {}, Current reserved: {}",
                      inventory.getId(), event.getQuantity(), inventory.getReservedSeats(), e);
            throw e;
        }
    }
} 