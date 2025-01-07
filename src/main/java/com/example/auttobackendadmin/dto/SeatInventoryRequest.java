package com.example.auttobackendadmin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SeatInventoryRequest {
    private LocalDateTime date;
    private BigDecimal price;
    private String currencyCode;
    private Integer reservedSeats;
    private Integer totalSeats;
}