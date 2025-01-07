package com.example.auttobackendadmin.dto;

import com.example.auttobackendadmin.dto.requestDto.RegisterProductRequestDto;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterProductRequest {

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

    public RegisterProductRequestDto toServiceDto(){
        return new RegisterProductRequestDto(
                title,
                location,
                runningTime,
                ticketingLimit,
                ticketingOpenDate,
                ticketingCloseDate,
                performStartDate,
                performEndDate,
                thumbnailUrl,
                posterUrl,
                seatInventories
        );
    }

}

