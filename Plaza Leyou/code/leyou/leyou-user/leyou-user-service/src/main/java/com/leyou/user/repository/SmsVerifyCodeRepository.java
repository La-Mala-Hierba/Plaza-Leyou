package com.leyou.user.repository;

import com.leyou.user.pojo.SmsVerifyCode;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SmsVerifyCodeRepository extends MongoRepository<SmsVerifyCode, String> {

    public SmsVerifyCode findByPhone(String phone);
}
