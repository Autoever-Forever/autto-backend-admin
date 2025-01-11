package com.example.auttobackendadmin.entity;

import com.example.auttobackendadmin.exception.InventoryValidation.NotEnoughSeatException;
import jakarta.persistence.*;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "seat_by_date_inventory")
public class Inventory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Column(name = "reserved_seats")
    private Integer reservedSeats;

    @Column(name = "total_seats")
    private Integer totalSeats;

    protected Inventory() {
        super(BaseStatus.ACTIVE);
    }

    public Inventory(Product product,
                     LocalDateTime date,
                     BigDecimal price,
                     String currencyCode,
                     Integer reservedSeats,
                     Integer totalSeats) {
        super(BaseStatus.ACTIVE);
        this.product = product;
        this.date = date;
        this.price = price;
        this.currencyCode = currencyCode;
        this.reservedSeats = reservedSeats;
        this.totalSeats = totalSeats;
    }

    public void reserveSeats(int quantity) {
        int availableSeats = this.totalSeats - this.reservedSeats;
        if (availableSeats < quantity) {
            throw new NotEnoughSeatException();
        }
        this.reservedSeats += quantity;
    }

    public int getAvailableSeats() {
        return this.totalSeats - this.reservedSeats;
    }
} 