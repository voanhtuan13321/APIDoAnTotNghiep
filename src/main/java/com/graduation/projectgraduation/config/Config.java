package com.graduation.projectgraduation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Configuration class.
 *
 * @author Anh Tuan
 * @version 1.0
 * @since 25/05/2023
 */
@Configuration
public class Config {

  /**
   * Cau hinh chuyen doi Java Object voi Json.
   *
   * @param builder the Jackson2ObjectMapperBuilder
   * @return ObjectMapper
   * @author Anh Tuan
   * @since 25/05/2023
   */
  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    return builder.build()
        .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

}
