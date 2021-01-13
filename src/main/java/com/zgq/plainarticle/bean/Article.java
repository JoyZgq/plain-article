package com.zgq.plainarticle.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private int articleId;
    private int articleKeyword;
    private String articleTitle;
    private int articleViewer;
    private String articleCategory;
    private Date articleTime;
    private int userId;
    private String articleAbstract;
    private String articleContent;
}
