package com.leyou.auth.test;

import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;
import com.leyou.common.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "D:\\aaa\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\aaa\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "20180322_Heita!");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    /**
     * 通过私钥生成jwt token
     * @throws Exception
     */
    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(2L, "Heiya"), privateKey, 5);
        System.out.println("token = " + token);
    }

    /**
     * 通过公钥解析jwt token
     * @throws Exception
     */
    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJIZWl5YSIsImV4cCI6MTYwMzkyMjM3NH0.Ub-CnyHTqlfgWmxFLAEcB5W2UTPL5lm6J5sDGlEcqlIlfqEnnS-lqOxhM8FZpIqYgKB4ZCbx0TEKfemYel4DPaCzKNuGG20WEKfZ_g8jwopQeZz-6c20GvLVxllQULBnz3tk3cp1XeHg34S6wcygz5x5YNl7R9aakijUe_wSv78";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}