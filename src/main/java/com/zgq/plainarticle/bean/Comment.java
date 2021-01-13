package com.zgq.plainarticle.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class Comment {
    private int commentId;
    private int userId;
    private int receiveUserId;
    private int articleId;
    private String commentContent;
    private String commentIp;
    private Date commentTime;
}
