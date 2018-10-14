package com.xstudy.test.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * @author zurun
 * @date 2018/2/9 17:51:33
 */
//@EnableConfigurationProperties
@Configuration
@Data
//@PropertySource(value = "classpath:/config/redis.properties")
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 是否启用集群
     */
    @Value("${spring.redis.iscluster}")
    private Boolean isluster;
    /**
     * Redis 集群的节点
     */
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    /**
     * 连接redis的工厂类
     *
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory;
        if (isluster) {
            factory = new JedisConnectionFactory(new RedisClusterConfiguration(this.getNodes()));
        } else {
            factory = new JedisConnectionFactory();
        }
        factory.setHostName(host);
        factory.setPort(port);
//        factory.setTimeout(timeout);
//        factory.setPassword(password);
//        factory.setDatabase(database);
        return factory;
    }

    @Bean
    StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    JdkSerializationRedisSerializer jdkSerializationRedisSerializer() {
        return new JdkSerializationRedisSerializer();
    }

    @Bean
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer(Object.class);
    }

    /**
     * 配置RedisTemplate
     * 设置原生序列化器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisTemplate<String, Object> jdkRedisTemplate(
            @Qualifier("jedisConnectionFactory") JedisConnectionFactory connectionFactory,
            @Qualifier("jdkSerializationRedisSerializer") RedisSerializer jdkSerializationRedisSerializer,
            @Qualifier("stringRedisSerializer") RedisSerializer stringRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);

        changeSerializer(redisTemplate, jdkSerializationRedisSerializer, stringRedisSerializer);
        return redisTemplate;
    }

    /**
     * 配置RedisTemplate
     * 设置string序列化器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisTemplate<String, Object> stringRedisTemplate(
            @Qualifier("jedisConnectionFactory") JedisConnectionFactory connectionFactory,
            @Qualifier("stringRedisSerializer") RedisSerializer stringRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);

        changeSerializer(redisTemplate, stringRedisSerializer, stringRedisSerializer);
        return redisTemplate;
    }

    /**
     * 配置RedisTemplate
     * 设置json序列化器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RedisTemplate<String, Object> jackson2RedisTemplate(
            @Qualifier("jedisConnectionFactory") JedisConnectionFactory connectionFactory,
            @Qualifier("jackson2JsonRedisSerializer") RedisSerializer jackson2JsonRedisSerializer,
            @Qualifier("stringRedisSerializer") RedisSerializer stringRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);

        changeSerializer(redisTemplate, jackson2JsonRedisSerializer, stringRedisSerializer);
        return redisTemplate;
    }

    /**
     * 更改序列化方式
     * 约定所有的key都为string
     *
     * @param redisTemplate
     * @param redisSerializer
     * @param stringRedisSerializer
     */
    private void changeSerializer(RedisTemplate redisTemplate,
                                  RedisSerializer redisSerializer,
                                  RedisSerializer stringRedisSerializer) {

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setDefaultSerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();

    }

//    /**
//     * 注:Qualifier注解用来指定bean,消除重复bean提示
//     *
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public RedisUtils redisUtils(@Qualifier("jedisConnectionFactory") JedisConnectionFactory connectionFactory) {
//        return new RedisUtils();
//    }


}
