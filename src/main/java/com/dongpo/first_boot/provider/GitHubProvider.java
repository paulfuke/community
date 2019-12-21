package com.dongpo.first_boot.provider;

import com.alibaba.fastjson.JSON;
import com.dongpo.first_boot.dataTransferObj.AccessTokenDTO;
import com.dongpo.first_boot.dataTransferObj.GitHubUser;
import com.sun.org.apache.bcel.internal.generic.NEW;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    //根据传递数据获取token
    public String getGitHubToken(AccessTokenDTO accessTokenDTO){
        //GitHub要求必须发送post请求获取token
        //采用OKHTTP的形式
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(requestBody).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String s = response.body().string();
            /**
             * 获取的信息格式
             * access_token=e636f1cd48211aa70ba33150d0d6c849415686f4&scope=user&token_type=bearer
             */
            String[] split = s.split("&");
            String[] split1 = split[0].split("=");
            String token = split1[1];
            return token;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //通过token获取user信息，用户名等
    public GitHubUser getGitHubUser(String access_token){
        //通过get获取用户的信息
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request= new Request.Builder().url("https://api.github.com/user?access_token="+access_token).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            return JSON.parseObject(string,GitHubUser.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
