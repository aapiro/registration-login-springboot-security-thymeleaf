package com.ilimitech.realstate.webapp.dto;

import lombok.Data;

@Data
public class UserContactDto {

    private Integer id;
    private String name;
    private String lastName;
    private String cellphone;
    private String whatsapp;
    private String email;
    private String chat;
}
