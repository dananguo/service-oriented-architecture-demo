package com.soa.authservice.library.interceptor;

import com.soa.authservice.library.filter.HttpHeaderParamFilter;
import com.soa.authservice.library.support.RibbonFilterContextHolder;
import feign.RequestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 19028
 * @date 2019/12/22 0:01
 */
@Component

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    public FeignBasicAuthRequestInterceptor() {
    }

    @Override
    public void apply(feign.RequestTemplate template) {
        Map<String, String> attributes = RibbonFilterContextHolder.getCurrentContext().getAttributes();
        for (String key : attributes.keySet()) {
            String value = attributes.get(key);
            template.header(key, value);
        }
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HttpHeaderParamFilter httpHeaderParamFilter = new HttpHeaderParamFilter();
        registrationBean.setFilter(httpHeaderParamFilter);
        List<String> urlPatterns = new ArrayList<>(1);
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
