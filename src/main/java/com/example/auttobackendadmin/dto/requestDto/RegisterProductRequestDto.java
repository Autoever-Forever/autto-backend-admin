package com.example.auttobackendadmin.dto.requestDto;


import com.example.auttobackendadmin.dto.SeatInventoryRequest;
import com.example.auttobackendadmin.entity.Product;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RegisterProductRequestDto {

    @Column(nullable = false)
    private String title;

    private String location;

    private String runningTime;

    private Integer ticketingLimit;

    @Column(nullable = false)
    private LocalDateTime ticketingOpenDate;

    @Column(nullable = false)
    private LocalDateTime ticketingCloseDate;

    @Column(nullable = false)
    private LocalDateTime performStartDate;

    @Column(nullable = false)
    private LocalDateTime performEndDate;

    private String thumbnailUrl;
    private String posterUrl;

    private List<SeatInventoryRequest> seatInventories;

    @Builder
    public RegisterProductRequestDto(String title, String location, String runningTime, Integer ticketingLimit, LocalDateTime ticketingOpenDate, LocalDateTime ticketingCloseDate, LocalDateTime performStartDate, LocalDateTime performEndDate, String thumbnailUrl, String posterUrl, List<SeatInventoryRequest> seatInventories) {
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
        this.seatInventories = seatInventories;
    }

    public Product toEntity(){
        return new Product(
                title,
                location,
                runningTime,
                ticketingLimit,
                ticketingOpenDate,
                ticketingCloseDate,
                performStartDate,
                performEndDate,
                thumbnailUrl,
                posterUrl
        );
    }
}
