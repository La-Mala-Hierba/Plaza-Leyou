package com.leyou.gateway.filter;

import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import com.leyou.gateway.config.FilterProperties;
import com.leyou.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {

        //Obtener allowPaths
        List<String> allowPaths = this.filterProperties.getAllowPaths();

        //Obtener url
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String url = request.getRequestURL().toString();

        //Los requests como Login, Register no deberían estar incluidos en el filtro.
        for (String allowPath : allowPaths) {
            if (StringUtils.contains(url, allowPath)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // Obtener el cookie
        String token = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());
        // Evalúa si el token está vacío
        if (StringUtils.isBlank(token)){
            context.setSendZuulResponse(false); // set Zuul not to forward the request to microservice
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        try {
            //Decodifica token
            JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            context.setSendZuulResponse(false); // set Zuul not to forward the request to microservice
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            logger.error("Acceso inválido, Cesión no iniciado, dirección: {}", request.getRemoteHost(), e );
        }
        return null;
    }
}
