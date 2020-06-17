package com.oktfolio.tero.modules.sms;

import com.oktfolio.tero.model.BaseModel;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class SmsDO extends BaseModel {

    private static final long serialVersionUID = 7311275111841576916L;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 接受者手机号
     */
    private String phone;
    /**
     * 发送账号安全认证的Access Key ID
     */
    private String accessKey;
    /**
     * 发送账号安全认证的Secret Access Key
     */
    private String secretKey;
    /**
     * 发送使用签名
     */
    private String signName;
    /**
     * 模板 code
     */
    private String templateCode;
    /**
     * SMS服务域名 百度/其他第三方需要
     */
    private String endPoint;
    /**
     * 短信参数
     * 腾讯是数组
     * 阿里百度 是json
     */
    private String templateParams;
}
