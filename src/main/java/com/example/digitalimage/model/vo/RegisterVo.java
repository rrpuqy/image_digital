package com.example.digitalimage.model.vo;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ToString
public class RegisterVo {

    private Long userId;


    private String userName;


    private Integer gender;

    private Date birthdate;

    private String email;

    @Length(max = 11,min = 11)
    private String phone;
    private String nick;
}
