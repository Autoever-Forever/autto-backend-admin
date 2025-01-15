package com.example.auttobackendadmin.event;

import com.example.auttobackendadmin.entity.Inventory;
import com.example.auttobackendadmin.exception.InventoryValidation.NotExistingSeatException;
import com.example.auttobackendadmin.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InventoryEventHandler {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void handle(InventoryEvent event) {

        Inventory inventory = inventoryRepository.findByIdForUpdate(event.getInventoryId())
                .orElseThrow(NotExistingSeatException::new);
        processCancellation(inventory, event);
    }

    private void processCancellation(Inventory inventory, InventoryEvent event) {
        inventory.cancelSeats(event.getQuantity());
    }
} 