package com.dongpo.first_boot.dataTransferObj;

import lombok.Data;
/**
 * 封装和github登录时传输的参数
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
