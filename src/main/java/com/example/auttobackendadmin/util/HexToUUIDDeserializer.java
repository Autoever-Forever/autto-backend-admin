package com.example.auttobackendadmin.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class HexToUUIDDeserializer extends JsonDeserializer<UUID> {
    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String hex = p.getValueAsString();
        return HexStringToUUIDConverter.convertHexToUUID(hex);
    }
} 