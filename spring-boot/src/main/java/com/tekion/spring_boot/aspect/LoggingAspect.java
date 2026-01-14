package com.tekion.spring_boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * This aspect logs the execution time of all methods in the service layer.
     * It demonstrates AOP by wrapping service methods with a proxy.
     */
    @Around("execution(* com.tekion.spring_boot.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.info("🔵 [AOP PROXY] Entering method: {}.{}", className, methodName);
        logger.info("🔵 [AOP PROXY] Method is being wrapped by a proxy - AOP is working!");
        
        Object result;
        try {
            // Proceed with the actual method execution
            result = joinPoint.proceed();
            
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("✅ [AOP PROXY] Method {}.{} executed successfully in {} ms", 
                       className, methodName, executionTime);
            
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.error("❌ [AOP PROXY] Method {}.{} threw exception after {} ms: {}", 
                        className, methodName, executionTime, e.getMessage());
            throw e;
        }
        
        return result;
    }
}

