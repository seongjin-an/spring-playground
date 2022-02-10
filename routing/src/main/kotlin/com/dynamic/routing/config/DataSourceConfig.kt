package com.dynamic.routing.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.dynamic.routing.entity"],
    entityManagerFactoryRef = "entityManagerFactoryBean",
    transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
class DataSourceConfig {
    @Bean("dataSource")
    @Primary
    fun dataSource(): DataSource {
        val routingDataSource = DataSourceRouting()
        routingDataSource.initDataSource(localDataSource = localDataSource(), serverDataSource = serverDataSource())
        return routingDataSource
    }
    @Bean("serverDataSource")
    @ConfigurationProperties(prefix = "database.datasource-server")
    fun serverDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean("localDataSource")
    @ConfigurationProperties(prefix = "database.datasource-local")
    fun localDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean("entityManagerFactoryBean")
    fun entityManagerFactoryBean(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply{
            dataSource = dataSource()
            setPackagesToScan("com.dynamic.routing.entity")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    fun transactionManager() = JpaTransactionManager(entityManagerFactoryBean().`object`!!)
}