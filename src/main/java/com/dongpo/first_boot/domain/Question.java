package com.dongpo.first_boot.domain;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer creator;
}
