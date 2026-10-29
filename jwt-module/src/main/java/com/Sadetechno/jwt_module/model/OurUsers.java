package com.Sadetechno.jwt_module.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "user_authentication") // Maps to your MongoDB collection
public class OurUsers implements UserDetails {

    @Id // Tells MongoDB to use this as the primary key
    private String id; 

    private String name;
    private String phoneNumber;
    private String email; 
    private String password;
    private String role = "USER";

    @JsonIgnore 
    private String confirmPassword;

    private String createdAt; 
    private boolean isEnabled;

    // --- Spring Security Methods ---

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @JsonIgnore
    @Override
    public String getUsername() { return email; }

    @JsonIgnore @Override public boolean isAccountNonExpired() { return true; }
    @JsonIgnore @Override public boolean isAccountNonLocked() { return true; }
    @JsonIgnore @Override public boolean isCredentialsNonExpired() { return true; }
    
    @JsonIgnore
    @Override public boolean isEnabled() { return isEnabled; }
}