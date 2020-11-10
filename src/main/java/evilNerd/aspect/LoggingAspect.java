package evilNerd.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    //   private Map<Integer, String> countMap = new HashMap<>();

//        @Before("aroundRepositoryPointcut()")
//    public void logBefore(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " start from before advice");
//    }
//
//    @AfterReturning(pointcut = "aroundRepositoryPointcut()")
//    public void doAccessCheck(JoinPoint joinPoint) {
//        log.info("Method " + joinPoint.getSignature().getName() + " finished from AfterReturning");
//    }


    // @Pointcut("execution(* evilNerd.repository.impl.*..Impl*.*(..))")
    //  @Pointcut("execution(* evilNerd.repository.impl.UserRepositoryJdbcTemplateImpl.*(..))")
     @Pointcut("execution(* evilNerd.repository.impl.CarsRepositoryJdbcTemplateImpl.*(..))")
    public void aroundRepositoryPointcut(){
    }
    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        int count = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Method " + joinPoint.getSignature().getName() + " start");

        Object proceed = joinPoint.proceed();

        switch (joinPoint.getSignature().getName()){
            case "search":
                ++count;
                break;
            case "save":
                ++count;
                break;
            case "findAll":
                ++count;
                break;
            case "findById":
                ++count;
                break;
            default:
                break;
        }
        stopWatch.stop();
        log.info("Method " + joinPoint.getSignature().getName() + " finished " + stopWatch.getTotalTimeMillis()+ " MILLISECINDS");
        log.info(joinPoint.getSignature().getName() + " Все методы вызывались " + count + " раз");
        return proceed;
    }

}
