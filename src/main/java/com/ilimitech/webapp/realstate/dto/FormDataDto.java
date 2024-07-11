package com.ilimitech.webapp.realstate.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FormDataDto {
    private String textInput;
    private String passwordInput;
    private String textareaInput;
    private String selectInput;
    private String radioInput;
    private List<String> checkboxInput;
    private String dateInput;
    private MultipartFile fileInput;

    // Getters and Setters
}