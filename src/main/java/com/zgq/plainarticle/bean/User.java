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
    @NotNull(message = "classId 不能为空")
    private String userName;
    private String userPassword;
    private int userStatus;
    private int userLevel;
    @Email(message = "email 格式不正确")
    private String userEmail;

}
