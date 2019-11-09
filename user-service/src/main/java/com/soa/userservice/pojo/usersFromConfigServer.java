package com.soa.userservice.pojo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties("users")
public class usersFromConfigServer {
    private List<userInfo> list = new ArrayList<>();
    @Data
    public static class userInfo {
        private String username;
        private String password;
    }
}
