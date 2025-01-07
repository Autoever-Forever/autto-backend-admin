package com.example.auttobackendadmin.service;

import com.example.auttobackendadmin.dto.requestDto.RegisterProductRequestDto;
import com.example.auttobackendadmin.exception.NotExistsProductException;
import com.example.auttobackendadmin.entity.Product;
import com.example.auttobackendadmin.entity.SeatByDateInventory;
import com.example.auttobackendadmin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void registerIndividualProduct(RegisterProductRequestDto dto) {
        Product product = dto.toEntity();
        
        // 좌석 인벤토리 생성 및 연결
        dto.getSeatInventories().forEach(seatDto -> {
            SeatByDateInventory inventory = new SeatByDateInventory(
                product,
                seatDto.getDate(),
                seatDto.getPrice(),
                seatDto.getCurrencyCode(),
                seatDto.getReservedSeats(),
                seatDto.getTotalSeats()
            );
            product.addSeatInventory(inventory);
        });

        productRepository.save(product);
    }
}
