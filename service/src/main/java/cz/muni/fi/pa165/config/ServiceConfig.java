package cz.muni.fi.pa165.config;

import cz.muni.fi.pa165.PersistenceApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Created by Marek Perichta.
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = {"cz.muni.fi.pa165.service","cz.muni.fi.pa165.facade"})
public class ServiceConfig {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
