package com.example.auttobackendadmin.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HexStringToUUIDConverter {

    public static UUID convertHexToUUID(String hex) {
        // "0x" 접두사 제거
        hex = hex.replace("0x", "");

        // 하이픈 추가하여 표준 UUID 형식으로 변환
        String uuid = hex.toLowerCase()
                .replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{12})",
                        "$1-$2-$3-$4-$5");

        return UUID.fromString(uuid);
    }
}
