package com.example.auttobackendadmin.entity;

import com.example.auttobackendadmin.common.domain.BaseEntity;
import com.example.auttobackendadmin.common.domain.BaseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String location;

    @Column(name = "running_time")
    private String runningTime;

    @Column(name = "ticketing_limit")
    private Integer ticketingLimit;

    @Column(name = "ticketing_open_date")
    private LocalDateTime ticketingOpenDate;

    @Column(name = "ticketing_close_date")
    private LocalDateTime ticketingCloseDate;

    @Column(name = "perform_start_date")
    private LocalDateTime performStartDate;

    @Column(name = "perform_end_date")
    private LocalDateTime performEndDate;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "poster_url")
    private String posterUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inventory> seatInventories = new ArrayList<>();

    protected Product() {
        super(BaseStatus.ACTIVE);
    }

    public Product(String title,
                  String location,
                  String runningTime,
                  Integer ticketingLimit,
                  LocalDateTime ticketingOpenDate,
                  LocalDateTime ticketingCloseDate,
                  LocalDateTime performStartDate,
                  LocalDateTime performEndDate,
                  String thumbnailUrl,
                  String posterUrl) {
        super(BaseStatus.ACTIVE);
        this.title = title;
        this.location = location;
        this.runningTime = runningTime;
        this.ticketingLimit = ticketingLimit;
        this.ticketingOpenDate = ticketingOpenDate;
        this.ticketingCloseDate = ticketingCloseDate;
        this.performStartDate = performStartDate;
        this.performEndDate = performEndDate;
        this.thumbnailUrl = thumbnailUrl;
        this.posterUrl = posterUrl;
    }

    public void addSeatInventory(Inventory inventory) {
        this.seatInventories.add(inventory);
    }
}
