package com.makersy.equipment.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private Integer userPriority;

    private Integer userState;


}