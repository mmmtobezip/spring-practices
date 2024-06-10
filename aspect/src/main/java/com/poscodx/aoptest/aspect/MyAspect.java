package com.poscodx.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

  @Before("execution(public com.poscodx.aoptest.vo.ProductVo com.poscodx.aoptest.service.ProductService.find(String))")
  public void adviceBefore() {
    System.out.println("-------Before Advice ------");
  }

  @After("execution(com.poscodx.aoptest.vo.ProductVo com.poscodx.aoptest.service.ProductService.find(String))")
  public void adviceAfter() {
    System.out.println("-------After Advice ------");
  }

  @AfterReturning("execution(* com.poscodx.aoptest.service.ProductService.find(..))")
  public void adviceAfterReturning() {
    System.out.println("-------AfterReturning Advice ------");
  }

  // * : 모든 return값
  // *..*. : package 이름을 줄임
  // overloading이 되어 있는 경우, find(..)로 처리하면 편리
  // execution(* *..*.*.*(..)) 동일하게 동작
  @AfterThrowing(value = "execution(* *..*.ProductService.*(..))", throwing = "ex")
  public void adviceAfterThrowing(Throwable ex) {
    System.out.println("------AfterThrowing Advice " + ex + "------");
  }

  @Around("execution(* *..*.ProductService.*(..))")
  public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
    /* 실행 순서 */
    /* 1. Before */
    System.out.println("------Around(Before)------");

    /* 2. Point Cut Method 실행 */
    Object result = pjp.proceed();

    // Object[] params = {"Camera"};
    // Object result = pjp.proceed(params);

    /* 3. After */
    System.out.println("------Around(After)------");

    return result;
  }
}
