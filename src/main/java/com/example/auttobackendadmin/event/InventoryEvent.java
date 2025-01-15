package com.example.auttobackendadmin.event;

import com.example.auttobackendadmin.common.event.Event;
import com.example.auttobackendadmin.util.HexToUUIDDeserializer;
import com.example.auttobackendadmin.util.UUIDToHexSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class InventoryEvent extends Event {
    @JsonSerialize(using = UUIDToHexSerializer.class)
    @JsonDeserialize(using = HexToUUIDDeserializer.class)
    private UUID inventoryId;
    private Integer quantity;
    private EventType eventType;

    public enum EventType {
        RESERVED,
        CANCELED
    }
} 