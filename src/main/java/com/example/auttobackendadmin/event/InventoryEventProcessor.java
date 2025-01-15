package com.example.auttobackendadmin.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryEventProcessor {
    private final InventoryEventHandler inventoryEventHandler;
    
    @KafkaListener(topics = "inventory-events", groupId = "inventory-group")
    public void processInventoryEvent(InventoryEvent event) {
        log.info("Received inventory event: {}", event);
        inventoryEventHandler.handle(event);
    }
} 