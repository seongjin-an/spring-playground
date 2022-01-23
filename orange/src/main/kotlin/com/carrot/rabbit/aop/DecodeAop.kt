package com.carrot.rabbit.aop

import com.carrot.rabbit.dto.User2
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class DecodeAop {
    @Pointcut("execution(* com.carrot.rabbit.controller..*.*(..))")
    private fun cut(){

    }

    @Pointcut("@annotation(com.carrot.rabbit.annotation.Decode)")
    private fun enableDecode(){

    }
    @Before("cut() && enableDecode()")
    fun before(joinPoint: JoinPoint){
        val args = joinPoint.args
        args.forEach{
            if(it is User2){
                val user = User2::class.java.cast(it)
                val base64Email = user.email
                val email = String(Base64.getDecoder().decode(base64Email), Charsets.UTF_8)
                user.email = email
            }
        }
    }
    @AfterReturning(value="cut() && enableDecode()", returning = "returnObj")
    fun afterReturn(joinPoint: JoinPoint, returnObj: Any){
        if(returnObj is User2){
            val user = User2::class.java.cast(returnObj)
            val email = user.email
            val base64Email = Base64.getEncoder().encodeToString(email.toByteArray())
            user.email = base64Email
        }
    }
}