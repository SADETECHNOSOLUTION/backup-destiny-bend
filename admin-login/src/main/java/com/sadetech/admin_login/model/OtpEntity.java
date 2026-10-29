package com.sadetech.admin_login.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpEntity {

    // Appwrite uses String IDs (mapped from $id)
    private String id;

    private String email;
    private String otp;
    private String otpType;
    private String text;
    private String emailOtpContent;
    
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructor matching your EmailService call: 
    // new OtpEntity(to, otp, otpType, text, LocalDateTime.now())
    public OtpEntity(String email, String otp, String otpType, String text, LocalDateTime createdAt) {
        this.email = email;
        this.otp = otp;
        this.otpType = otpType;
        this.text = text;
        this.createdAt = createdAt;
    }
}