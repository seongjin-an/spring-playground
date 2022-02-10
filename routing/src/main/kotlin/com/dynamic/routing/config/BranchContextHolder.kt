package com.dynamic.routing.config

import com.dynamic.routing.constant.BranchEnum

class BranchContextHolder {
    companion object {
        private val threadLocal: ThreadLocal<BranchEnum> = ThreadLocal()

        fun setBranchContext(branchEnum: BranchEnum){
            threadLocal.set(branchEnum)
        }

        fun getBranchContext(): BranchEnum{
            return threadLocal.get()
        }

        fun clearBranchContext(){
            threadLocal.remove()
        }
    }
}