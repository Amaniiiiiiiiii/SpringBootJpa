package demo.boot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component // if not added spring will ignore this bean--->Spring componrnt of aspect type
public class LoggingHandler {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* demo.boot..*(..)))")// this is known as pointcut
	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable 
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
          
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
          
        final StopWatch stopWatch = new StopWatch();
          
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
  
        //Log method execution time
        log.info("Execution time of " + className + "." + methodName + " "
                            + ":: " + stopWatch.getTotalTimeNanos() + " nano");
  
        return result;
    }
	
	 @Before("execution (* demo.boot.dao..*(..)))")
	 public void securityLogic()
	 {
		 System.out.println("Authentication successful");
	 }
	


}
