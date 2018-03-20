package com.billy.demo.open;

import com.billy.demo.open.util.DatetimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 打印请求参数
 */
public class RequestParameterInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(RequestParameterInterceptor.class);

    /**
     * 当*Controller的业务方法里出现了异常，此方法就不会被执行。
     *
     * @param request
     * @param response
     * @param handler
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod h = (HandlerMethod) handler;

        StringBuilder sb = new StringBuilder(1000);

        sb.append("-------------").append(DatetimeUtil.getDateTimeString(new Date()))
                .append("-------------\n");
        sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
        sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
        sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
        sb.append("URI       : ").append(request.getRequestURI()).append("\n");
        LOG.info(sb.toString());

        return true;
    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }


}
