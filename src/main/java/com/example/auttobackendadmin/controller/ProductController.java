package com.example.auttobackendadmin.controller;

import com.example.auttobackendadmin.common.dto.CommandSuccessResponse;
import com.example.auttobackendadmin.dto.request.RegisterProductRequest;
import com.example.auttobackendadmin.exception.ProductRegisteration.ProductAccessException;
import com.example.auttobackendadmin.security.UserPrincipal;
import com.example.auttobackendadmin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/product")
    public ResponseEntity<CommandSuccessResponse> registerIndividualProduct(@Validated @RequestBody RegisterProductRequest request,
                                                    @AuthenticationPrincipal UserPrincipal userPrincipal) {
       log.info("공연 정보 생성 요청 api 시작");
        if(!userPrincipal.isAdmin()){
            throw new ProductAccessException();
        }
        productService.registerIndividualProduct(request.toServiceDto());
       log.info("공연 정보 생성 요청 api 종료");
        return new ResponseEntity<>(CommandSuccessResponse.from("상품이 등록되었습니다."), CREATED);
    }
}
