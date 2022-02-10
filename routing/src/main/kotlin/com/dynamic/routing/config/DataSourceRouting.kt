package com.dynamic.routing.config

import com.dynamic.routing.constant.BranchEnum
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource

class DataSourceRouting: AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        return BranchContextHolder.getBranchContext()
    }
    fun initDataSource(localDataSource: DataSource, serverDataSource: DataSource){
        BranchContextHolder.setBranchContext(BranchEnum.LOCAL)
        val dataSourceMap = mutableMapOf<Any, Any>()
        dataSourceMap[BranchEnum.LOCAL] = localDataSource
        dataSourceMap[BranchEnum.SERVER] = serverDataSource
        setTargetDataSources(dataSourceMap)
        setDefaultTargetDataSource(localDataSource)
    }
}