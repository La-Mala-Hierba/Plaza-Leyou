package com.leyou.user.service.impl;

import com.leyou.common.utils.NumberUtils;
import com.leyou.user.pojo.SmsVerifyCode;
import com.leyou.user.pojo.User;
import com.leyou.user.repository.SmsVerifyCodeRepository;
import com.leyou.user.repository.UserRepository;
import com.leyou.user.service.UserService;
import com.leyou.user.utils.CodecUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SmsVerifyCodeRepository smsVerifyCodeRepository;

    /**
     * Verificar los datos del usuario
     * @param data
     * @param type
     * @return
     */
    @Override
    public Boolean checkUser(String data, Integer type) {

        Specification<User> spec = null;
        if (type == 1){
            spec = (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), data)));
        }else if (type == 2){
            spec = (((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("phone"), data)));
        }else {
            return null;
        }

        return this.userRepository.count(spec) == 0;

    }

    /**
     * Enviar el código de verificación
     * @param phone
     * @return
     */
    @Override
    public void sendVerifyCode(String phone) {

        if (StringUtils.isBlank(phone)){
            return;
        }

        //Generar el código de verificación
        Map<String, String> msgMap = new HashMap<>();
        String code = NumberUtils.generateCode(6);
        msgMap.put("phone", phone);
        msgMap.put("code", code);

        //Enviar mensaje al RabbitMQ
        this.amqpTemplate.convertAndSend("verifyCode.sms", msgMap);

        //Guardar el código de verificación en MongoDb
        SmsVerifyCode smsVerifyCode = new SmsVerifyCode();
        smsVerifyCode.setId(UUID.randomUUID().toString());
        smsVerifyCode.setCode(code);
        smsVerifyCode.setPhone(phone);
        smsVerifyCode.setCreateTime(new Date());
        this.mongoTemplate.save(smsVerifyCode);
    }

    /**
     * Registro de cuenta
     * @param user
     * @param code
     * @return
     */
    @Override
    public void register(User user, String code) {
        //0.Buscar el  código de verificación en MongoDB
        SmsVerifyCode mongoVerifyCode = this.smsVerifyCodeRepository.findByPhone(user.getPhone());

        //1. chequear el código de verificación
        if (!StringUtils.equals(code, mongoVerifyCode.getCode())){
            return;
        }

        //2. generar el salt y añadir el salt al user
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        //3. añadir el salt a la contraseña
        user.setPassword((CodecUtils.md5Hex(user.getPassword(), salt)));

        //4 registra el usuario
        user.setId(null);
        user.setCreated(new Date());
        //4.1 Eliminar el código de verificación en MongoDB para evitar multi-registro
        this.smsVerifyCodeRepository.delete(mongoVerifyCode);
        //this.mongoTemplate.remove(mongoVerifyCode);

        this.userRepository.save(user);
    }

    /**
     * Buscar el usuario con username y password
     * @param username
     * @param password
     * @return
     */
    @Override
    public User queryUser(String username, String password) {
        //query user
        User user = this.userRepository.findByUsername(username);

        //verify user
        if (user == null){
            return null;
        }

        String salt = user.getSalt();
        password = CodecUtils.md5Hex(password, salt);

        //verify password
        if (StringUtils.equals(password, user.getPassword())){
            return user;
        }else {
            return null;
        }
    }
}
