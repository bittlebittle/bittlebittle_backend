package com.spring.bittlebittle.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userNo;
    private String userId, userPwd, userName, nickname, email, phone, status, adminYn;

}
