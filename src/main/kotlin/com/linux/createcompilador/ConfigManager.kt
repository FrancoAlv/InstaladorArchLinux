package com.linux.createcompilador

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@ComponentScan(basePackages = ["com.linux.createcompilador"])
@PropertySource("classpath:application.properties")
open class ConfigManager {

}