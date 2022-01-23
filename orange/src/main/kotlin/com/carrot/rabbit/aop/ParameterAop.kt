package com.carrot.rabbit.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import java.lang.reflect.Method
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Aspect
@Component
class ParameterAop {
    @Pointcut("execution(* com.carrot.rabbit.controller..*.*(..))")
    private fun cut(){

    }
    @Before(value = "cut()")
    fun before(joinPoint: JoinPoint){
        val methodSignature:MethodSignature = joinPoint.signature as MethodSignature
        val method: Method = methodSignature.method
        println("//////////////////////////////////////////////")
        println("method name: ${method.name}")

        val args = joinPoint.args
        if(args.isNotEmpty()) {
            args.forEach {
                println("type: ${it.javaClass.simpleName}")
                println("value: $it")
            }
        }else{
            println("args null")
        }
        println("//////////////////////////////////////////////")
    }
    @AfterReturning(value = "cut()", returning = "returnObj")
    fun afterReturn(joinPoint: JoinPoint, returnObj: Any?){
        println("return obj: $returnObj")
    }
}