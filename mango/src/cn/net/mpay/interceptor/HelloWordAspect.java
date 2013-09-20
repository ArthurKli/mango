package cn.net.mpay.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect 
@Component("helloWordAspect")
public class HelloWordAspect {
//	@Around(value = "execution(* cn.net.mpay.action.order.OrderAction.*(..))")
//	public void beforeSayHello(ProceedingJoinPoint  joinPoint) throws Throwable {
//		System.out.println("Before :" + joinPoint.getArgs());
//		System.out.println("getClass :" + joinPoint.getClass());
//		System.out.println("getTarget :" + joinPoint.getTarget());
//		System.out.println("getThis :" + joinPoint.getThis());
//		joinPoint.proceed();
//		
//	}

//	@After(value = "execution(public void com.SpringAOP.HelloWord.HelloWord.sayHello(..)) && args(message)")
//	public void afterSayHello(String message) {
//		System.out.println("After : " + message);
//	}
//
//	@Around(value = "execution(public void com.SpringAOP.HelloWord.HelloWord.sayHello(..))")
//	public void aroundSayHello(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.println("Around Before !! ");
//		joinPoint.proceed();
//		System.out.println("Around After !! ");
//	}
//
//	@AfterThrowing(value = "execution(public void com.SpringAOP.HelloWord.HelloWord.sayHello(..))", throwing = "ex")
//	public void afterThrowingSayHello(Exception ex) {
//		System.out.println("After Throwing : " + ex.getMessage());
//	}
//
//	@AfterReturning(value = "execution(public void com.SpringAOP.HelloWord.HelloWord.sayHello(..))", returning = "reval")
//	public void afterReturningSayHello(String reval) {
//		System.out.println("After Returning : " + reval);
//	}
}
