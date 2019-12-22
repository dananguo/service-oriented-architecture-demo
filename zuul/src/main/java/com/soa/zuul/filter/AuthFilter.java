package com.soa.zuul.filter;

import com.soa.zuul.library.base.ResponseCode;
import com.soa.zuul.library.base.ResponseData;
import com.soa.zuul.library.utils.JWTUtils;
import com.soa.zuul.library.utils.JsonUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.soa.zuul.config.FilterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 19028
 * @date 2019/12/21 0:07
 */

@Component
public class AuthFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public AuthFilter() {
        super();
    }

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        String token = requestContext.getRequest().getHeader("Bearer");
//        // 以下为动态配置
//        JWTUtils jwtUtils = JWTUtils.getInstance(System.getProperty("rsa.modulus"), System.getProperty("rsa.privateExponent"), System.getProperty("rsa.publicExponent"));
        JWTUtils jwtUtils =JWTUtils.getInstance();
        // 白名单，放过
        List<String> whiteApis =this.filterProperties.getWhitelist();
        String uri = requestContext.getRequest().getRequestURI();

        for(String wapi : whiteApis){
            if (uri.contains(wapi)){
                return null;
            }
        }

        // path uri 处理
        for (String wapi : whiteApis) {
            if (wapi.contains("{") && wapi.contains(")")) {
                if (wapi.split("/").length == uri.split("/").length) {
                    String reg = wapi.replaceAll("\\{.*}", ".*{1,}");
                    Pattern r = Pattern.compile(reg);
                    Matcher m = r.matcher(uri);
                    if (m.find()) {
                        return null;
                    }
                }
            }
        }

        // 验证 TOKEN
        if (!StringUtils.hasText(token)) {
            requestContext.setSendZuulResponse(false);
            requestContext.set("isSuccess", false);
            ResponseData data = ResponseData.fail("非法请求【缺少 Authorization 信息】", ResponseCode.NO_AUTH_CODE.getCode());
            requestContext.setResponseBody(JsonUtils.toJson(data));
            requestContext.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }

        JWTUtils.JWTResult jwt = jwtUtils.checkToken(token);
        if (!jwt.isStatus()) {
            requestContext.setSendZuulResponse(false);
            requestContext.set("isSuccess", false);
            ResponseData data = ResponseData.fail(jwt.getMsg(), jwt.getCode());
            requestContext.setResponseBody(JsonUtils.toJson(data));
            requestContext.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        requestContext.addZuulRequestHeader("uid", jwt.getUid());
        return null;
    }
}
