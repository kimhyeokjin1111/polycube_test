package kr.co.polycube.backendtest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect // 이클래스를 Aspect로 등록하는 어노테이션
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final HttpServletRequest req;
    @Before("@annotation(LoggingPointCut)")
    public void printLog(){
        String userAgent = req.getHeader("User-Agent");
        log.info("**************** Client Agent: {}", userAgent);
    }
}
