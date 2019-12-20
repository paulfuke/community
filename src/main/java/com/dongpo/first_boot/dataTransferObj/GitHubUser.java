package com.dongpo.first_boot.dataTransferObj;

import lombok.Data;

@Data
public class GitHubUser {
    private String bio;
    private Long id;
//    gitHub公开的姓名
    private String name;
//    github的用户名/账号
    private String login;
}
