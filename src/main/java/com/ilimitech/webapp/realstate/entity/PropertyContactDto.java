package com.ilimitech.webapp.realstate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PropertyContactDto {

    private Integer id;
    private String name;
    private String lastName;
    private String cellphone;
    private String whatsapp;
    private String email;
    private String chat;
}
