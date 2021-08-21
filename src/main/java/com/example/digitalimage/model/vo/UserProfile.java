package com.example.digitalimage.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserProfile {
    String nick;
    Integer gender;
    String signature;
    Date birthdate;
}
