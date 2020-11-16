package com.leyou.cart.config;

import com.leyou.common.utils.RsaUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@ConfigurationProperties(prefix = "leyou.jwt")
@Data
public class JwtProperties {

    private String pubKeyPath;// Clave pública

    private String cookieName;

    private PublicKey publicKey; // Clave pública


    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    /**
     * @PostContruct：Procede el método despúes del método constructor
     */
    @PostConstruct
    public void init(){
        try {
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            logger.error("¡Iniciación de claves pública!", e);
            throw new RuntimeException();
        }
    }

}