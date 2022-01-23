package com.carrot.rabbit.annotation

import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Timer()
