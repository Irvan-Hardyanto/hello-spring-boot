package com.example.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.example.demo.employee.Employee;

@Configuration
public class CacheConfig {

  // @Bean
  // public CacheManager cacheManager() {
  //     return new ConcurrentMapCacheManager("employees");
  // }

  @Bean
  LettuceConnectionFactory connectionFactory() {
    return new LettuceConnectionFactory();
  }

  //ResponseEntity is not Serializable, so it is better to do the caching at the service level, not the controller
  @Bean
  public RedisTemplate<String, List<Employee>> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, List<Employee>> template = new RedisTemplate<>();
    
    template.setConnectionFactory(connectionFactory);
    return template;
  }
}