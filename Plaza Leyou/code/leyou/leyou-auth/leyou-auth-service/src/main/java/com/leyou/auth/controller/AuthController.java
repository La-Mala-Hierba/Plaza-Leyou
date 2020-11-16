package com.leyou.auth.controller;

import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * El Centro de Autenticación verifica los datos del acceso y genera jwt token
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> accredit(
            @RequestParam("username") String username, @RequestParam("password") String password,
            HttpServletRequest request, HttpServletResponse response){

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ResponseEntity.badRequest().build();
        }

        String token = this.authService.accredit(username, password);

        if (StringUtils.isBlank(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();// 401
        }

        //añadir jwt in cookie
        CookieUtils.setCookie(request, response,
                this.jwtProperties.getCookieName(), token,
                this.jwtProperties.getExpire()*60, null, true);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Verificar los datos del usuario
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("PLY_TOKEN") String token,
                                           HttpServletRequest request, HttpServletResponse response){
        if (StringUtils.isBlank(token)){
            return ResponseEntity.badRequest().build();
        }

        try {
            UserInfo user = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());

            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();//401
            }

            //actualiza expireTime de Jwt a través de generar un token nuevo
            token = JwtUtils.generateToken(user, this.jwtProperties.getPrivateKey(), this.jwtProperties.getExpire());

            //actualiza expireTime de Cookie por volver a establcerlo
            CookieUtils.setCookie(request, response, this.jwtProperties.getCookieName(), token, this.jwtProperties.getExpire()*60);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
