package com.carrot.rabbit.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class TimerAop {

    @Pointcut("execution(* com.carrot.rabbit.controller..*.*(..))")
    private fun cut(){

    }

    @Pointcut("@annotation(com.carrot.rabbit.annotation.Timer)")
    private fun enableTimer(){

    }
    @Around("cut() && enableTimer()")
    fun around(joinPoint: ProceedingJoinPoint){
        val stopWatch = StopWatch()
        stopWatch.start()
        val result = joinPoint.proceed()
        stopWatch.stop()
        println("total time: ${stopWatch.totalTimeSeconds}")
    }

}