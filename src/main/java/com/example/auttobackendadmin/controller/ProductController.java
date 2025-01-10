package com.example.auttobackendadmin.controller;

import com.example.auttobackendadmin.common.dto.CommandSuccessResponse;
import com.example.auttobackendadmin.dto.RegisterProductRequest;
import com.example.auttobackendadmin.exception.ProductRegisteration.ProductAccessException;
import com.example.auttobackendadmin.security.UserPrincipal;
import com.example.auttobackendadmin.service.ProductService;
import com.example.auttobackendadmin.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.HttpStatus.CREATED;


@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final FileService fileService;

    @PostMapping("/admin/product")
    public ResponseEntity<CommandSuccessResponse> registerIndividualProduct(@Validated @ModelAttribute RegisterProductRequest request,
                                                                            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if(!userPrincipal.isAdmin()) {
            throw new ProductAccessException();
        }

        log.info("공연 정보 생성 요청 api 시작");
        String thumbnailUrl = uploadImage(request.getThumbnail(), request.getTitle());
        String posterUrl = uploadImage(request.getPoster(), request.getTitle());
        productService.registerIndividualProduct(request.toServiceDto(thumbnailUrl, posterUrl));
        log.info("공연 정보 생성 요청 api 종료");

        return new ResponseEntity<>(CommandSuccessResponse.from("상품이 등록되었습니다."), CREATED);
    }


    private String uploadImage(MultipartFile file, String directory) {
        return file != null ? fileService.uploadFile(file, directory) : null;
    }
}
