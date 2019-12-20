package com.dongpo.first_boot.controller;

import com.dongpo.first_boot.dataTransferObj.AccessTokenDTO;
import com.dongpo.first_boot.dataTransferObj.GitHubUser;
import com.dongpo.first_boot.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;

//    配置文件注入github登录的参数
    @Value("${github.client.id}")
    private String github_client_id;
    @Value("${github.client.secret}")
    private String github_client_secret;
    @Value("${github.client.uri}")
    private String github_client_uri;

    //github登录认证后的回调
    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code") String code,
                         @RequestParam(value = "state") String state){
        //请求接口发送post请求
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(github_client_id);
        accessTokenDTO.setClient_secret(github_client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(github_client_uri);
        String gitHubToken = gitHubProvider.getGitHubToken(accessTokenDTO);
        //拿到token去获取用户信息
        GitHubUser gitHubUser = gitHubProvider.getGitHubUser(gitHubToken);
        System.out.println(gitHubUser);
        return "index";
    }
}
