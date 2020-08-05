package com.jwt;

import com.jwt.entity.SysUser;
import com.jwt.utils.JwtTokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZVRpbWUiOjE1OTY2Mjg5NDQ2MTgsImV4cCI6NTc0Nzg2NDIwMDYyODQwMH0.WroMZcsCRxsPlwyBxFMYBKFqt3yTSwFMArbyxcx_0Xg";

    @Test
    public void test1() {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60);
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(expirationDate);
    }

    @Test
    public void test2() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        System.out.println(this.jwtTokenUtils.isTokenExpired((token)));
        System.out.println(this.jwtTokenUtils.getUsernameByToken(token));
        //String token = this.jwtTokenUtils.generateToken(sysUser);
        /*System.out.println("token是否过期："+this.jwtTokenUtils.isTokenExpired(token));
        System.out.println("token信息："+this.jwtTokenUtils.getUsernameByToken(token));
        System.out.println("token是否合法："+this.jwtTokenUtils.validToken(token,sysUser));
        System.out.println("刷新后的token值为："+token);
        System.out.println("刷新后的token值为："+this.jwtTokenUtils.refreshToken(token));*/
    }


}
