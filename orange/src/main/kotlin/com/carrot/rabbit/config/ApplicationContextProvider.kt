package com.carrot.rabbit.config

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class ApplicationContextProvider : ApplicationContextAware {

    companion object {
        private lateinit var context: ApplicationContext

        public fun getContext(): ApplicationContext {
            return context
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

}