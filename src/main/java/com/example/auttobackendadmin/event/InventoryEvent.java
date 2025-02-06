package com.example.auttobackendadmin.event;

import com.example.auttobackendadmin.common.event.Event;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class InventoryEvent extends Event {
    @JsonProperty("productId")
    private UUID inventoryId;
    
    @JsonProperty("seatCount")
    private int quantity;
}