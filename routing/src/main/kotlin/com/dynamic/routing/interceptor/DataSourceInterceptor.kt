package com.dynamic.routing.interceptor

import com.dynamic.routing.config.BranchContextHolder
import com.dynamic.routing.constant.BranchEnum
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class DataSourceInterceptor: HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val branch = request.getHeader("branch")
        if(BranchEnum.SERVER.toString().equals(branch, ignoreCase = true)){
            BranchContextHolder.setBranchContext(BranchEnum.SERVER)
        }else{
            BranchContextHolder.setBranchContext(BranchEnum.LOCAL)
        }
        return super.preHandle(request, response, handler)
    }
}