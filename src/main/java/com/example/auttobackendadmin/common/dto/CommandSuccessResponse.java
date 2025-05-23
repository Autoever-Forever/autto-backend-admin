package com.example.auttobackendadmin.common.dto;

import lombok.Getter;

@Getter
public class CommandSuccessResponse {

    private String message;

    public CommandSuccessResponse() {};

    CommandSuccessResponse(String message) {
        this.message = message;
    }

    public static CommandSuccessResponse from(String message){
        return new CommandSuccessResponse(message);
    }
}
