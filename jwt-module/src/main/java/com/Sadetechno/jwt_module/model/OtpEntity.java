package com.Sadetechno.jwt_module.model;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpEntity {

    private String id;
    private String email;
    private String otp;
    private String otpType;
    private String text;
    private LocalDateTime createdAt;
    private String name;
    private String phoneNumber;
    private String password;

    // This constructor matches your EmailService call:
    // new OtpEntity(to, otp, otpType, text, LocalDateTime.now())
    public OtpEntity(String email, String otp, String otpType, String text, LocalDateTime createdAt) {
        this.email = email;
        this.otp = otp;
        this.otpType = otpType;
        this.text = text;
        this.createdAt = createdAt;
    }
}