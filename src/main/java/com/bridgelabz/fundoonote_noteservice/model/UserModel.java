package com.bridgelabz.fundoonote_noteservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private long userId;
    private String firstName;
    private  String lastname;
    private String email;
    private String password;
    private String token;
    private boolean verify;
    private boolean isVerified;
}
