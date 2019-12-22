package com.soa.zuul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author 19028
 * @date 2019/12/21 0:19
 */

@ConfigurationProperties(prefix = "soa.filter")
@RefreshScope
@Data
@Component
public class FilterProperties {
    private String whitelist;
}
