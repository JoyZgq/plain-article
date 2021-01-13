package com.zgq.plainarticle.bean;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    @NotNull
    private String userName;
    private String userPassword;
    private int userStatus;
    private int userLevel;
    private String userEmail;

}
