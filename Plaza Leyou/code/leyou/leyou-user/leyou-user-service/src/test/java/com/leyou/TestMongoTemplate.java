package com.leyou;

import com.leyou.user.pojo.SmsVerifyCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = LeyouUserApplication.class)
@RunWith(SpringRunner.class)
public class TestMongoTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        SmsVerifyCode smsVerifyCode = new SmsVerifyCode();
        smsVerifyCode.setId(UUID.randomUUID().toString());
        smsVerifyCode.setCode("13546666");
        smsVerifyCode.setPhone("617139666");
        smsVerifyCode.setCreateTime(new Date());
        this.mongoTemplate.save(smsVerifyCode);
    }

    /*
    *
    *
    *
    *
    * */
}
