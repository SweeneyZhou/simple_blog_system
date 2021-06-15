package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void log() {

        return;
    }

    @Before("log()")
    public void doBefore(JoinPoint jointPoint) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        Signature signature = jointPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        String classMethod = className + "." + methodName + "()";
        Object[] args = jointPoint.getArgs();

        RequestLog requestlog = new RequestLog(url, ip, classMethod, args);

        logger.info("Request:{}", requestlog);
    }

    @After("log()")
    public void doAfter() {
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("return:{}", result);
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog [url=" + url + ", ip=" + ip + ", classMethod=" + classMethod + ", args="
                    + Arrays.toString(args) + "]";
        }
    }
}
