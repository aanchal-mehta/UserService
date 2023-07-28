package com.immersion.useraudit.user.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class User {
    private String id;
    private String name;
    private String email;
    private String contactNumber;
    private String dateOfBirth;
    private String gender;
    private String role;
    @Transient
    private String correlationId;
    private Boolean isActive;
    private List<Address> address;
}
