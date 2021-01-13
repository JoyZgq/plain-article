package com.zgq.plainarticle.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Reply {
    private int replyId;
    private String replyContent;
    private int userId;
    private int articleId;
    private Date replyTime;
    private int receive_user_id;

}
