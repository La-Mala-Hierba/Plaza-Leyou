package com.leyou.auth.config;

import com.leyou.common.utils.RsaUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@ConfigurationProperties(prefix = "leyou.jwt")
@Data
public class JwtProperties {

    private String secret;

    private String pubKeyPath;

    private String priKeyPath;

    private int expire;// token expireTime

    private String cookieName;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    /**
     * @PostContruct：Procede el método despúes del método constructor
     */
    @PostConstruct
    public void init(){
        try {
            File pubKey = new File(pubKeyPath);
            File priKey = new File(priKeyPath);
            if (!pubKey.exists() || !priKey.exists()) {
                // generar publicKey y privateKey
                RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
            }
            // obetener publicKey y privateKey
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            logger.error("¡Iniciación de claves pública y privada fallida!", e);
            throw new RuntimeException();
        }
    }

}