package com.dongpo.first_boot.dataTransferObj;

import com.dongpo.first_boot.domain.User;
import lombok.Data;

@Data
public class QuestionDTO {
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
    private User user;
}
