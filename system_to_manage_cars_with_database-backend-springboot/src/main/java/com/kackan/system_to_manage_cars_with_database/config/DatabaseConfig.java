package com.kackan.system_to_manage_cars_with_database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private DataSource dataSource;

    @Autowired
    public DatabaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate()
    {
        JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init()
    {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        String sql="CREATE TABLE cars(car_id int not null, mark varchar(255), model varchar(255), color varchar(255),year_of_production int , PRIMARY KEY(car_id))";
        jdbcTemplate.update(sql);

        String sqlInsert="INSERT INTO cars VALUES (1,'Opel','Astra','Red',1995)";
        String sqlInsert1="INSERT INTO cars VALUES (2,'BMW','E36','Black',1990)";
        String sqlInsert2="INSERT INTO cars VALUES (3,'Fiat','126p','Red',1980)";
        String sqlInsert3="INSERT INTO cars VALUES (4,'Fiat','Panda','Red',1982)";
        String sqlInsert4="INSERT INTO cars VALUES (6,'Ford','Mustang','Red',1994)";

        jdbcTemplate.update(sqlInsert);
        jdbcTemplate.update(sqlInsert1);
        jdbcTemplate.update(sqlInsert2);
        jdbcTemplate.update(sqlInsert3);
        jdbcTemplate.update(sqlInsert4);

    }


}
