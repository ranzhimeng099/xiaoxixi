package com.xxrr.home.servlet;

import com.alibaba.fastjson.JSON;
import com.xxrr.home.action.BaseAction;
import com.xxrr.home.action.FirstSpringAction;
import com.xxrr.home.domain.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class RequestDispatcher extends HttpServlet {

    private static final String EXEC_ACTIONS = "executeMap";
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestDispatcher.class);

    private Map<String, BaseAction> actions;

    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(context);
        actions = (Map<String, BaseAction>) springContext.getBean(EXEC_ACTIONS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String requestURI = request.getRequestURI();
        if (StringUtils.contains(requestURI, "favicon.ico")) {
            return;
        }

        // 打日志(log测试)
        LOGGER.info("-----------" + requestURI + "-----------");

        String str = "yeah, success!";
        // 获取请求aciton名称
        String actionName = getActionName(request);
        if (StringUtils.isNotBlank(actionName)) {
            BaseAction action = actions.get(actionName);
            if (action != null) {
                Result result = action.execute();
                str = JSON.toJSONString(result);
            }
        }
        LOGGER.info(str);

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            LOGGER.info("why?", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    LOGGER.info("输出流 关闭异常：", e);
                }
            }
        }
    }

    private String getActionName(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri != null && uri.length() > 1) {
            return uri.substring(1);
        }
        return null;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
