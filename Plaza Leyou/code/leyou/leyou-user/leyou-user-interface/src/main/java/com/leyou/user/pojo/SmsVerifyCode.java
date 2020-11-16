package com.leyou.user.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "sms_verifycode")
public class SmsVerifyCode implements Serializable {

    @Id
    private String id;
    private String code;
    private String phone;
    @Indexed(expireAfterSeconds = 120)
    private Date createTime;

}
