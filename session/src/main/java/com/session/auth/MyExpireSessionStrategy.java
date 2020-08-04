package com.session.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyExpireSessionStrategy implements SessionInformationExpiredStrategy {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","您的账号在另一台设备登陆了，被迫下线");

        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write(
                objectMapper.writeValueAsString(map)
        );
    }
}
